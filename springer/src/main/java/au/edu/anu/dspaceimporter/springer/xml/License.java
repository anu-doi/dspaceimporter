package au.edu.anu.dspaceimporter.springer.xml;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;

import au.edu.anu.dspaceimporter.springer.xml.handler.LicenseHandler;

public class License {
	private String type;
	private String licencep;

	@XmlAttribute(name="license-type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlAnyElement(LicenseHandler.class)
	public String getLicencep() {
		return licencep;
	}

	public void setLicencep(String licencep) {
		this.licencep = licencep;
	}
	
}
