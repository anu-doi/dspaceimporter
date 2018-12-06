package au.edu.anu.dspaceimporter.plos.json;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import org.apache.solr.client.solrj.beans.Field;

public class Docs {
	private String id;
	private String journal;
	private String eissn;
	private String publicationDate;
	private String articleType;
	private List<String> authors;
	private List<String> abstrs;
	private String title;
	private Double score;
	
	@XmlElement(name="id")
	@Field("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(name="journal")
	@Field("journal")
	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	@XmlElement(name="eissn")
	@Field("eissn")
	public String getEissn() {
		return eissn;
	}

	public void setEissn(String eissn) {
		this.eissn = eissn;
	}

	@XmlElement(name="publication_date")
	@Field("publication_date")
	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	@XmlElement(name="article_type")
	@Field("article_type")
	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	@XmlElement(name="author_display")
	@Field("author_display")
	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	@XmlElement(name="abstract")
	@Field("abstract")
	public List<String> getAbstrs() {
		return abstrs;
	}

	public void setAbstrs(List<String> abstrs) {
		this.abstrs = abstrs;
	}

	@XmlElement(name="title_display")
	@Field("title_display")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement(name="score")
	@Field("score")
	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
}
