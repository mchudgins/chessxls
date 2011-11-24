/**
 * $Id$
 *
 * 
 */
package com.dstresearch.chess.mvc;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import com.dstresearch.beans.Game;
import com.dstresearch.chess.db.DbReader;


/**
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */
public class GameHandler
	{
	private static final Logger	log		= Logger.getLogger( GameHandler.class );
	
	public class GameRadioBtn
		{
		String	result;
		
		public GameRadioBtn( String result )
			{
			this.result	= result.toUpperCase();
			}
		
		protected String theCheck( String gameResult )
			{
			return( result.equals( gameResult ) ? "checked=\"checked\"" : "" );
			}
		
		public String getWhite()
			{
			return( theCheck( "W" ) );
			}
		
		public String getBlack()
			{
			return( theCheck( "B" ) );
			}
		
		public String getDraw()
			{
			return( theCheck( "D" ) );
			}
		}
	
	public	ModelAndView	displayGame( DbReader reader, HttpServletRequest req,
			MessageSource msgs, String thisPage, long gameId )
		{
		ModelAndView	mav	= new ModelAndView( "game" );
		
		Game		g	= reader.readGameRecord( gameId );
		
		mav.addObject( "thisPage", thisPage );

		if ( g != null )
			{
			mav.addObject( "game", g );
			mav.addObject( "rbWinner", new GameRadioBtn( g.getWinner() ) );
			}
		else
			{
			// TODO
			log.fatal( "game not found.  redirect." );
			}
		
		return( mav );
		}
	
	
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
		ModelAndView	mav	= new ModelAndView( "games" );

		// how do they want the data sorted?
		String	sortOrder	= null;
		if ( ( sortOrder = req.getParameter( "sort" ) ) != null )
			{
			
			}

		List< Game >	games	= null;
		PaginatorHelper	pager	= new PaginatorHelper( req );
		int		pageNum	= pager.getPageNum();
		int		itemsNum = pager.getItemsPerPage();
		
		if ( pageNum < 2 )
			games	= reader.getAllGames( itemsNum );
		else
			games	= reader.getMoreGames( ( pageNum - 1 ) * itemsNum, itemsNum );
			
		mav.addObject( "sortOrder", ( sortOrder == null ) ? "" : sortOrder.toLowerCase() );
		
		// last time the data was updated/computed
//		mav.addObject( "lastUpdate", engine.getLastUpdate() );
		
		mav.addObject( "pageTitle", "" );
		mav.addObject( "games", games );
		mav.addObject( "page", pageNum );
		mav.addObject( "itemsPerPage", itemsNum );
		mav.addObject( "totalItems", reader.getTotalGameCount() );
		
		return( mav );
		}
	}
