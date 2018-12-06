package au.edu.anu.dspaceimporter.springer.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import au.edu.anu.dspaceimporter.util.DSpaceImporterConfiguration;

public class SpringerUtilTest {
	@Test
	public void test() {
		Object url = DSpaceImporterConfiguration.getProperty("springer", "springer.url");
		assertNotNull(url);
		String urlStr = (String) url;
		assertEquals("http://api.springernature.com/openaccess/jats", urlStr);

		Object apiKey = DSpaceImporterConfiguration.getProperty("springer", "springer.apikey");
		assertNotNull(apiKey);
		assertEquals("01234567890abcdef1234567890abcde", apiKey);
		
		Object institution = DSpaceImporterConfiguration.getProperty("springer", "springer.institution");
		assertNotNull(institution);
		assertEquals("Australian National University", institution);
	}
}
