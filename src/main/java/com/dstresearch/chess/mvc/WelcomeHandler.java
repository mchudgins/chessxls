/**
 * $Id$
 *
 * 
 */
package com.dstresearch.chess.mvc;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;

/**
 * Displays the Welcome page.
 * 
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */
public class WelcomeHandler
	{
	private static final Logger	log		= Logger.getLogger( WelcomeHandler.class );

	/**
	 * Processes the GET request for the welcome page.
	 * 
	 * @param req
	 * @param msgs
	 * @return
	 */
	public	ModelAndView	get( HttpServletRequest req, MessageSource msgs )
		{
		ModelAndView	mav	= new ModelAndView( "welcome" );

		if ( req == null )
			log.warn( "ServletRequest is NULL!" );

		mav.addObject( "pageTitle", msgs.getMessage( "WelcomeHandler.pageTitle", new Object[]{}, req.getLocale() ) );
		mav.addObject( "message", msgs.getMessage( "message", new Object[] { "hello, world" }, req.getLocale() ) );
		mav.addObject( "userLocale", req.getLocale().toString() );

		mav.addObject( "requestURI", req.getRequestURI() );
		mav.addObject( "requestURL", req.getRequestURL() );

		return( mav );
		}
	}
