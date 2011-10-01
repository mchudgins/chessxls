/**
 * $Id$
 *
 * 
 */
package com.dstresearch.beans;

import org.apache.log4j.Logger;

/**
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */
public class UserSummary
	{
	private static final Logger	log		= Logger.getLogger( UserSummary.class );

	private	String	player;
	private	long	games;
	private	long	wins;
	private	long	draws;
	private	long	winStreak;
	private	long	lossStreak;
	private	long	currentStreak;
	private	String	last10;
	
	public	UserSummary( String player, long games, long wins, long draws, long winStreak,
			long lossStreak, long currentStreak, String last10 )
		{
		this.player	= player;
		this.games	= games;
		this.wins	= wins;
		this.draws	= draws;
		this.winStreak	= winStreak;
		this.lossStreak	= lossStreak;
		this.currentStreak = currentStreak;
		this.last10	= last10;
		}
	
	public	String	getPlayer()
		{
		return( this.player );
		}
	
	public	long	getGames()
		{
		return( this.games );
		}

	public	long	getWins()
		{
		return( this.wins );
		}
	
	public	long	getDraws()
		{
		return( this.draws );
		}
	
	public	long	getLosses()
		{
		return( this.games - this.wins - this.draws );
		}
	
	public	double	getWinPercentage()
		{
		return( (double) ( this.wins * 100 ) / (double) this.games );
		}
	
	public	double	getDrawPercentage()
		{
		return( (double) ( this.draws * 100 ) / (double) this.games );
		}
	
	public	long	getLongestWinStreak()
		{
		return( this.winStreak );
		}
	
	public	long	getLongestLossStreak()
		{
		return( this.lossStreak );
		}
	
	public	String	getCurrentStreak()
		{
		if ( this.currentStreak > 0 )
			return( "W" + new Long( this.currentStreak ).toString() );
		if ( this.currentStreak == 0 )
			return( "-" );
		if ( this.currentStreak < 0 )
			return( "L" + new Long( this.currentStreak * -1 ).toString() );
		
		return( "?" );
		}
	
	public	String	getLast10()
		{
		return( this.last10 );
		}
	
	public	String	toString()
		{
		return( this.games + "/" + this.wins + "/" + this.draws + "/" + this.getLosses() + "/"
				+ this.getLongestWinStreak() + "/" + this.getLongestLossStreak() );
		}
	}
