/**
 * $Id$
 *
 * 
 */
package com.dstresearch.chess.mvc;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import com.dstresearch.beans.UserSummary;
import com.dstresearch.beans.UserSummaryCalculator;
import com.dstresearch.chess.db.DbReader;

/**
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */
public class SummaryHandler
	{
	private static final Logger	log		= Logger.getLogger( SummaryHandler.class );
	
	protected class SortBy
		{
		protected	int	tieBreaker( UserSummary o1, UserSummary o2 )
			{
			return( o1.getWinPercentage() > o2.getWinPercentage() ? -1 : 1 );
			}
		}
	
	protected class SortByPlayerName extends SortBy implements Comparator< UserSummary >
		{

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compare( UserSummary o1, UserSummary o2 )
			{
			return( o1.getPlayer().compareToIgnoreCase( o2.getPlayer() ) );
			}
		
		}

	protected class SortByWins extends SortBy implements Comparator< UserSummary >
		{

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compare( UserSummary o1, UserSummary o2 )
			{
			if ( o1.getWins() == o2.getWins() )
				{
				return( tieBreaker( o1, o2 ) );
				}
			if ( o1.getWins() > o2.getWins() )
				return( -1 );
			else
				return( 1 );
			}
		
		}

	protected class SortByWinPercentage implements Comparator< UserSummary >
		{

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compare( UserSummary o1, UserSummary o2 )
			{
			if ( o1.getWinPercentage() == o2.getWinPercentage() )
				{
				if ( o1.getGames() > o2.getGames() )
					return( -1 );
				else
					return( 1 );
				}
			if ( o1.getWinPercentage() > o2.getWinPercentage() )
				return( -1 );
			else
				return( 1 );
			}
		
		}

	protected class SortByGamesPlayed extends SortBy implements Comparator< UserSummary >
		{

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compare( UserSummary o1, UserSummary o2 )
			{
			if ( o1.getGames() == o2.getGames() )
				{
				return( tieBreaker( o1, o2 ) );
				}
			if ( o1.getGames() > o2.getGames() )
				return( -1 );
			else
				return( 1 );
			}
		
		}

	/**
	 * Processes the GET request for the welcome page.
	 * 
	 * @param req
	 * @param msgs
	 * @return
	 */
	public	ModelAndView	get( DbReader reader, HttpServletRequest req, MessageSource msgs, String thisPage )
		{
		Map< String, UserSummary >	map;
		SortedSet< UserSummary >	sorted	= null;
		Comparator< UserSummary >	sorter	= null;
		
		ModelAndView	mav	= new ModelAndView( "summaryStats" );

		if ( req == null )
			log.warn( "ServletRequest is NULL!" );
		if ( reader == null )
			log.fatal( "reader is NULL!" );
		
		UserSummaryCalculator	engine	= new UserSummaryCalculator( reader );
		map	= engine.compute();
		
		// how do they want the data sorted?
		String	sortOrder;

		if ( ( sortOrder = req.getParameter( "sort" ) ) != null )
			{
			if ( sortOrder.equalsIgnoreCase( "Player" ) )
				sorter	= new SortByPlayerName();
			else if ( sortOrder.equalsIgnoreCase( "GamesPlayed" ) )
				sorter	= new SortByGamesPlayed();
			else if ( sortOrder.equalsIgnoreCase( "Wins" ) )
				sorter	= new SortByWins();
			else if ( sortOrder.equalsIgnoreCase( "WinPercentage" ) )
				sorter	= new SortByWinPercentage();
			}
		else
			{
			sorter	= new SortByPlayerName();
			}
		
		sorted		= new TreeSet< UserSummary > ( sorter );
		
		
		for ( String key : map.keySet() )
			{
			sorted.add( map.get( key ) );
			}
	
		mav.addObject( "thisPage", thisPage );
		mav.addObject( "stats", sorted );
		mav.addObject( "keys", map.keySet() );
		mav.addObject( "sortOrder", ( sortOrder == null ) ? "" : sortOrder.toLowerCase() );
		
		// various ways to view this page (for next time)
		mav.addObject( "viewByPlayer", thisPage + "?sort=Player" );
		mav.addObject( "viewByGames", thisPage + "?sort=GamesPlayed" );
		mav.addObject( "viewByWins", thisPage + "?sort=Wins" );
		mav.addObject( "viewByWinningPercentage", thisPage + "?sort=WinPercentage" );
		
		// last time the data was updated/computed
		mav.addObject( "lastUpdate", engine.getLastUpdate() );
		
		mav.addObject( "pageTitle", "" );
		mav.addObject( "message", msgs.getMessage( "message", new Object[] { "hello, world" }, req.getLocale() ) );
		mav.addObject( "userLocale", req.getLocale().toString() );

		mav.addObject( "requestURI", req.getRequestURI() );
		mav.addObject( "requestURL", req.getRequestURL() );

		return( mav );
		}

	}
