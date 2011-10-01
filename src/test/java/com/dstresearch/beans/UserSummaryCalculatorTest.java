/**
 * $Id$
 *
 * 
 */
package com.dstresearch.beans;

import static org.junit.Assert.*;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.dstresearch.chess.db.DbReader;

/**
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = { "/testContext.xml" } )

public class UserSummaryCalculatorTest
	{
	private static final Logger	log	= Logger.getLogger( UserSummaryCalculatorTest.class );

	@Resource( name = "dbReader" )
	private	DbReader	reader;
	
	@Test
	public	void	testCalculations()
		{
		UserSummaryCalculator	c	= new UserSummaryCalculator( this.reader );
		
		Map< String, UserSummary > map	= c.compute();
		for ( String key : map.keySet() )
			{
			UserSummary	u	= map.get( key );
			assertTrue( "something doesn't add up!", u.getGames() == u.getWins() + u.getDraws() + u.getLosses() );
			log.info( key + ":  " + map.get( key ) + ", " + u.getWinPercentage() );
			}
		}
	}
