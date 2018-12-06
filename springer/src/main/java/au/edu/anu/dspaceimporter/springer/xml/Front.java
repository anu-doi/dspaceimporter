package au.edu.anu.dspaceimporter.springer.xml;

import javax.xml.bind.annotation.XmlElement;

public class Front {
	private JournalMeta journalMeta;
	private ArticleMeta articleMeta;

	@XmlElement(name="journal-meta")
	public JournalMeta getJournalMeta() {
		return journalMeta;
	}

	public void setJournalMeta(JournalMeta journalMeta) {
		this.journalMeta = journalMeta;
	}

	@XmlElement(name="article-meta")
	public ArticleMeta getArticleMeta() {
		return articleMeta;
	}

	public void setArticleMeta(ArticleMeta articleMeta) {
		this.articleMeta = articleMeta;
	}
}
