package au.edu.anu.dspaceimporter.springer.xml;

import javax.xml.bind.annotation.XmlElement;

public class Permissions {
	private String copyrightStatement;
	private String copyrightYear;
	private License licence;
	
	@XmlElement(name="copyright-statement")
	public String getCopyrightStatement() {
		return copyrightStatement;
	}
	
	public void setCopyrightStatement(String copyrightStatement) {
		this.copyrightStatement = copyrightStatement;
	}
	
	@XmlElement(name="copyright-year")
	public String getCopyrightYear() {
		return copyrightYear;
	}
	
	public void setCopyrightYear(String copyrightYear) {
		this.copyrightYear = copyrightYear;
	}
	
	@XmlElement(name="license")
	public License getLicence() {
		return licence;
	}
	
	public void setLicence(License licence) {
		this.licence = licence;
	}
}
