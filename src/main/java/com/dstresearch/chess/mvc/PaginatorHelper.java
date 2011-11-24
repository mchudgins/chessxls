/**
 * $Id$
 *
 * 
 */
package com.dstresearch.chess.mvc;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */
public class PaginatorHelper
	{
	private static final Logger	log	= Logger.getLogger( PaginatorHelper.class );
	
	public	static final String	PAGE_PARAM	= "page";
	public	static final String	ITEMS_PARAM	= "items";
	public	static final int	DEFAULT_ITEMS	= 10;

	private	int	pageNum;
	private	int	itemsNum;
	
	protected int	parseInt( String param, int defaultValue )
		{
		int	result;
		
		try
			{
			if ( param != null )
				result	= Integer.parseInt( param );
			else
				{
				result	= defaultValue;
				}
			}
		catch ( NumberFormatException excNum )
			{
			result		= defaultValue;
			}
		return( result );
		}
	
	public PaginatorHelper( HttpServletRequest req )
		{
	
		String	page		= req.getParameter( PAGE_PARAM );
		String	itemsPerPage	= req.getParameter( ITEMS_PARAM );

		this.pageNum		= parseInt( page, 1 );
		this.itemsNum		= parseInt( itemsPerPage, DEFAULT_ITEMS );
		}

	/**
	 * @return the pageNum
	 */
	public int getPageNum()
		{
		return pageNum;
		}

	/**
	 * @return the itemsNum
	 */
	public int getItemsPerPage()
		{
		return itemsNum;
		}
	
	}
