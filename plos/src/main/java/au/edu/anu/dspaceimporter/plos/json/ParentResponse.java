package au.edu.anu.dspaceimporter.plos.json;

import javax.xml.bind.annotation.XmlElement;

public class ParentResponse {
	private PlosResponse response;

	@XmlElement(name="response")
	public PlosResponse getResponse() {
		return response;
	}

	public void setResponse(PlosResponse response) {
		this.response = response;
	}
}
