/**
 * $Id$
 *
 * 
 */
package com.dstresearch.gwt.behaviours;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Widget;


/**
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 *
 */
public class SortedTable extends Widget	//Anchor
	{
	private class TableColumnWidget extends Widget
		{
		public TableColumnWidget( Element e )
			{
			super();
			assert ( e != null );
			
			// magic GWT voodoo to get an existing element on the page/dom
			// to be part of the GWT event handling system:
			
			this.setElement( e );
			this.onAttach();
			Event.setEventListener( e, this );
			TestJig.log( "columnwidget constructed." );
			}
		}
	
	private class SortedTableHandler implements ClickHandler
		{

		/* (non-Javadoc)
		 * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
		 */
		public void onClick( ClickEvent evt )
			{
			TestJig.log( "got a click event" );
			TestJig.log( "Event source:  " + evt.getSource().getClass().getName() );
			}
	
		}
	
	private	SortedTableHandler	handler	= new SortedTableHandler();
	private	TableColumnWidget	tmp;
	
	public HandlerRegistration addClickHandler( ClickHandler handler )
		{
		return addDomHandler( handler, ClickEvent.getType() );
		}

	
	public SortedTable( TableElement e )
		{
		super();
		assert ( e != null );
		
		TableRowElement	headerRow = e.getRows().getItem( 0 );
		this.tmp	= new TableColumnWidget( headerRow );

		// magic GWT voodoo to get an existing element on the page/dom
		// to be part of the GWT event handling system:
		
		this.setElement( e );
		this.onAttach();
		this.addClickHandler( handler );
		}
	}
