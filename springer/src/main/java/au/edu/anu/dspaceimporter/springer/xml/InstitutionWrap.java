package au.edu.anu.dspaceimporter.springer.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class InstitutionWrap {
	private List<InstitutionId> institutionIds;
	private List<Institution> institutions;
	
	@XmlElement(name="institution-id")
	public List<InstitutionId> getInstitutionIds() {
		return institutionIds;
	}
	
	public void setInstitutionIds(List<InstitutionId> institutionIds) {
		this.institutionIds = institutionIds;
	}
	
	@XmlElement(name="institution")
	public List<Institution> getInstitutions() {
		return institutions;
	}
	
	public void setInstitutions(List<Institution> institutions) {
		this.institutions = institutions;
	}
}
