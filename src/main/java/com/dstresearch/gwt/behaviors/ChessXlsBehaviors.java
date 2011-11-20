/**
 * $Id$
 *
 * 
 */
package com.dstresearch.gwt.behaviors;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.user.client.ui.Widget;
import static com.google.gwt.query.client.GQuery.*;
import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.GQuery;

/**
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */
public class ChessXlsBehaviors implements EntryPoint
	{

	private	static	List< Widget > widgets	= new ArrayList< Widget >();
	
	public static native void log( String s )
	/*-{
		console.log( s );
	 }-*/;
	
	/* (non-Javadoc)
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	public void onModuleLoad()
		{
		GWT.log( "onModuleLoad" );
		Document doc	= Document.get();

		// debug only! (its faster to skip the charts)
		//$( "#chart_area" ).hide();
		
		// attach the handlers for .dropdown-menu
		GQuery	ulElmts	= $( "ul > li[ data-dropdown=\"dropdown\" ]" );
		for ( int i = 0, n = ulElmts.size(); i < n; i++ )
			{
			Element	e	= ulElmts.get( i );
			widgets.add( new DropDownMenu( e ) );
			}
		}

	}
