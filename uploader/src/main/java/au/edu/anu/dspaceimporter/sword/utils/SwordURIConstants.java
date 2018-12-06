package au.edu.anu.dspaceimporter.sword.utils;

import au.edu.anu.dspaceimporter.util.DSpaceImporterConfiguration;

/**
 * Constant class to retrieve appropriate URLs and values
 * 
 * @author Genevieve Turner
 *
 */
public class SwordURIConstants {
	/**
	 * The prefix of the edit url
	 */
	public static final String EDIT_PREFIX = DSpaceImporterConfiguration.getProperty("uploader", "sword.server") + "/edit/";
	
	/**
	 * The prefix of the edit media url
	 */
	public static final String EDIT_MEDIA_PREFIX = DSpaceImporterConfiguration.getProperty("uploader", "sword.server") + "/edit-media/";
	
	/**
	 * The service document url
	 */
	public static final String SERVICE_DOCUMENT = DSpaceImporterConfiguration.getProperty("uploader", "sword.server") + "/servicedocument";
	
	/**
	 * The prefix of the collection url
	 */
	public static final String COLLECTION_PREFIX = DSpaceImporterConfiguration.getProperty("uploader", "sword.server") + "/collection/";
	
	/**
	 * The collection
	 */
	public static final String DEFAULT_COLLECTION = COLLECTION_PREFIX + DSpaceImporterConfiguration.getProperty("uploader", "sword.default.collection");
}
