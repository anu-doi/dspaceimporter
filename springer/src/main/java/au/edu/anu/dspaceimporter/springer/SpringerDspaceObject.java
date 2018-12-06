package au.edu.anu.dspaceimporter.springer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.dspaceimporter.annotation.DSpaceField;
import au.edu.anu.dspaceimporter.springer.xml.Aff;
import au.edu.anu.dspaceimporter.springer.xml.Article;
import au.edu.anu.dspaceimporter.springer.xml.ArticleId;
import au.edu.anu.dspaceimporter.springer.xml.Contrib;
import au.edu.anu.dspaceimporter.springer.xml.Institution;
import au.edu.anu.dspaceimporter.springer.xml.PubDate;
import au.edu.anu.dspaceimporter.springer.xml.XRef;
import au.edu.anu.dspaceimporter.util.DSpaceObject;

public class SpringerDspaceObject implements DSpaceObject {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringerDspaceObject.class);
	
	Article article;
	
	public SpringerDspaceObject(Article article) {
		this.article = article;
	}

	@Override
	@DSpaceField("dc-title")
	public String getTitle() {
		try {
			return article.getFront().getArticleMeta().getTitleGroup().getArticleTitle();
		}
		catch (NullPointerException e) {
			LOGGER.info("article title is null");
		}
		
		return null;
	}

	@Override
	@DSpaceField("dc-source")
	public String getJournal() {
		try {
			return article.getFront().getJournalMeta().getJournalTitleGroup().getJournalTitle();
		}
		catch (NullPointerException e) {
//			LOGGER.info("article journal is null");
			// Ignore null point exceptions
		}
		
		return null;
	}

	@Override
	@DSpaceField("dc-publisher")
	public String getPublisher() {
		try {
			return article.getFront().getJournalMeta().getPublisher().getPublisherName();
		}
		catch (NullPointerException e) {
			// Ignore null point exceptions
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@DSpaceField("dc-contributor-author")
	public List<String> getAuthors() {
		try {
			List<Contrib> contributors = article.getFront().getArticleMeta().getContribGroup().getContributors();
			List<String> authors = new ArrayList<String>();
			for (Contrib contributor : contributors) {
				if ("author".equals(contributor.getType())) {
					String name = getName(contributor);
					authors.add(name);
				}
			}
			return authors;
		}
		catch (NullPointerException e) {
			// Ignore null point exceptions
		}
		
		return null;
	}

	@Override
	@DSpaceField("dc-identifier-issn")
	public String getIssn() {
		try {
			return article.getFront().getJournalMeta().getIssn();
		}
		catch (NullPointerException e) {
			// Ignore null point exceptions
		}
		return null;
	}

	@Override
	@DSpaceField("dc-identifier-isbn")
	public String getIsbn() {
		return null;
	}

	@Override
	@DSpaceField("local-identifier-doi")
	public String getDoi() {
		// TODO Auto-generated method stub
		try {
			List<ArticleId> articleIds = article.getFront().getArticleMeta().getArticleIds();
			for (ArticleId id : articleIds) {
				if ("doi".equals(id.getType())) {
					return id.getValue();
				}
			}
		}
		catch (NullPointerException e) {
			// Ignore null point exceptions
		}
		return null;
	}

	@Override
	@DSpaceField("dc-date-issued")
	public String getPublicationDate() {
		// TODO Auto-generated method stub
		try {
			List<PubDate> publicationDates = article.getFront().getArticleMeta().getPublicationDates();
			if (publicationDates.size() > 0) {
				// TODO may need to change this?
				PubDate pubDate = publicationDates.get(0);
				StringBuilder date = new StringBuilder();
				date.append(pubDate.getYear());
				if (null != pubDate.getMonth()) {
					date.append("-");
					String month = String.format("%02d", pubDate.getMonth());
//					date.append(pubDate.getMonth());
					date.append(month);
					if (null != pubDate.getDay()) {
						date.append("-");
						String day = String.format("%02d", pubDate.getDay());
						date.append(day);
//						date.append(pubDate.getDay());
					}
				}
				return date.toString();
			}
		}
		catch (NullPointerException e) {
			// Ignore null point exceptions
		}
		
		return null;
	}

	@Override
	@DSpaceField("local-contributor-affiliation")
	public List<String> getAffiliations() {
		try {
			List<Aff> affiliations = article.getFront().getArticleMeta().getContribGroup().getAffiliations();
			List<Contrib> contributors = article.getFront().getArticleMeta().getContribGroup().getContributors();
			
			Map<String, String> orgMap = new HashMap<String, String>();
			for (Aff aff : affiliations) {
				String label = aff.getLabel();
				String organisation = getOrganisation(aff);
				orgMap.put(label, organisation);
			}
			
			List<String> authorAffiliations = new ArrayList<String>();
			for (Contrib contributor : contributors) {

				if ("author".equals(contributor.getType())) {
					String name = getName(contributor);
					List<String> affiliationIds = getAffiliationsIds(contributor);
					for (String id : affiliationIds) {
						String aff = orgMap.get(id);
						String authAff = name + ", " + aff;
						authorAffiliations.add(authAff);
//						authorAffiliations.add(e)
					}
//					authorAffiliations.add(name);
				}
			}
			return authorAffiliations;
		}
		catch (NullPointerException e) {
			// Ignore null point exceptions
		}
		return null;
	}
	
	private String getOrganisation(Aff affiliation) {
		String orgname = null;
		String orgdivision = null;
		for (Institution institution : affiliation.getWrap().getInstitutions()) {
			if ("org-name".equals(institution.getType())) {
				orgname = institution.getValue();
			}
			else if ("org-division".equals(institution.getType())) {
				orgdivision = institution.getValue();
			}
		}
		if (null != orgname && null != orgdivision) {
			return orgdivision + ", " + orgname;
		}
		else if (null != orgname) {
			return orgname;
		}
		else if (null != orgdivision) {
			return orgdivision;
		}
		return null;
	}
	
	private String getName(Contrib contributor) {
		String givenName = contributor.getName().getGivenName();
		String surname = contributor.getName().getSurname();
		String name = surname + ", " + givenName;
		return name;
	}
	
	private List<String> getAffiliationsIds(Contrib contributor) {
		List<String> affiliations = new ArrayList<String>();
		
		for (XRef xref : contributor.getXref()) {
			if ("aff".equals(xref.getRefType())) {
				affiliations.add(xref.getRid());
			}
		}
		
		return affiliations;
	}

	@Override
	@DSpaceField("local-identifier-citationvolume")
	public String getVolume() {
		try {
			return article.getFront().getArticleMeta().getVolume();
		}
		catch (NullPointerException e) {
			// Ignore null point exceptions
		}
		return null;
	}

	@Override
	@DSpaceField("local-bibliographicCitation-issue")
	public String getIssue() {
		try {
			return article.getFront().getArticleMeta().getIssue();
		}
		catch (NullPointerException e) {
			// Ignore null point exceptions
		}
		return null;
	}

	@Override
	@DSpaceField("dc-rights")
	public String getRights() {
		try {
			return article.getFront().getArticleMeta().getPermissions().getLicence().getLicencep();
		}
		catch (NullPointerException e) {
			// Ignore null point exceptions
		}
		return null;
	}

	@Override
	public String getAbstract() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@DSpaceField("local-description-notes")
	public String getNotes() {
		return "Imported from Springer Nature";
	}
	
	
	
	/*
	try {
		
	}
	catch (NullPointerException e) {
		// Ignore null point exceptions
	}
	*/
	
}
