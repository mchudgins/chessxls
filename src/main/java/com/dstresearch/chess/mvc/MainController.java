/**
 * $Id$
 *
 * 
 */

package com.dstresearch.chess.mvc;

/**
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.dstresearch.util.AppBean;
import com.dstresearch.chess.db.DbReader;

/**
 * Launches handlers for URL's related to slices.
 *
 * Controls all the web requests for /slice and /work URI's.
 *
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 * @version Aug 13, 2010
 *
 */

@Controller
public class MainController
	{
	private static final Logger	log		= Logger.getLogger( MainController.class );

	/*
	 * These @Inject fields are magically initialized by the Spring dependency injection mechanism
	 * when Spring constructs a SpinsController object.
	 */
	@Inject
	private final	AppBean			app		= null;

	@Inject
	private final	MessageSource		msgs		= null;
	
	@Inject
	private		DbReader		dbReader	= null;
	
	protected String	getContextPath( HttpServletRequest req )
		{
		if ( req.getContextPath().equals( "/" ) )
			return( req.getContextPath() );
		else
			return( req.getContextPath() + "/" );
		}

	/*
	 * The routines to handle various URL's go here...
	 */
	
	/**
	 * Displays the website's welcome page.
	 */
	@RequestMapping( value = { "/welcome", "/goodbye" } )
	public	ModelAndView	welcome( HttpServletRequest req )
		{
		log.info( "/welcome" );
		WelcomeHandler	controller	= new WelcomeHandler();
		
		return( controller.get( req, this.msgs ) );
		}
	
	/**
	 * Display the Summary Stats
	 */
	@RequestMapping( value = { "/", "/standings" } )
	public	ModelAndView	summaryStats( HttpServletRequest req )
		{
		log.info( "/" );
		SummaryHandler	controller	= new SummaryHandler();
		
		String	thisURL	= this.getContextPath( req );
		
		return( controller.get( this.dbReader, req, this.msgs, "/standings" ) );
		}
	
	@RequestMapping( "/teams" )
	public	ModelAndView	teams( HttpServletRequest req )
		{
		log.info( "/teams" );
		
		TeamHandler	controller	= new TeamHandler();
		
		return( controller.get( this.dbReader, req, this.msgs, "/teams" ) );
		}
	
	@RequestMapping( "/game" )
	public	ModelAndView	games( HttpServletRequest req )
		{
		log.info( "/teams" );
		
		GameHandler	controller	= new GameHandler();
		
		return( controller.get( this.dbReader, req, this.msgs, "/games" ) );
		}
	
	@RequestMapping( "/game/{id}" )
	public	ModelAndView	game( HttpServletRequest req, @PathVariable long id )
		{
		log.info( "/game/" + id );
		GameHandler	controller	= new GameHandler();
		
		return( controller.displayGame( this.dbReader, req, this.msgs, "/game", id ) );
		}
	
	@RequestMapping( "/player/{Player}" )
	public	ModelAndView	player( HttpServletRequest req, @PathVariable String Player )
		{
		log.info( "/teams" );
		
		PlayerHandler	controller	= new PlayerHandler();
		
		return( controller.get( this.dbReader, req, this.msgs, "/player/" + Player ) );
		}
	
	/**
	 * post your games results here
	 */
	
	@RequestMapping( value="/game", method=RequestMethod.GET, params="new" )
	public	ModelAndView	getNewGame( HttpServletRequest req )
		{
		log.info( "/newGame:get" );
		
		NewGameHandler controller	= new NewGameHandler();
		
		return( controller.get( this.dbReader, req, this.msgs, "/game" ) );
		}

	@RequestMapping( value="/game", method=RequestMethod.POST )
	public	ModelAndView	postNewGame( HttpServletRequest req ) throws Exception
		{
		log.info( "/newGame:post" );
		
		NewGameHandler controller	= new NewGameHandler();
		
		return( controller.post( this.dbReader, req, this.msgs, "/game" ) );
		}
	
	/**
	 * testjig
	 */
	
	@RequestMapping( "/testjig" )
	public	ModelAndView	testjig( HttpServletRequest req )
		{
		log.info( "/testjig" );
		
		ModelAndView	mav	= new ModelAndView( "testjig" );
		return( mav );
		}
	
	@RequestMapping( "/tableTest" )
	public	ModelAndView	tableTest()
		{
		return( new ModelAndView( "tableTest" ) );
		}
	}
