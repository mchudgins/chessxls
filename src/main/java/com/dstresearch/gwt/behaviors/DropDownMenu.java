/**
 * $Id$
 *
 * 
 */
package com.dstresearch.gwt.behaviors;

import org.apache.log4j.Logger;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */
public class DropDownMenu extends Widget
	{
	private	OnMouseOutHandler	hMouseOut	= new OnMouseOutHandler();
	private	ClickHandler		hClick		= null;
	private	Element			dropdown	= null;
	
	private class OnMouseOutHandler implements MouseOutHandler
		{

		/* (non-Javadoc)
		 * @see com.google.gwt.event.dom.client.MouseOutHandler#onMouseOut(com.google.gwt.event.dom.client.MouseOutEvent)
		 */
		public void onMouseOut( MouseOutEvent arg0 )
			{
			GWT.log( "onMouseOut" );
			if ( dropdown != null )
				{
				dropdown.getStyle().setDisplay( Display.NONE );
				dropdown	= null;
				}
			}
	
		}
	
	private class DropDownClickHandler implements ClickHandler
		{
		private	Element	e		= null;
		private	Boolean	isListDisplayed	= false;
		
		public DropDownClickHandler( Element target )
			{
			super();
			
			this.e	= target;
			}
	
		/* (non-Javadoc)
		 * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
		 */
		public void onClick( ClickEvent evt )
			{
			GWT.log( "got a click event" );
			GWT.log( "Event source:  " + evt.getSource().getClass().getName() );
			
			if ( ! this.isListDisplayed )
				evt.preventDefault();
			
			if ( this.e != null )
				{
				GWT.log( e.getInnerHTML() );
				GWT.log( e.getChildCount() + " children." );
				
				NodeList< Element > uls	= this.e.getElementsByTagName( "ul" );
				GWT.log( uls.getLength() + " children <ul>'s" );
				for ( int i = 0, n = uls.getLength(); i < n; i++ )
					{
					Element c	= uls.getItem( i );
					
					if ( c.getClassName().contains( "dropdown-menu" ) )
						{
						GWT.log( "found it." );
						GWT.log( c.getInnerHTML() );
						this.isListDisplayed	= true;
						dropdown		= c;
						dropdown.getStyle().setDisplay( Display.BLOCK );
						return;
						}
					}
				
				dropdown	= null;
				}
			}
	
		}

	public DropDownMenu( Element e )
		{
		super();
		GWT.log( "constructing DropDownElement" );
		assert ( e != null );
		
		// magic GWT voodoo to get an existing element on the page/dom
		// to be part of the GWT event handling system:
		
		this.setElement( e );
		this.onAttach();
		
		GWT.log(  e.getInnerHTML() );
		
		
		this.hClick	= new DropDownClickHandler( e );
		
		addDomHandler( this.hMouseOut, MouseOutEvent.getType() );
		addDomHandler( this.hClick, ClickEvent.getType() );
		}

	}
