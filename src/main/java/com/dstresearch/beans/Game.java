/**
 * $Id$
 *
 * 
 */
package com.dstresearch.beans;

import java.util.Date;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */
public class Game
	{
	private static final Logger	log		= Logger.getLogger( Game.class );

	private	Date			date;
	private	String			result;		// 'W', 'B', 'D'
	private	Map< String, String >	teams;
	
	
	}
