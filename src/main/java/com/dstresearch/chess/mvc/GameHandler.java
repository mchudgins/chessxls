/**
 * $Id$
 *
 * 
 */
package com.dstresearch.chess.mvc;

import javax.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import com.dstresearch.chess.db.DbReader;
import com.dstresearch.chess.mvc.SummaryHandler.SortByPlayerName;

/**
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */
public class GameHandler
	{
	public	ModelAndView	get( DbReader reader, HttpServletRequest req, MessageSource msgs, String thisPage )
		{
		ModelAndView	mav	= new ModelAndView( "games" );

		// how do they want the data sorted?
		String	sortOrder;
		if ( ( sortOrder = req.getParameter( "sort" ) ) != null )
			{
			
			}
			
		mav.addObject( "sortOrder", ( sortOrder == null ) ? "" : sortOrder.toLowerCase() );
		
		// various ways to view this page (for next time)
		mav.addObject( "viewByPlayer", thisPage + "?sort=Player" );
		mav.addObject( "viewByGames", thisPage + "?sort=GamesPlayed" );
		mav.addObject( "viewByWins", thisPage + "?sort=Wins" );
		mav.addObject( "viewByWinningPercentage", thisPage + "?sort=WinPercentage" );
		
		// last time the data was updated/computed
//		mav.addObject( "lastUpdate", engine.getLastUpdate() );
		
		mav.addObject( "pageTitle", "" );
		mav.addObject( "message", msgs.getMessage( "message", new Object[] { "hello, world" }, req.getLocale() ) );
		mav.addObject( "userLocale", req.getLocale().toString() );
	
		mav.addObject( "requestURI", req.getRequestURI() );
		mav.addObject( "requestURL", req.getRequestURL() );
	
		return( mav );
		}
	}
