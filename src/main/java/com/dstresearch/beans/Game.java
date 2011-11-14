/**
 * $Id$
 *
 * 
 */
package com.dstresearch.beans;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import org.apache.log4j.Logger;

/**
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */
public class Game
	{
	private static final Logger	log		= Logger.getLogger( Game.class );
	
	public	long			id;
	public	Date			date;
	public	String			result;		// 'W', 'B', 'D'
	public	Map< String, String >	teams;
	
	public	Game()
		{
		this.teams	= new TreeMap< String, String >();
		}
	
	public long getId()
		{
		return( this.id );
		}
	
	public Date getDate()
		{
		return( this.date );
		}
	
	public String getWinner()
		{
		return( this.result );
		}
	
	public Map< String, String > getPlayers()
		{
		return( this.teams );
		}
	}
