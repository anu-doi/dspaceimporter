package au.edu.anu.dspaceimporter.springer.xml;

import javax.xml.bind.annotation.XmlElement;

public class JournalMeta {
	private JournalTitleGroup journalTitleGroup;
	private String issn;
	private Publisher publisher;

	@XmlElement(name="journal-title-group")
	public JournalTitleGroup getJournalTitleGroup() {
		return journalTitleGroup;
	}

	public void setJournalTitleGroup(JournalTitleGroup journalTitleGroup) {
		this.journalTitleGroup = journalTitleGroup;
	}

	@XmlElement(name="issn")
	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	@XmlElement(name="publisher")
	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
}
