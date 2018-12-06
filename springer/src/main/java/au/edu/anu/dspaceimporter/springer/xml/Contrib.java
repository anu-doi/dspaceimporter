package au.edu.anu.dspaceimporter.springer.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Contrib {
	private String type;
	private ContribId contribId;
	private Name name;
	private List<XRef> xref;
	
	@XmlAttribute(name="contrib-type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement(name="contrib-id")
	public ContribId getContribId() {
		return contribId;
	}

	public void setContribId(ContribId contribId) {
		this.contribId = contribId;
	}

	@XmlElement(name="name")
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	@XmlElement(name="xref")
	public List<XRef> getXref() {
		return xref;
	}

	public void setXref(List<XRef> xref) {
		this.xref = xref;
	}
}
