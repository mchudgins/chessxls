/**
 * $Id$
 *
 * 
 */
package com.dstresearch.taglib;

import java.io.IOException;
import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Logger;
import com.dstresearch.chess.mvc.PaginatorHelper;

/**
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */
public class PaginationControl extends TagSupport implements Serializable
	{
	private static final long	serialVersionUID	= 1L;
	private static final Logger	log	= Logger.getLogger( PaginationControl.class );
	
	private PageContext		pc	= null;
	private	int			page	= 0;
	private	int			items	= 0;
	private	int			total	= 0;
	private	String			url	= null;
	
	protected void printHref( JspWriter out, String url, int page )
			throws IOException
		{
		out.print( url );
		out.print( "?" );
		out.print( PaginatorHelper.PAGE_PARAM );
		out.print( "=" );
		out.print( page );
		if ( this.items != PaginatorHelper.DEFAULT_ITEMS )
			{
			out.print( "&" );
			out.print( PaginatorHelper.ITEMS_PARAM );
			out.print( "=" );
			out.print( this.items );
			}
		}
	
	protected void printListItem( JspWriter out, int page,
			String href, boolean active, boolean ellipsis, boolean preEllipsis )
			throws IOException
		{
		
		if ( active )
			out.print( "<li class=\"active\"> " );
		else
			out.print( "<li>" );
		out.print( "<a href=\"" );
		printHref( out, url, page );
		out.print( "\">" );
		if ( preEllipsis )
			out.print( "&hellip;" );
		out.print( page );
		if ( ellipsis )
			out.print( "&hellip;" );
		out.print( "</a></li>" );
		}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException
		{
		if ( this.items == 0 )
			this.items	= PaginatorHelper.DEFAULT_ITEMS;
		
		int	lastPage	= total / items;
		if ( this.total % this.items > 0 )
			lastPage++;
		
		try
			{
			JspWriter	out	= this.pc.getOut();
			
			out.print( "<div class=\"pagination\" >" );
			out.print( "<ul>" );
			out.print( "<li class=\"prev" );
			if ( this.page < 2 )
				out.write( " disabled" );
			out.print( "\"><a href=\"" );
			printHref( out, this.url, this.page - 1 );
			out.print( "\">&larr; Previous</a></li>" );

			if ( this.page > 3 )
				printListItem( out, 1, "#", false, true, false );
			
			if ( this.page < lastPage - 3 )
				for ( int i = 0; i < 3; i++ )
					{
					printListItem( out, page + i, "#",
						page + i == page, false, false );
					}
			else
				for ( int i = lastPage - 3; i <= lastPage; i++ )
					printListItem( out, i, "#",
						i == page, false, false );	
							
			if ( this.page < lastPage - 5 )
				{
				printListItem( out, lastPage - 1, "#", false, false, true );
				printListItem( out, lastPage, "#", false, false, false );
				}
			
			out.print( "</a></li>"
				+ "<li class=\"next" );
			if ( this.page == lastPage )
				out.print( " disabled" );
			out.print( "\"><a href=\"" );
			printHref( out, this.url, this.page + 1 );
			out.print( "\">Next &rarr;</a></li></ul></div> " );
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
		this.url	= null;
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
	 * @param page the page to set
	 */
	public void setPage( int page )
		{
		this.page = page;
		}

	/**
	 * @param items the items to set
	 */
	public void setItems( int items )
		{
		this.items = items;
		}

	/**
	 * @param url the url to set
	 */
	public void setUrl( String url )
		{
		this.url = url;
		}

	/**
	 * @param total the total to set
	 */
	public void setTotalItems( int total )
		{
		this.total = total;
		}

	}
