/**
 * $Id$
 *
 * 
 */
package com.dstresearch.chess.mvc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import com.dstresearch.beans.Game;
import com.dstresearch.beans.GameForm;
import com.dstresearch.chess.db.DbReader;

/**
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */
public class NewGameHandler
	{
	private static final Logger	log		= Logger.getLogger( MainController.class );

	/**
	 * 
	 * @param reader
	 * @param req
	 * @param msgs
	 * @param thisPage
	 * @return
	 */
	public	ModelAndView	get( DbReader reader, HttpServletRequest req, MessageSource msgs, String thisPage )
		{
		ModelAndView	mav	= new ModelAndView( "newGame" );

		GameForm	game	= new GameForm( reader );
		
		//
		// get the list of known players
		//
		
		List< String >	players	= reader.getPlayers();
		mav.addObject( "players", players );
			
//		mav.addObject( "lastUpdate", engine.getLastUpdate() );
		
		mav.addObject( "thisPage", thisPage );
		mav.addObject( "pageTitle", "" );
		mav.addObject( "message", msgs.getMessage( "message", new Object[] { "hello, world" }, req.getLocale() ) );
		mav.addObject( "userLocale", req.getLocale().toString() );
		mav.addObject( "game", game );
	
		return( mav );
		}
	
	/**
	 * 
	 * @param reader
	 * @param req
	 * @param msgs
	 * @param thisPage
	 * @return
	 * @throws ParseException 
	 */
	
	public	ModelAndView	post( DbReader reader, HttpServletRequest req, MessageSource msgs, String thisPage ) throws ParseException
		{
		ModelAndView	mav	= new ModelAndView( "newGame" );

		// create & populate a game structure
		GameForm	game	= new GameForm( reader );
		String		playDate= req.getParameter( "playDate" );
		
		try
			{
			SimpleDateFormat fmt = new SimpleDateFormat( "yyyy-MM-dd" );
			game.date	= fmt.parse( req.getParameter( "playDate" ) );
			}
		catch ( ParseException e1 )
			{
			// TODO Auto-generated catch block
			log.fatal( "yikes -- " + e1.getClass().getName() + " " + e1.getLocalizedMessage() );
			throw( e1 );
			}
		
		//
		// get the list of known players & see who played
		//
		List< String >	players	= reader.getPlayers();

		for ( String p : players )
			{
			String	side;
			if ( ( side = req.getParameter( p ) ) != null )
				{
				if ( side.equals( "white" ) )
					{
					game.teams.put( p, "W" );
					}
				else if ( side.equals(  "black" ) )
					{
					game.teams.put( p, "B" );
					}
				}
			}
		
		// make sure somebody played
		if ( game.teams.isEmpty() )
			{
			mav.addObject( "error", "Who played?" );
			mav.addObject( "players", players );
			mav.addObject( "playDate", playDate );
			
			return( mav );
			}
		
		// and that there's one white and one black player
		boolean	fWhitePlayer	= false;
		boolean	fBlackPlayer	= false;
		for ( String key : game.teams.keySet() )
			{
			String	side	= game.teams.get( key );
			
			if ( side.equals( "W" ) )
				fWhitePlayer	= true;
			else if ( side.equals( "B" ) )
				fBlackPlayer	= true;
			}
		
		String	error	= null;
		if ( ! fWhitePlayer )
			error	= "Nobody played on white's side?";
		else if ( ! fBlackPlayer )
			error	= "Nobody played on black's side?";
		if ( !fWhitePlayer || ! fBlackPlayer )
			{
			mav.addObject( "error", error );
			mav.addObject( "players", players );
			mav.addObject( "playDate", playDate );
			
			return( mav );
			}
		
		// who won?
		game.result	= "D";
		String	winner	= req.getParameter( "winner" );
		if ( winner == null )
			{
			mav.addObject( "error", "Did you forget to select the winner?" );
			mav.addObject( "players", players );
			mav.addObject( "playDate", playDate );

			return( mav );
			}
		if ( winner.equals( "white" ) )
			game.result	= "W";
		else if ( winner.equals( "black" ) )
			game.result	= "B";

		long	gameId	= reader.writeGameRecord( game );
		if ( gameId != 0 )
			{
			mav.setViewName( "redirect:"
				+ "/game/"
				+ gameId );
			}
		
		return( mav );
		}
	}
