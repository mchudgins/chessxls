/*
 * $Id$
 */

package com.dstresearch.beans;

import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import com.dstresearch.beans.ConfigurationValue;

/**
 *
 * Holds helpful information about the configuration settings of this application.
 *
 * @author Mike Hudgins <mchudgins@dstsystems.com>
 * @version Aug 18, 2011
 *
 */
public class AppBean
	{
	/**
	 * A singleton AppBean is stored in the application's session using the key defined by NAME.
	 *
	 * JSP scripts and application code can retrieve the AppBean use this NAME.
	 */
	public static final String	NAME		= "app";

	private static final String	BUILD_MODE	= "application.buildMode";
	private static final String	NAME_KEY	= "application.name";
	private static final String	VERSION_KEY	= "application.version";
	private static final String	SVN_REV_KEY	= "application.sourceRevision";
	private static final String	IMAGESRV_KEY	= "application.imageServer";
	private static final String	BUILD_TIMESTAMP	= "application.buildTime";
	private static final String	VERSION		= "0.1";
	private static final String	MANIFEST_NAME	= "/META-INF/MANIFEST.MF";
	private static final String	CSS_PATH	= "application.cssPath";
	private static final String	IMAGE_PATH	= "application.imagePath";
	private static final String	JS_PATH		= "application.jsPath";

	private static final String	UNKNOWN		= "unknown";

	private static final Logger	log		= Logger.getLogger( AppBean.class );

	private MessageSource		msgs;
	
	private String			version		= VERSION;
	private String			name		= "Chess-XLS";
	private String			svnBuild	= "";
	private Date			dtBuild		= null;
	private Date			dtInit		= null;
	private String			imageServer	= null;
	private String			cssPath		= null;
	private String			imagePath	= null;
	private String			jsPath		= null;

	private ConfigurationValue	cfg		= null;

	/**
	 *
	 * Gets the application's version number.
	 *
	 * @return A String containing the current version number.
	 */
	public String getVersion()
		{
		return( this.version );
		}

	/**
	 *
	 * Gets the base part of the image server's URL.
	 *
	 * @return a String containing the URL.
	 */
	public String getImageServer()
		{
		return( this.imageServer );
		}

	/**
	 *
	 * Gets the name of the application.
	 *
	 *
	 * @return a pithy name for this application.
	 */
	public String getName()
		{
		return( this.name );
		}

	/**
	 *
	 * The revision number of the application, from the revision control system.
	 *
	 * @return A String with the revision control number.
	 */
	public String getSvnRevision()
		{
		return( this.svnBuild );
		}

	/**
	 *
	 * Gets the timestamp of the application's build.
	 *
	 * @return A Date object.
	 */
	public Date getBuildTime()
		{
		return( this.dtBuild );
		}

	/**
	 *
	 * Gets the timestamp when the application was launched in the container.
	 *
	 * @return A Date object.
	 */
	public Date getInitTime()
		{
		return( this.dtInit );
		}

	/**
	 * Default constructor.
	 */
	public AppBean()
		{
		super();
		}

	/**
	 *
	 * Gets the path used to find CSS resources.
	 *
	 * @return
	 */
	public String getCssPath()
		{
		return( this.cssPath );
		}

	/**
	 *
	 * Gets the path used to find png's/jpg's resources.
	 *
	 * @return
	 */
	public String getImagePath()
		{
		return( this.imagePath );
		}

	/**
	 *
	 * Gets the path used to find Javascript resources.
	 *
	 * @return
	 */
	public String getJsPath()
		{
		return( this.jsPath );
		}
	/**
	 * Constructor used in a JEE application.
	 *
	 * Loads the configured values for the application from its ServletContext/ServletConfig.
	 *
	 * @param ctx the application's context.
	 * @param cfg the applicattion's config.
	 */
	public AppBean( ServletContext ctx, ServletConfig cfg )
		{
		super();
		}

	/**
	 *
	 * Initialization method.
	 *
	 * Call-able by the Spring Framework.
	 *
	 */
	public void init()
		{
		String strMode = this.cfg.getProperty( BUILD_MODE, UNKNOWN );
		if ( strMode.equalsIgnoreCase( "Release" ) )
			this.version = this.cfg.getProperty( VERSION_KEY, VERSION );
		else
			{
			this.version = this.cfg.getProperty( VERSION_KEY, VERSION );
			this.name = this.cfg.getProperty( NAME_KEY, NAME );
			this.svnBuild = this.cfg.getProperty( SVN_REV_KEY, UNKNOWN );
			String strTime = this.cfg.getProperty( BUILD_TIMESTAMP, UNKNOWN );
			this.dtBuild = new Date( Long.decode( strTime ) );
			}
		this.dtInit = new Date( System.currentTimeMillis() );
		this.imageServer = this.cfg.getProperty( IMAGESRV_KEY, "https://spins.dstinput.com/images" );
		this.cssPath = this.cfg.getProperty( CSS_PATH, "/css" );
		this.imagePath = this.cfg.getProperty( IMAGE_PATH, "/images" );
		this.jsPath = this.cfg.getProperty( JS_PATH, "/js" );

		log.info( getName() + " (version " + getVersion() + ") started." );
		log.info( "css URL's point to " + this.cssPath );
		log.info( "image URL's point to " + this.imagePath );

		ServletContext ctx = this.cfg.getServletContext();
		if ( ctx != null )
			ctx.setAttribute( NAME, this );
		}

	/**
	 * Set's the bean's configuration source.
	 *
	 * Used by Spring to inject the configuation source.
	 */

	public void setConfigurationSource( ConfigurationValue cfg )
		{
		this.cfg = cfg;
		}

	/**
	 * Set's the bean's message source.
	 *
	 * Used by Spring to inject the message source.
	 */

	public void setMessageSource( MessageSource msgs )
		{
		this.msgs	= msgs;
		}
	
	/**
	 * Gets the bean's message source.
	 */
	
	public MessageSource getMessageSource()
		{
		return( this.msgs );
		}
	}
