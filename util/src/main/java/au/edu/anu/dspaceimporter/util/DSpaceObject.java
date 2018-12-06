package au.edu.anu.dspaceimporter.util;

import java.util.List;

public interface DSpaceObject {
//	@DSpaceField("dc-title")
	public String getTitle();
	
//	@DSpaceField("dc-source")
	public String getJournal();
	
//	@DSpaceField("dc-publisher")
	public String getPublisher();
	
//	@DSpaceField("dc-contributor-author")
	public List<String> getAuthors();
	
//	@DSpaceField("dc-identifier-issn")
	public String getIssn();
	
//	@DSpaceField("dc-identifier-isbn")
	public String getIsbn();
	
//	@DSpaceField("local-identifier-doi")
	public String getDoi();
	
//	@DSpaceField("dc-date-issued")
	public String getPublicationDate();
	
//	@DSpaceField("local-contributor-affiliation")
	public List<String> getAffiliations();
	
//	@DSpaceField("local-identifier-citationvolume")
	public String getVolume();
	
//	@DSpaceField("local-bibliographicCitation-issue")
	public String getIssue();
	
//	@DSpaceField("dc-rights")
	public String getRights();

//	@DSpaceField("dc-description-abstract")
	public String getAbstract();

//	@DSpaceField("dc-description-notes")
	public String getNotes();
}
