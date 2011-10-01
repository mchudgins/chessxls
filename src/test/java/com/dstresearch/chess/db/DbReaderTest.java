/**
 * $Id$
 *
 * 
 */
package com.dstresearch.chess.db;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = { "/testContext.xml" } )

public class DbReaderTest
	{
	private static final Logger	log	= Logger.getLogger( DbReaderTest.class );

	@Resource( name = "dbReader" )
	private	DbReader	reader;
	
	@Test
	public	void	totalGamesPerUser()
		{
		Map< String, Integer >	map	= this.reader.getTotalGamesPerUser();
		
		for ( String key : map.keySet() )
			{
			log.info( key + ":  " + map.get( key ) );
			}
		}
	@Test
	public	void	wonGamesPerUser()
		{
		Map< String, Integer >	map	= this.reader.getWonGamesPerUser();
		
		for ( String key : map.keySet() )
			{
			log.info( key + ":  " + map.get( key ) );
			}
		}

	@Test
	public	void	drawnGamesPerUser()
		{
		Map< String, Integer >	map	= this.reader.getDrawnGamesPerUser();
		
		for ( String key : map.keySet() )
			{
			log.info( key + ":  " + map.get( key ) );
			}
		}
	
	@Test
	public	void	gameHistory()
		{
		List< DbReader.GameParticipationRecord >	l;
		l	= this.reader.getGameResults();
		for ( int i = 0; i < l.size(); i++ )
			{
			DbReader.GameParticipationRecord	gpr	= l.get( i );
			log.info( "Game:  " + gpr.gameId );
			log.info( "Date:  " + gpr.date );
			log.info( "User:  " + gpr.user );
			log.info( "Side:  " + gpr.side );
			log.info( "Rslt:  " + gpr.result );
			log.info( "    :  " + gpr.getScore() );
			}
		}
	}
