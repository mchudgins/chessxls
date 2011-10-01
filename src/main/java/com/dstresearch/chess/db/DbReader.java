/**
 * $Id$
 *
 * 
 */
package com.dstresearch.chess.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */
public class DbReader
	{
	private static final Logger	log		= Logger.getLogger( DbReader.class );
	private DataSource		ds		= null;
	private JdbcTemplate		jdbcTemplate	= null;
	
	protected GameParticipationRecord	gprFactory( ResultSet rs, int rowNum ) throws SQLException
		{
		long	id	= rs.getLong( "id" );
		Timestamp date	= rs.getTimestamp( "playdate" );
		String	result	= rs.getString( "winner" );
		String	user	= rs.getString( "user" );
		String	side	= rs.getString( "side" );
		
		return( new GameParticipationRecord( id, date, result, user, side ) );
		}

	public	class GameParticipationRecord
		{
		protected	GameParticipationRecord( long id, Timestamp date, String result, String user, String side )
			{
			this.gameId	= id;
			this.date	= date;
			this.result	= result;
			this.user	= user;
			this.side	= side;
			}
		
		public	long		gameId;
		public	Timestamp	date;
		public	String		result;
		public	String		user;
		public	String		side;
		
		public	String	getScore()
			{
			if ( this.result.equals( "D" ) || this.result.equals( "N" ) )
				return( "+1/2" );
			
			return( this.result.equals( this.side ) ? "+1" : "0" );
			}
		}
	
	class	Pair< First, Second >
		{
		public	First	key;
		public	Second	value;
		
		public	Pair( First key, Second value )
			{
			this.key	= key;
			this.value	= value;
			}
		}

	/**
	 * 
	 * Sets the datasource for the DAO.
	 * 
	 * The datasource must be set prior to utilizing the DAO. In an application operating with Spring's dependency injection,
	 * the datasource may be set via configuration in the spring configuration xml.
	 * 
	 * @param ds the datasource used to construct a Spring <a href=
	 *                "http://static.springsource.org/spring/docs/3.0.x/api/org/springframework/jdbc/core/JdbcTemplate.html"
	 *                >JdbcTemplate</a>.
	 */
	public void setDataSource( DataSource ds )
		{
		this.ds = ds;
		this.jdbcTemplate = new JdbcTemplate( this.ds );
		}

	/**
	 * 
	 * Gets the current datasource.
	 * 
	 * @return the current datasource object.
	 */

	public DataSource getDataSource()
		{
		return( this.ds );
		}
	
	/**
	 * 
	 * Gets the current JdbcTemplate.
	 * 
	 * @return the JdbcTemplate for the DAO.
	 */
	protected	JdbcTemplate	getJdbcTemplate()
		{
		return( this.jdbcTemplate );
		}
	
	/*
	 * (non-Javadoc)
	 * @see com.dstresearch.spins.data.GenericDao#isInternallyValid()
	 */
	public boolean isInternallyValid()
		{
		if ( getJdbcTemplate() == null )
			log.fatal( getClass().getName() + ".jdbcTemplate is null" );
		return( getJdbcTemplate() != null );
		}

	/**
	 * 
	 * @param sql
	 * @return
	 */
	protected	Map< String, Integer >	getGamesPerUser( String sql )
		{
		HashMap< String, Integer >	map	= new HashMap< String, Integer >();
		List< Pair< String, Integer > >	l;

		assert ( isInternallyValid() );

		try
			{
			l = getJdbcTemplate()
					.query( sql,
						new Object[] {},
						new RowMapper< Pair< String, Integer > >()
							{
								public Pair< String, Integer > mapRow(
										ResultSet rs,
										int rowNum )
										throws SQLException
									{
									return( new Pair< String, Integer >( rs.getString( 2 ), rs.getInt( 1 ) ) );
									}
							} );
			for ( int i = 0; i < l.size(); i++ )
				{
				Pair< String, Integer >	item	= l.get( i );
				map.put( item.key, item.value );
				}
			}
		catch ( Exception e )
			{
			log.fatal( "getTotalGamesPerUser -- "
					+ e.getLocalizedMessage() );
			}

		return( map );
		}

	
	/**
	 * Gets the Total Games for all Users
	 */
	
	public	Map< String, Integer >	getTotalGamesPerUser()
		{
		return( this.getGamesPerUser( "select count(*), user from games g, games_teams_xref x where g.id=x.game_id group by x.user" ) );
		}
	
	/**
	 * Gets the Won Games for all Users
	 */

	public	Map< String, Integer >	getWonGamesPerUser()
		{
		return( this.getGamesPerUser( "select count(*), user from games g, games_teams_xref x "
				+ "where g.id=x.game_id and g.winner=x.side group by x.user" ) );
		}
	
	
	/**
	 * Gets the Drawn Games for all Users
	 */

	public	Map< String, Integer >	getDrawnGamesPerUser()
		{
		return( this.getGamesPerUser( "select count(*), user from games g, games_teams_xref x "
				+ "where g.id=x.game_id and (g.winner='N' or g.winner='D') group by x.user" ) );
		}
	
	public	List< GameParticipationRecord >	getGameResults()
		{
		List< GameParticipationRecord >	l	= null;

		assert ( isInternallyValid() );

		try
			{
			l = getJdbcTemplate()
					.query( "select * from games g, games_teams_xref x where g.id=x.game_id order by g.id asc",
						new Object[] {},
						new RowMapper< GameParticipationRecord >()
							{
							public GameParticipationRecord mapRow(
									ResultSet rs,
									int rowNum )
									throws SQLException
								{
								return( gprFactory( rs, rowNum ) );
								}
							} );
			}
		catch ( Exception e )
			{
			log.fatal( "getTotalGamesPerUser -- "
					+ e.getLocalizedMessage() );
			}

		return( l );
		}
	
	public	List< String > getPlayers()
		{
		List< String >	l	= null;
		
		assert( isInternallyValid() );
		
		try
			{
			l	= this.getJdbcTemplate()
					.query(  "select distinct( user ) from games_teams_xref order by user asc",
						new Object[] {},
						new RowMapper< String >()
							{
							public String mapRow(
								ResultSet rs,
								int rowNum ) throws SQLException
								{
								return( rs.getString( 1 ) );
								}
							} );
			}
		
		catch ( Exception e )
			{
			log.fatal( "getPlayers -- "
					+ e.getLocalizedMessage() );
			}

		return( l );
		}
	}
