package au.edu.anu.dspaceimporter.springer.xml;

import javax.xml.bind.annotation.XmlElement;

public class JournalTitleGroup {
	private String journalTitle;

	@XmlElement(name="journal-title")
	public String getJournalTitle() {
		return journalTitle;
	}

	public void setJournalTitle(String journalTitle) {
		this.journalTitle = journalTitle;
	}
	
}
