package au.edu.anu.dspaceimporter.springer.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class KeywordGroup {
	private String lang;
	private String title;
	private List<String> kwds;
	
	@XmlAttribute(name="lang", namespace="")
	public String getLang() {
		return lang;
	}
	
	public void setLang(String lang) {
		this.lang = lang;
	}
	
	@XmlElement(name="title")
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@XmlElement(name="kwd")
	public List<String> getKwds() {
		return kwds;
	}
	
	public void setKwds(List<String> kwds) {
		this.kwds = kwds;
	}
}
