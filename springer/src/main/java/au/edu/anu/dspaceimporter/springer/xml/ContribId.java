package au.edu.anu.dspaceimporter.springer.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class ContribId {
	private String contribIdType;
	private String value;
	
	@XmlAttribute(name="contrib-id-type")
	public String getContribIdType() {
		return contribIdType;
	}
	
	public void setContribIdType(String contribIdType) {
		this.contribIdType = contribIdType;
	}
	
	@XmlValue
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
