/**
 * $Id$
 *
 * 
 */
package com.dstresearch.beans;

import java.sql.Timestamp;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.dstresearch.chess.db.DbReader;

/**
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */
public class UserSummaryCalculator
	{
	private static final Logger	log		= Logger.getLogger( UserSummaryCalculator.class );
	
	private	DbReader	reader			= null;
	
	public	UserSummaryCalculator( DbReader reader )
		{
		this.reader	= reader;
		}
	
	protected class PerUserData
		{
		public	long	games;
		public	long	wins;
		public	long	draws;
		public	long	streak;
		public	char	streakType;
		public	long	longestWinStreak;
		public	long	longestLossStreak;
		public	String	last10;
		Deque< Integer >	que;
		
		protected PerUserData()
			{
			this.games	= 0;
			this.wins	= 0;
			this.draws	= 0;
			this.streak	= 0;
			this.streakType	= ' ';
			this.longestLossStreak = 0;
			this.longestWinStreak = 0;
			this.que	= new ArrayDeque< Integer >();
			}
		
		public	void push( char result )
			{
			assert( result != '?' );
			
			// increment their games, wins, draws
			this.games++;
			if ( result == 'W' )
				{
				this.wins++;
				}
			else if ( result == 'D' )
				{
				this.draws++;
				}
			else
				{
				// s.losses;  note that losses are computed!
				}
					
			// see what kind of streak they're on
			if ( this.streakType == result )
				this.streak++;
			else
				{
				if ( this.streakType == 'W' && this.streak > this.longestWinStreak )
					{
					this.longestWinStreak	= this.streak;
					}
				else if ( this.streakType == 'L' && this.streak > this.longestLossStreak )
					this.longestLossStreak	= this.streak;
				
				this.streak	= 1;
				this.streakType	= result;
				}
			
			if ( this.que.size() == 10 )
				this.que.remove();
			
			Integer	lresult	= 1;
			if ( result == 'L' )
				lresult	= -1;
			else if ( result == 'D' )
				lresult = 0;
			
			this.que.add( lresult );
			}
		
		public void summarize()
			{
			// final check if their current streak is their longest streak
			if ( this.streakType == 'W' && this.streak > this.longestWinStreak )
				this.longestWinStreak	= this.streak;
			else if ( this.streakType == 'L' && this.streak > this.longestLossStreak )
				this.longestLossStreak	= this.streak;
			
			// set their current streak
			if ( this.streakType == 'D' )
				this.streak	= 0;
			if ( this.streakType == 'L' )
				this.streak	*= -1;
			
			// last 10 games
			int	wins	= 0;
			int	losses	= 0;
			int	result;
			
			while ( this.que.size() > 0 )
				{
				result	= this.que.remove();
				if ( result > 0 )
					wins++;
				if ( result < 0 )
					losses++;
				}
			this.last10	= new String( wins + "-" + losses );
			}
		}
	
	public Timestamp getLastUpdate()
		{
		return( new Timestamp( System.currentTimeMillis() ) );
		}
	
	public Map< String, UserSummary >	compute()
		{
		List< DbReader.GameParticipationRecord > l = this.reader.getGameResults();
		List< String >			players	= this.reader.getPlayers();
		Map< String, PerUserData >	map	= new HashMap< String, PerUserData >();
		Map< String, UserSummary >	results	= new HashMap< String, UserSummary >();
		String	currentResult;
		
		for ( int i = 0; i < players.size(); i++ )
			{
			map.put( players.get( i ), new PerUserData() );
			}
		
		for ( int i = 0; i < l.size(); i++ )
			{
			DbReader.GameParticipationRecord	gpr	= l.get( i );
			PerUserData	s	= map.get( gpr.user );
			char	result = '?';
			
			if ( gpr.result.equals( gpr.side ) )
				result	= 'W';
			else
				result	= 'L';
			if ( gpr.result.equals( "D" ) || gpr.result.equals( "N" ) )
				result	= 'D';
			
			s.push( result );
			}
		
		for ( String key : map.keySet() )
			{
			PerUserData	p	= map.get( key );

			p.summarize();
			
			UserSummary	s	= new UserSummary( key, p.games, p.wins, p.draws,
							p.longestWinStreak, p.longestLossStreak,
							p.streak, p.last10 );
			results.put( key, s );
			}
		
		return( results );
		}
	}
