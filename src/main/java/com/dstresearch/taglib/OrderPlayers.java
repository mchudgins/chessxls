/**
 * $Id$
 *
 * 
 */
package com.dstresearch.taglib;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Logger;

/**
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */
public class OrderPlayers extends TagSupport implements Serializable
	{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	log	= Logger.getLogger( OrderPlayers.class );

	private PageContext		pc	= null;
	private String			black	= null;
	private String			white	= null;
	private String			sep	= null;
	private Map< String, String>	players	= null;
	
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException
		{
		try
			{
			if ( this.players != null )
				{
				SortedSet< String >	whites	= new TreeSet< String >();
				SortedSet< String >	blacks	= new TreeSet< String >();
				
				for ( String key : this.players.keySet() )
					{
					if ( this.players.get( key ).equals( "W" ) )
						whites.add( key );
					else
						blacks.add( key );
					}
				
				StringBuffer	whiteBuf	= new StringBuffer();
				for ( String player : whites )
					{
					if ( whiteBuf.length() > 0 )
						whiteBuf.append( this.sep );
					whiteBuf.append( player );
					}
				StringBuffer	blackBuf	= new StringBuffer();
				for ( String player : blacks )
					{
					if ( blackBuf.length() > 0 )
						blackBuf.append( this.sep );
					blackBuf.append( player );
					}
				
				this.pc.setAttribute( this.white, whiteBuf.toString() );
				this.pc.setAttribute( this.black, blackBuf.toString() );
				}
			else
				{
				this.pc.getOut().write( "*** required attribute 'var' not specified ***" );
				}
			}
		catch ( IOException e )
			{
			throw new JspTagException( "An IOException occurred." );
			}

		return( SKIP_BODY );
		}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#release()
	 */
	public void release()
		{
		this.pc		= null;
		this.players	= null;
		this.black	= null;
		this.white	= null;
		this.sep	= null;
		
		super.release();
		}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#setPageContext(javax.servlet.jsp.PageContext)
	 */
	public void setPageContext( PageContext arg0 )
		{
		super.setPageContext( arg0 );
		this.pc = arg0;
		}

	/**
	 * @return the black
	 */
	public String getBlack()
		{
		return black;
		}

	/**
	 * @param black the black to set
	 */
	public void setBlack( String black )
		{
		this.black = black;
		}

	/**
	 * @return the white
	 */
	public String getWhite()
		{
		return white;
		}

	/**
	 * @param white the white to set
	 */
	public void setWhite( String white )
		{
		this.white = white;
		}

	/**
	 * @return the players
	 */
	public Map< String, String > getPlayers()
		{
		return players;
		}

	/**
	 * @param players the players to set
	 */
	public void setPlayers( Map< String, String > players )
		{
		this.players = players;
		}

	/**
	 * @return the sep
	 */
	public String getSep()
		{
		return sep;
		}

	/**
	 * @param sep the sep to set
	 */
	public void setSep( String sep )
		{
		this.sep = sep;
		}
	
	}
