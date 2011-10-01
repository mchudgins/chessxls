/**
 * $Id$
 *
 * 
 */
package com.dstresearch.gwt.behaviours;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */

public class TestJig implements EntryPoint
	{
	private	static	Widget	table;
	
	public static native void log( String s )
	/*-{
		console.log( s );
	 }-*/;
	
	public static void pageSetup( String s )
		{
		log( "in pageSetup." );
		
		TableElement	gorf	= TableElement.as( Document.get().getElementById( s ) );
		Element		fubar	= Document.get().getElementById( "fubar" );
		
		fubar.setInnerHTML( "<p>Hello, world</p>" );
		fubar.setAttribute( "style", "color: blue;" );
		
		//table	= new SortedTable( gorf );
		}

	public static native void exportMethods()
		/*-{
		$wnd.pageSetup	= $entry( @com.dstresearch.gwt.behaviours.TestJig::pageSetup(Ljava/lang/String;));
		}-*/;
	
	/**
	 * main entry point for the javascript running on the browser.
	 */

	public void onModuleLoad()
		{
		log( "onModuleLoad." );
		exportMethods();
		}
	}

