package au.edu.anu.dspaceimporter.springer.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Aff {
	private String id;
	private String label;
	private InstitutionWrap wrap;

	@XmlAttribute(name="id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(name="label")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@XmlElement(name="institution-wrap")
	public InstitutionWrap getWrap() {
		return wrap;
	}

	public void setWrap(InstitutionWrap wrap) {
		this.wrap = wrap;
	}
}
