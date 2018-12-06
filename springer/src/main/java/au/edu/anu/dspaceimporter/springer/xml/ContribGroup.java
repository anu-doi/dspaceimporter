package au.edu.anu.dspaceimporter.springer.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class ContribGroup {
	private List<Contrib> contributors;
	private List<Aff> affiliations;

	@XmlElement(name="contrib")
	public List<Contrib> getContributors() {
		return contributors;
	}

	public void setContributors(List<Contrib> contributors) {
		this.contributors = contributors;
	}

	@XmlElement(name="aff")
	public List<Aff> getAffiliations() {
		return affiliations;
	}

	public void setAffiliations(List<Aff> affiliations) {
		this.affiliations = affiliations;
	}
}
