package au.edu.anu.dspaceimporter.springer.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class ArticleMeta {
	private List<ArticleId> articleIds;
	private ArticleCategories categories;
	private TitleGroup titleGroup;
	private ContribGroup contribGroup;
	private List<PubDate> publicationDates;
	private String volume;
	private String issue;
	private Permissions permissions;
	// TODO abstract?
	private List<KeywordGroup> keywordGroups;

	@XmlElement(name="article-id")
	public List<ArticleId> getArticleIds() {
		return articleIds;
	}

	public void setArticleIds(List<ArticleId> articleIds) {
		this.articleIds = articleIds;
	}

	@XmlElement(name="article-categories")
	public ArticleCategories getCategories() {
		return categories;
	}

	public void setCategories(ArticleCategories categories) {
		this.categories = categories;
	}

	@XmlElement(name="title-group")
	public TitleGroup getTitleGroup() {
		return titleGroup;
	}

	public void setTitleGroup(TitleGroup titleGroup) {
		this.titleGroup = titleGroup;
	}

	@XmlElement(name="contrib-group")
	public ContribGroup getContribGroup() {
		return contribGroup;
	}

	public void setContribGroup(ContribGroup contribGroup) {
		this.contribGroup = contribGroup;
	}

	@XmlElement(name="pub-date")
	public List<PubDate> getPublicationDates() {
		return publicationDates;
	}

	public void setPublicationDates(List<PubDate> publicationDates) {
		this.publicationDates = publicationDates;
	}

	@XmlElement(name="volume")
	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	@XmlElement(name="issue")
	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	@XmlElement(name="permissions")
	public Permissions getPermissions() {
		return permissions;
	}

	public void setPermissions(Permissions permissions) {
		this.permissions = permissions;
	}

	@XmlElement(name="kwd-group")
	public List<KeywordGroup> getKeywordGroups() {
		return keywordGroups;
	}

	public void setKeywordGroups(List<KeywordGroup> keywordGroups) {
		this.keywordGroups = keywordGroups;
	}
}
