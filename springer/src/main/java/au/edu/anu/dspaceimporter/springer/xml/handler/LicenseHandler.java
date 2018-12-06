package au.edu.anu.dspaceimporter.springer.xml.handler;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.DomHandler;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LicenseHandler implements DomHandler<String, StreamResult> {
	private static final Logger LOGGER = LoggerFactory.getLogger(LicenseHandler.class);
	
	//TODO see if there is a better way to handle the namespaces
	private static final String LICENSE_START_TAG = "<license-p xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:mml=\"http://www.w3.org/1998/Math/MathML\">";
	private static final String LICENSE_END_TAG = "</license-p>";
	
	private StringWriter xmlWriter = new StringWriter();

	@Override
	public StreamResult createUnmarshaller(ValidationEventHandler errorHandler) {
		// TODO Auto-generated method stub
		return new StreamResult(xmlWriter);
	}

	@Override
	public String getElement(StreamResult rt) {
		// TODO Auto-generated method stub
		String xml = rt.getWriter().toString();
//		LOGGER.info("XML: {}", xml);
		
//		int startTagIndex = xml.indexOf(LICENSE_START_TAG);
//		LOGGER.info("Start tag index: {}", startTagIndex);
		int beginIndex = xml.indexOf(LICENSE_START_TAG) + LICENSE_START_TAG.length();
		
		int endIndex = xml.indexOf(LICENSE_END_TAG);
//		LOGGER.info("Start index: {}, End index: {}", beginIndex, endIndex);
//		return xml.substring(beginIndex, endIndex);
		String xmlSubstring = xml.substring(beginIndex, endIndex);
//		LOGGER.info("XML Substring: {}", xmlSubstring);
		return xmlSubstring;
//		return null;
	}

	@Override
	public Source marshal(String n, ValidationEventHandler errorHandler) {
		// TODO Auto-generated method stub
		try {
			String xml = LICENSE_START_TAG + n.trim() + LICENSE_END_TAG;
			StringReader xmlReader = new StringReader(xml);
			return new StreamSource(xmlReader);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
