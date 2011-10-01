/**
 * $Id$
 *
 * 
 */
package com.dstresearch.chess.mvc;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import com.dstresearch.chess.db.DbReader;

/**
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */
public class NewGameHandler
	{
	public	ModelAndView	get( DbReader reader, HttpServletRequest req, MessageSource msgs )
		{
		ModelAndView	mav	= new ModelAndView( "newGame" );

		// how do they want the data sorted?
		String	sortOrder;
		if ( ( sortOrder = req.getParameter( "sort" ) ) != null )
			{
			
			}
		
		//
		// get the list of known players
		//
		
		List< String >	players	= reader.getPlayers();
		mav.addObject( "players", players );
			
//		mav.addObject( "lastUpdate", engine.getLastUpdate() );
		
		mav.addObject( "pageTitle", "" );
		mav.addObject( "message", msgs.getMessage( "message", new Object[] { "hello, world" }, req.getLocale() ) );
		mav.addObject( "userLocale", req.getLocale().toString() );
	
		return( mav );
		}
	
	public	ModelAndView	post(DbReader reader, HttpServletRequest req, MessageSource msgs )
		{
		ModelAndView	mav	= new ModelAndView( "testjig" );

		// how do they want the data sorted?
		String	sortOrder;
		if ( ( sortOrder = req.getParameter( "sort" ) ) != null )
			{
			
			}
		
		//
		// get the list of known players
		//
		
		List< String >	players	= reader.getPlayers();
		mav.addObject( "players", players );
			
//		mav.addObject( "lastUpdate", engine.getLastUpdate() );
		
		mav.addObject( "pageTitle", "" );
		mav.addObject( "message", msgs.getMessage( "message", new Object[] { "hello, world" }, req.getLocale() ) );
		mav.addObject( "userLocale", req.getLocale().toString() );
	
		return( mav );
		}
	}
