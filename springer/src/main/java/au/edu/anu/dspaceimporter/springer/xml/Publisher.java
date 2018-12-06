package au.edu.anu.dspaceimporter.springer.xml;

import javax.xml.bind.annotation.XmlElement;

public class Publisher {
	private String publisherName;
	private String publisherLocation;
	
	@XmlElement(name="publisher-name")
	public String getPublisherName() {
		return publisherName;
	}
	
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	
	@XmlElement(name="publisher-loc")
	public String getPublisherLocation() {
		return publisherLocation;
	}
	
	public void setPublisherLocation(String publisherLocation) {
		this.publisherLocation = publisherLocation;
	}
}
