/**
 * $Id$
 *
 * 
 */
package com.dstresearch.beans;

import java.util.List;
import org.apache.log4j.Logger;
import com.dstresearch.chess.db.DbReader;

/**
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */
public class GameForm extends Game
	{
	private static final Logger	log	= Logger.getLogger( GameForm.class );

	public List< String >	openings;
	
	public GameForm( DbReader reader )
		{
		super();
		
		this.openings	= reader.getOpenings();
		}

	public List< String > getOpenings()
		{
		return( this.openings );
		}
	}
