package com.dstresearch.beans;

/*
 * $Id$
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ListResourceBundle;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ServletContextAware;

/**
 *
 * Retrieves an application configuration value.
 *
 * This class searches for the named value from the following places in the designated order:
 *
 * <ol>
 * <li>In the properties file '/usr/local/etc/application.properties"</li>
 * <li>In the properties file '/WEB-INF/classes/application.properties'.</li>
 * <li>If a ServletConfig is provided, in its initialization parameters.</li>
 * <li>The ServletContext's initialization parameters.</li>
 * </ol>
 *
 * <p>
 * The first value found is the value returned. Otherwise, the default value that was passed in is returned.
 * </p>
 *
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 * @version Aug 19, 2011
 *
 */
public class ConfigurationValue implements ServletContextAware
	{
	private static Log		log		= LogFactory.getLog( ConfigurationValue.class );
	private static final String	PROP_FILE	= "/WEB-INF/classes/application.properties";
	private static final String	LOCAL_PROP_FILE	= "/usr/local/etc/application.properties";
	private static final Object[][]	emptySet	= { { "yuksnort", "" } };
	private static String		propFile	= PROP_FILE;
	private	static String		localPropFile	= LOCAL_PROP_FILE;
	private static ResourceBundle	properties	= null;
	private static ResourceBundle	localProperties	= null;
	private ServletContext		ctx		= null;
	private ServletConfig		cfg		= null;

	/**
	 *
	 * A resource bundle with nothing in it.
	 *
	 * @author Mike Hudgins <mchudgins@dstsystems.com>
	 * @version Aug 24, 2011
	 *
	 */

	public class EmptyResourceBundle extends ListResourceBundle
		{
		@Override
		protected Object[][] getContents()
			{
			return( ConfigurationValue.emptySet );
			}
		}

	/**
	 * Default Constructor
	 */

	public ConfigurationValue()
		{
		}

	/**
	 * Explicit construction using ServletContext and optional ServletConfig
	 * 
	 * @param ctx
	 * @param cfg
	 */
	public ConfigurationValue( ServletContext ctx, ServletConfig cfg )
		{
		this.ctx = ctx;
		this.cfg = cfg;
		}

        /**
	 *
	 * Loads a configuration value.
	 *
	 * @param ctx a ServletContext for the application.
	 * @param cfg a ServletConfig for the application.
	 * @param strKey the name of the configuration value.
	 * @param strDefault if a value is not found, this value is returned.
	 * @return A String for the value or <code>strDefault</code> if not found.
	 */
	protected String loadConfigurationValue( ServletContext ctx, ServletConfig cfg, String strKey, String strDefault )
		{
		String strTmp;
		String location = "application default";
		String strValue = strDefault;
		ResourceBundle prop;

		/*
		 * check for a value in the application properties files, then the servlet context, and, finally, from the
		 * application context in corresponding order of priority.
		 */

		try
			{
			prop = getLocalPropertyBundle();
			try
				{
				if ( ( prop != null ) && ( strTmp = prop.getString( strKey ) ) != null )
					{
					strValue = strTmp;
					location = localPropFile;
					return( strValue );
					}
				}
			catch ( java.util.MissingResourceException exc )
				{
				}

			if ( ( cfg != null ) && ( strTmp = cfg.getInitParameter( strKey ) ) != null )
				{
				strValue = strTmp;
				location = "servlet configuration";
				return( strValue );
				}

			if ( ( strTmp = ctx.getInitParameter( strKey ) ) != null )
				{
				strValue = strTmp;
				location = "application context";
				return( strValue );
				}

			prop = getPropertyBundle( ctx );
			try
				{
				if ( ( prop != null ) && ( strTmp = prop.getString( strKey ) ) != null )
					{
					strValue = strTmp;
					location = ConfigurationValue.propFile;
					return( strValue );
					}
				}
			catch ( java.util.MissingResourceException exc )
				{
				}
			catch ( Exception exc )
				{
				log.error( exc );
				}

			}
		finally
			{
			if ( log.isTraceEnabled() )
				log.trace( "Configuration value " + strValue + " for property " + strKey + " obtained from "
						+ location );
			}

		return( strValue );
	}

	/*
	 * load the property bundle
	 */
	private synchronized ResourceBundle getPropertyBundle( ServletContext ctx )
		{
		if ( ConfigurationValue.properties == null )
			{
			try
				{
				InputStream is = ctx.getResourceAsStream( ConfigurationValue.propFile );
				if ( is != null )
					ConfigurationValue.properties = new PropertyResourceBundle( is );
				else
					log.error( "Unable to find property file:  " + ConfigurationValue.propFile );
				}
			catch ( Exception exc )
				{
				log.warn( exc );
				}
			return( ConfigurationValue.properties );
			}

		return( ConfigurationValue.properties );
		}

	/**
	 *
	 * Load the local property bundle.
	 *
	 * @return
	 */
	private synchronized ResourceBundle getLocalPropertyBundle()
		{

		if ( ConfigurationValue.localProperties == null )
			{
			try
				{
				InputStream is = new FileInputStream( localPropFile );
				if ( is != null )
					ConfigurationValue.localProperties = new PropertyResourceBundle( is );
				}
			catch ( FileNotFoundException e )
				{
				ConfigurationValue.localProperties = new EmptyResourceBundle();
				log.info( "Unable to find local property file:  " + localPropFile );
				}
			catch ( SecurityException e )
				{
				log.info( "Unable to read local property file:  " + localPropFile );
				}
			catch ( IOException e )
				{
				log.fatal( e.getLocalizedMessage() );
				}
			}

		return( ConfigurationValue.localProperties );
		}

	/**
	 *
	 * Gets a property.
	 *
	 * @param key
	 * @param deflt
	 * @return
	 */
	public String getProperty( String key, String deflt )
		{
		return( loadConfigurationValue( this.ctx, this.cfg, key, deflt ) );
		}

	/**
	 *
	 * Gets the ServletContext.
	 *
	 * Long description. use html, etc.
	 *
	 * @return
	 */
	public ServletContext getServletContext()
		{
		return( this.ctx );
		}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.context.ServletContextAware#setServletContext(javax.servlet.ServletContext)
	 */
	public void setServletContext( ServletContext ctx )
		{
		log.trace( "setServletContext()" );
		this.ctx = ctx;
		}
	
	/**
	 * Sets the default properties filename (bundled in the war)
	 */
	public	void	setDefaultPropertiesFilename( String filename )
		{
		propFile	= filename;
		}
	
	/**
	 * Sets the local properties filename
	 * 
	 */
	public	void	setLocalPropertiesFilename( String filename )
		{
		localPropFile	= filename;
		}
	}
