/**
 * $Id$
 *
 * 
 */
package com.dstresearch.beans;

import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.springframework.web.context.ServletContextAware;


/**
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */
public class ContextParam implements ServletContextAware
	{
	private static final Logger	log		= Logger.getLogger( ContextParam.class );
	private ServletContext		ctx		= null;
	private	String			key;
	
	public	void	setKey( String value )
		{
		this.key	= value;
		}
	
	public	String	getValue()
		{
		return( this.ctx.getInitParameter( this.key ) );
		}

	/* (non-Javadoc)
	 * @see org.springframework.web.context.ServletContextAware#setServletContext(javax.servlet.ServletContext)
	 */
	public void setServletContext( ServletContext newContext )
		{
		this.ctx	= newContext;
		}
	}
