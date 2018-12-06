package au.edu.anu.dspaceimporter.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DSpaceImporterConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(DSpaceImporterConfiguration.class);
	private static Map<String, Properties> propertiesMap = new HashMap<String, Properties>();
	
	private static Properties getProperties(String group) {
		Properties properties = propertiesMap.get(group);
		if (properties != null) {
			return properties;
		}
		String propertyFilename = group + ".properties";
		LOGGER.debug("Loading properties file named {}", propertyFilename);
		try {
			InputStream is = null;
			if (propertyFilename != null && propertyFilename.length() > 0) {
				is = DSpaceImporterConfiguration.class.getClassLoader().getResourceAsStream(propertyFilename);
			}
			properties = new Properties();
			properties.load(is);
			propertiesMap.put(group, properties);
		}
		catch (IOException e) {
			LOGGER.error("Error reading property file", e);
		}

		return properties;
	}
	
	public static final Object getProperty(String group, String name) {
		Properties properties = getProperties(group);
		if (properties != null) {
			return properties.getProperty(name);
		}
		return null;
	}
}
