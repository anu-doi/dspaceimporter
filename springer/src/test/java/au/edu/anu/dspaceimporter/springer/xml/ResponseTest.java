package au.edu.anu.dspaceimporter.springer.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseTest.class);
	
	@Test
	void test() {
		LOGGER.info("Start test");
		SpringerResponse response = null;
		try {
			JAXBContext context = JAXBContext.newInstance(SpringerResponse.class);
			Unmarshaller um = context.createUnmarshaller();
			InputStream inputstream = this.getClass().getResourceAsStream("/example-response.xml");
			response = (SpringerResponse) um.unmarshal(inputstream);
		}
		catch (Exception e) {
			fail("Issue extracting response to object", e);
		}
		
		assertEquals("This XML was provided by Springer Nature", response.getApiMessage(), "Unexpected api message");
		assertEquals("3d944a7299e3cfcd4b48c05ae438585d", response.getApiKey());
		Records record = response.getRecords();
		List<Article> articles = record.getArticles();
		assertEquals(2, articles.size());
		Article article1 = articles.get(0);
		Front front = article1.getFront();
		assertNotNull(front, "front is null");
		JournalMeta journalMeta = front.getJournalMeta();
		assertNotNull(journalMeta, "journal-meta is null");
		JournalTitleGroup journalTitleGroup = journalMeta.getJournalTitleGroup();
		assertNotNull(journalTitleGroup,"journal-title-group is null");
		assertEquals("BMC Psychiatry", journalTitleGroup.getJournalTitle(), "Unexpected journal title");
		assertEquals("1471-244X", journalMeta.getIssn(), "No issn found");
//		Article article2 = articles.get(1);
		Publisher publisher = journalMeta.getPublisher();
		assertNotNull(publisher);
		assertEquals("BioMed Central", publisher.getPublisherName());
		assertEquals("London", publisher.getPublisherLocation());
//		Articleme
		ArticleMeta articleMeta = front.getArticleMeta();
		assertNotNull(articleMeta);
		List<ArticleId> articleIds = articleMeta.getArticleIds();
		assertNotNull(articleIds);
		assertEquals(3, articleIds.size());
		
		ArticleId articleId1 = articleIds.get(0);
		assertEquals("publisher-id", articleId1.getType());
		assertEquals("s12888-018-1871-z", articleId1.getValue());
		
		ArticleId articleId2 = articleIds.get(1);
		assertEquals("manuscript", articleId2.getType());
		assertEquals("1871", articleId2.getValue());
		
		ArticleId articleId3 = articleIds.get(2);
		assertEquals("doi", articleId3.getType());
		assertEquals("10.1186/s12888-018-1871-z", articleId3.getValue());
		
		TitleGroup titleGroup = articleMeta.getTitleGroup();
		assertNotNull(titleGroup, "Article title is null");
		//TODO
		String title = titleGroup.getArticleTitle();
		assertEquals("The Prejudice towards People with Mental Illness (PPMI) scale: structure and validity", title, "Unexpected article title");
		
		ContribGroup contribGroup = articleMeta.getContribGroup();
		assertNotNull(contribGroup, "contrib-group is null");
		
		List<Contrib> contributors = contribGroup.getContributors();
		assertNotNull(contributors, "No contributors found for contrib group");
		assertEquals(3, contributors.size(), "Unexpected number of contributos for contrib group");
		
		Contrib contributor1 = contributors.get(1);
		ContribId contribId = contributor1.getContribId();
		assertNotNull(contribId, "Contrib id is null");
		assertEquals("orcid", contribId.getContribIdType());
		assertEquals("http://orcid.org/0000-0003-2574-4893", contribId.getValue());
		
		Name name1 = contributor1.getName();
		assertNotNull(name1, "No name found");
		assertEquals("Bizumic", name1.getSurname());
		assertEquals("Boris", name1.getGivenName());
		
		List<XRef> xrefs = contributor1.getXref();
		assertNotNull(xrefs, "No xrefs found");
		assertEquals(2, xrefs.size(), "Unexpected number of xrefs");
//		XRef
//		assertEquals(expected, actual);
		XRef xref1 = xrefs.get(0);
		assertEquals("aff", xref1.getRefType(), "Unexpected xref type");
		assertEquals("Aff1", xref1.getRid());
		assertEquals("1", xref1.getValue());
		
		XRef xref2 = xrefs.get(1);
		assertEquals("corresp", xref2.getRefType(), "Unexpected xref type");
		assertEquals("cor2", xref2.getRid());
		assertEquals("b", xref2.getValue());
		
		List<Aff> affiliations = contribGroup.getAffiliations();
		assertNotNull(affiliations, "Affiliations are null");
		assertEquals(1, affiliations.size());
		
		InstitutionWrap wrap = affiliations.get(0).getWrap();
		assertNotNull(wrap, "Institution wrap is null");
		
		List<InstitutionId> institutionIds = wrap.getInstitutionIds();
		assertNotNull(institutionIds, "No institutionIds found");
		assertEquals(2, institutionIds.size());
		
		InstitutionId insId1 = institutionIds.get(0);
		assertEquals("ISNI", insId1.getType());
		assertEquals("0000 0001 2180 7477", insId1.getValue());
		
		List<Institution> institutions = wrap.getInstitutions();
		assertNotNull(institutions, "No institutions found");
		assertEquals(2, institutions.size(), "Unexpected number of institutions");
		Institution ins1 = institutions.get(0);
		assertEquals("org-division", ins1.getType());
		assertEquals("Research School of Psychology", ins1.getValue());

		Institution ins2 = institutions.get(1);
		assertEquals("org-name", ins2.getType());
		assertEquals("The Australian National University", ins2.getValue());
		
		List<PubDate> publicationDates = articleMeta.getPublicationDates();
		assertNotNull(publicationDates, "No publication dates found");
		assertEquals(2, publicationDates.size());
		
		PubDate date1 = articleMeta.getPublicationDates().get(0);
		assertEquals(12, date1.getDay().intValue(), "Unexpected publication day");
		assertEquals(9, date1.getMonth().intValue(), "Unexpected publication month");
		assertEquals(2018, date1.getYear().intValue(), "Unexpected publication year");
		
		PubDate date2 = articleMeta.getPublicationDates().get(1);
		assertNull(date2.getDay(), "Unexpected publication day");
		assertEquals(12, date2.getMonth().intValue(), "Unexpected publication month");
		assertEquals(2018, date2.getYear().intValue(), "Unexpected publication year");
		
		Permissions permissions = articleMeta.getPermissions();
		assertNotNull(permissions, "Permissions is null");
		assertEquals("© The Author(s). 2018", permissions.getCopyrightStatement());
		assertEquals("2018", permissions.getCopyrightYear());
		License license = permissions.getLicence();
		assertNotNull(license);
//		LicenseP licencep = license.getLicensep();
//		assertNotNull(licencep);
//		assertEquals("<bold>Open Access</bold>This article is distributed under the terms of the Creative Commons Attribution 4.0 International License (<ext-link ext-link-type=\"uri\" xlink:href=\"http://creativecommons.org/licenses/by/4.0/\">http://creativecommons.org/licenses/by/4.0/</ext-link>), which permits unrestricted use, distribution, and reproduction in any medium, provided you give appropriate credit to the original author(s) and the source, provide a link to the Creative Commons license, and indicate if changes were made. The Creative Commons Public Domain Dedication waiver (<ext-link ext-link-type=\"uri\" xlink:href=\"http://creativecommons.org/publicdomain/zero/1.0/\">http://creativecommons.org/publicdomain/zero/1.0/</ext-link>) applies to the data made available in this article, unless otherwise stated.", licencep.getValue(), "unexpected licence-p value");
//		String licensep = license.getLicencep();
		assertEquals("<bold>Open Access</bold>This article is distributed under the terms of the Creative Commons Attribution 4.0 International License (<ext-link ext-link-type=\"uri\" xlink:href=\"http://creativecommons.org/licenses/by/4.0/\">http://creativecommons.org/licenses/by/4.0/</ext-link>), which permits unrestricted use, distribution, and reproduction in any medium, provided you give appropriate credit to the original author(s) and the source, provide a link to the Creative Commons license, and indicate if changes were made. The Creative Commons Public Domain Dedication waiver (<ext-link ext-link-type=\"uri\" xlink:href=\"http://creativecommons.org/publicdomain/zero/1.0/\">http://creativecommons.org/publicdomain/zero/1.0/</ext-link>) applies to the data made available in this article, unless otherwise stated.", license.getLicencep(), "unexpected licence-p value");
		
		List<KeywordGroup> keywordGroups = articleMeta.getKeywordGroups();
		assertNotNull(keywordGroups, "Keyword groups is null");
		assertEquals(1, keywordGroups.size(), "Unexpected number of keyword groups");
		KeywordGroup group1 = keywordGroups.get(0);
		assertEquals("Keywords", group1.getTitle());
		List<String> keywords = group1.getKwds();
		assertNotNull(keywords, "Keywords are null");
		assertEquals(5, keywords.size());
		assertEquals("Prejudice", keywords.get(0));
		assertEquals("Mental illness", keywords.get(1));
		assertEquals("Attitudes", keywords.get(2));
		assertEquals("Stigma", keywords.get(3));
		assertEquals("Scale development", keywords.get(4));
		
//		assertEquals("kwd");
		
		LOGGER.info("End test");
	}
}
