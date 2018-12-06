package au.edu.anu.dspaceimporter.plos.json;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class PlosResponse {
	private Long numFound;
	private Long start;
	private Double maxScore;
	private List<Docs> docs;

	@XmlElement(name="numFound")
	public Long getNumFound() {
		return numFound;
	}

	public void setNumFound(Long numFound) {
		this.numFound = numFound;
	}

	@XmlElement(name="start")
	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}
	
	@XmlElement(name="maxScore")
	public Double getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Double maxScore) {
		this.maxScore = maxScore;
	}

	@XmlElement(name="docs")
	public List<Docs> getDocs() {
		return docs;
	}

	public void setDocs(List<Docs> docs) {
		this.docs = docs;
	}
}
