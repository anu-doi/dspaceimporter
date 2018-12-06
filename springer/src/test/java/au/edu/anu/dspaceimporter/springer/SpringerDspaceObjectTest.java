package au.edu.anu.dspaceimporter.springer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.dspaceimporter.annotation.DSpaceObjectParser;
import au.edu.anu.dspaceimporter.springer.xml.Article;
import au.edu.anu.dspaceimporter.springer.xml.Records;
import au.edu.anu.dspaceimporter.springer.xml.SpringerResponse;

public class SpringerDspaceObjectTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringerDspaceObjectTest.class);
	
	
	private static SpringerResponse response;
	
	@BeforeAll
	public static void setup() {
		try {
			JAXBContext context = JAXBContext.newInstance(SpringerResponse.class);
			Unmarshaller um = context.createUnmarshaller();
			InputStream is = SpringerDspaceObjectTest.class.getClass().getResourceAsStream("/example-response.xml");
			response = (SpringerResponse) um.unmarshal(is);
		}
		catch (JAXBException e) {
			LOGGER.error("Exception transforming object", e);
		}
	}
	
	@Test
	public void testArticle1() {
		assertNotNull(response, "No response found");
		Records records = response.getRecords();
		assertNotNull(records);
		List<Article> articles = records.getArticles();
		assertNotNull(articles);
		assertEquals(2, articles.size());
		Article article = articles.get(0);
		SpringerDspaceObject dspaceObject = new SpringerDspaceObject(article);
		assertNotNull(dspaceObject, "DSpace Object is null");
		assertEquals("The Prejudice towards People with Mental Illness (PPMI) scale: structure and validity", dspaceObject.getTitle(), "Unexpected title");
		assertEquals("BMC Psychiatry", dspaceObject.getJournal(), "Unexpected journal name");
		assertEquals("1471-244X", dspaceObject.getIssn(), "Unexpected issn");
		assertNull(dspaceObject.getIsbn(), "Unexpeced isbn");
		assertEquals("BioMed Central", dspaceObject.getPublisher(), "Unexpected publisher");
		//TODO authors
		List<String> authors = dspaceObject.getAuthors();
		assertNotNull(authors, "Authors were unexpectedly null");
		assertEquals(3, authors.size(), "Unexpected number of authors");
		
		assertEquals("Kenny, Amanda", authors.get(0), "Unexpected author");
		assertEquals("Bizumic, Boris", authors.get(1), "Unexpected author");
		assertEquals("Griffiths, Kathleen M.", authors.get(2), "Unexpected author");
		
		assertEquals("10.1186/s12888-018-1871-z", dspaceObject.getDoi(), "Unexpected doi");
		assertEquals("2018-09-12", dspaceObject.getPublicationDate(), "Unexpected publication date");
		
		List<String> authorAffiliations = dspaceObject.getAffiliations();
		assertNotNull(authorAffiliations, "Author affiliations were unexpectedly null");
		assertEquals(3, authorAffiliations.size(), "Unexpected number of author affiliations");
		
		assertEquals("Kenny, Amanda, Research School of Psychology, The Australian National University", authorAffiliations.get(0), "Unexpected author affiliation");
		assertEquals("Bizumic, Boris, Research School of Psychology, The Australian National University", authorAffiliations.get(1), "Unexpected author affiliation");
		assertEquals("Griffiths, Kathleen M., Research School of Psychology, The Australian National University", authorAffiliations.get(2), "Unexpected author affiliation");
		
		assertEquals("18", dspaceObject.getVolume(), "Unexpected volume");
		assertEquals("1", dspaceObject.getIssue(), "Unexpected issue");
		assertEquals("<bold>Open Access</bold>This article is distributed under the terms of the Creative Commons Attribution 4.0 International License (<ext-link ext-link-type=\"uri\" xlink:href=\"http://creativecommons.org/licenses/by/4.0/\">http://creativecommons.org/licenses/by/4.0/</ext-link>), which permits unrestricted use, distribution, and reproduction in any medium, provided you give appropriate credit to the original author(s) and the source, provide a link to the Creative Commons license, and indicate if changes were made. The Creative Commons Public Domain Dedication waiver (<ext-link ext-link-type=\"uri\" xlink:href=\"http://creativecommons.org/publicdomain/zero/1.0/\">http://creativecommons.org/publicdomain/zero/1.0/</ext-link>) applies to the data made available in this article, unless otherwise stated.", dspaceObject.getRights(), "Unexpected rights value");
		
		LOGGER.info("Finished article1 test");
	}
	
	@Test
	public void testArticle2() {
		assertNotNull(response, "No response found");
		Records records = response.getRecords();
		assertNotNull(records);
		List<Article> articles = records.getArticles();
		assertNotNull(articles);
		assertEquals(2, articles.size());
		Article article = articles.get(1);
		SpringerDspaceObject dspaceObject = new SpringerDspaceObject(article);
		assertNotNull(dspaceObject, "DSpace Object is null");

		assertEquals("Seismic imaging and petrology explain highly explosive eruptions of Merapi Volcano, Indonesia", dspaceObject.getTitle(), "Unexpected title");
		assertEquals("Scientific Reports", dspaceObject.getJournal(), "Unexpected journal name");
		assertEquals("2045-2322", dspaceObject.getIssn(), "Unexpected issn");
		assertNull(dspaceObject.getIsbn(), "Unexpeced isbn");
		assertEquals("Nature Publishing Group UK", dspaceObject.getPublisher(), "Unexpected publisher");
		//TODO authors
		List<String> authors = dspaceObject.getAuthors();
		assertNotNull(authors, "Authors were unexpectedly null");
		assertEquals(10, authors.size(), "Unexpected number of authors");
		
		assertEquals("Widiyantoro, S.", authors.get(0), "Unexpected author");
		assertEquals("Ramdhan, M.", authors.get(1), "Unexpected author");
		assertEquals("Métaxian, J.-P.", authors.get(2), "Unexpected author");
		assertEquals("Cummins, P. R.", authors.get(3), "Unexpected author");
		assertEquals("Martel, C.", authors.get(4), "Unexpected author");
		assertEquals("Erdmann, S.", authors.get(5), "Unexpected author");
		assertEquals("Nugraha, A. D.", authors.get(6), "Unexpected author");
		assertEquals("Budi-Santoso, A.", authors.get(7), "Unexpected author");
		assertEquals("Laurin, A.", authors.get(8), "Unexpected author");
		assertEquals("Fahmi, A. A.", authors.get(9), "Unexpected author");
		
		assertEquals("10.1038/s41598-018-31293-w", dspaceObject.getDoi(), "Unexpected doi");
		assertEquals("2018-09-12", dspaceObject.getPublicationDate(), "Unexpected publication date");

		List<String> authorAffiliations = dspaceObject.getAffiliations();
		assertNotNull(authorAffiliations, "Author affiliations were unexpectedly null");
		assertEquals(13, authorAffiliations.size(), "Unexpected number of author affiliations");
		
		assertEquals("Widiyantoro, S., Global Geophysics Research Group, Faculty of Mining and Petroleum Engineering, Bandung Institute of Technology", authorAffiliations.get(0), "Unexpected author");
		assertEquals("Widiyantoro, S., Research Center for Disaster Mitigation, Bandung Institute of Technology", authorAffiliations.get(1), "Unexpected author");
		assertEquals("Ramdhan, M., Study Program of Earth Sciences, Faculty of Earth Sciences and Technology, Bandung Institute of Technology", authorAffiliations.get(2), "Unexpected author");
		assertEquals("Ramdhan, M., Agency for Meteorology, Climatology and Geophysics", authorAffiliations.get(3), "Unexpected author");
		assertEquals("Métaxian, J.-P., ISTerre, IRD R219, CNRS, Université de Savoie Mont Blanc", authorAffiliations.get(4), "Unexpected author");
		assertEquals("Métaxian, J.-P., Institut de Physique du Globe de Paris, Université Sorbonne-Paris-Cité, CNRS", authorAffiliations.get(5), "Unexpected author");
		assertEquals("Cummins, P. R., Research School of Earth Sciences, Australian National University", authorAffiliations.get(6), "Unexpected author");
		assertEquals("Martel, C., Institut des Sciences de la Terre d’Orléans (ISTO), Université d’Orléans-CNRS-BRGM", authorAffiliations.get(7), "Unexpected author");
		assertEquals("Erdmann, S., Institut des Sciences de la Terre d’Orléans (ISTO), Université d’Orléans-CNRS-BRGM", authorAffiliations.get(8), "Unexpected author");
		assertEquals("Nugraha, A. D., Global Geophysics Research Group, Faculty of Mining and Petroleum Engineering, Bandung Institute of Technology", authorAffiliations.get(9), "Unexpected author");
		assertEquals("Budi-Santoso, A., Center for Volcanology and Geological Hazard Mitigation, Geological Agency", authorAffiliations.get(10), "Unexpected author");
		assertEquals("Laurin, A., ISTerre, IRD R219, CNRS, Université de Savoie Mont Blanc", authorAffiliations.get(11), "Unexpected author");
		assertEquals("Fahmi, A. A., ISTerre, IRD R219, CNRS, Université de Savoie Mont Blanc", authorAffiliations.get(12), "Unexpected author");

		assertEquals("8", dspaceObject.getVolume(), "Unexpected volume");
		assertEquals("1", dspaceObject.getIssue(), "Unexpected issue");
		assertEquals("<bold>Open Access</bold>This article is distributed under the terms of the Creative Commons Attribution 4.0 International License (<ext-link ext-link-type=\"uri\" xlink:href=\"http://creativecommons.org/licenses/by/4.0/\">http://creativecommons.org/licenses/by/4.0/</ext-link>), which permits unrestricted use, distribution, and reproduction in any medium, provided you give appropriate credit to the original author(s) and the source, provide a link to the Creative Commons license, and indicate if changes were made. The Creative Commons Public Domain Dedication waiver (<ext-link ext-link-type=\"uri\" xlink:href=\"http://creativecommons.org/publicdomain/zero/1.0/\">http://creativecommons.org/publicdomain/zero/1.0/</ext-link>) applies to the data made available in this article, unless otherwise stated.", dspaceObject.getRights(), "Unexpected rights value");
		
		LOGGER.info("Finished article2 test");
	}
	
	@Test
	public void dspaceObjectParserTest() {

		assertNotNull(response, "No response found");
		Records records = response.getRecords();
		assertNotNull(records);
		List<Article> articles = records.getArticles();
		assertNotNull(articles);
		assertEquals(2, articles.size());
		Article article = articles.get(0);

		SpringerDspaceObject dspaceObject = new SpringerDspaceObject(article);
		assertNotNull(dspaceObject, "DSpace Object is null");
		
		DSpaceObjectParser parser = new DSpaceObjectParser();
		Map<String, Set<String>> values = null;
		try {
			values = parser.getDSPaceValues(dspaceObject);
		}
		catch (InvocationTargetException | IllegalAccessException e) {
			fail("Exception parsing dspace object", e);
		}
		assertNotNull(values, "DSpace map is unexpectedly null");
		
		Set<String> titles = values.get("dc-title");
		assertNotNull(titles, "Titles are unexpectedly null");
		assertEquals(1, titles.size(), "Unexpected number of titles");
		assertEquals("The Prejudice towards People with Mental Illness (PPMI) scale: structure and validity", titles.iterator().next(), "Unexpected title");
		
		Set<String> sources = values.get("dc-source");
		assertNotNull(sources, "Sources are unexpectedly null");
		assertEquals(1, sources.size(), "Unexpected number of sources");
		assertEquals("BMC Psychiatry", sources.iterator().next(), "Unexpected journal name");
		
		Set<String> issns = values.get("dc-identifier-issn");
		assertNotNull(issns, "ISSNs unexpectedly null");
		assertEquals("1471-244X", issns.iterator().next(), "Unexpected issn");
		
		Set<String> isbns = values.get("dc-identifier-isbn");
		assertNull(isbns, "ISBNs unexpectedly not null");
		
		Set<String> publishers = values.get("dc-publisher");
		assertNotNull(publishers, "Publishers are unexpectedly null");
		assertEquals(1, publishers.size(), "Unexpected number of publishers");
		assertEquals("BioMed Central", publishers.iterator().next(), "Unexpected publisher");
		
		Set<String> authors = values.get("dc-contributor-author");
		assertNotNull(authors, "Authors are unexpectedly null");
		assertEquals(3, authors.size(), "Unexpected number of authors");
		Iterator<String> authorIterator = authors.iterator();

		assertEquals("Kenny, Amanda", authorIterator.next(), "Unexpected author");
		assertEquals("Bizumic, Boris", authorIterator.next(), "Unexpected author");
		assertEquals("Griffiths, Kathleen M.", authorIterator.next(), "Unexpected author");
		
		Set<String> dois = values.get("local-identifier-doi");
		assertNotNull(dois, "DOI value is unexpectedly null");
		assertEquals(1, dois.size(), "Unexpected number of dois");
		assertEquals("10.1186/s12888-018-1871-z", dois.iterator().next(), "Unexpected doi");
		
		Set<String> publicationDates = values.get("dc-date-issued");
		assertNotNull(publicationDates, "Publication dates is unexpectedly null");
		assertEquals(1, publicationDates.size(), "Unexpected number of publication dates");
		assertEquals("2018-09-12", publicationDates.iterator().next(), "Unexpected publication date");
		
		Set<String> affiliations = values.get("local-contributor-affiliation");
		assertNotNull(affiliations, "Affiliations unexpectedly null");
		assertEquals(3, affiliations.size());
		Iterator<String> affiliationIterator = affiliations.iterator();
		assertEquals("Kenny, Amanda, Research School of Psychology, The Australian National University", affiliationIterator.next(), "Unexpected author affiliation");
		assertEquals("Bizumic, Boris, Research School of Psychology, The Australian National University", affiliationIterator.next(), "Unexpected author affiliation");
		assertEquals("Griffiths, Kathleen M., Research School of Psychology, The Australian National University", affiliationIterator.next(), "Unexpected author affiliation");
		
		Set<String> volumes = values.get("local-identifier-citationvolume");
		assertNotNull(volumes, "Volumes unexpectedly null");
		assertEquals(1, volumes.size(), "Unexpected number of volumes");
		assertEquals("18", volumes.iterator().next(), "Unexpected volume");
		
		Set<String> issues = values.get("local-bibliographicCitation-issue");
		assertNotNull(issues, "Issues unexpectedly null");
		assertEquals(1, issues.size(), "Unexpected number of issues");
		assertEquals("1", issues.iterator().next(), "Unexpected issue");
		
		Set<String> rights = values.get("dc-rights");
		assertNotNull(rights, "Rights unexpectedly null");
		assertEquals(1, rights.size(), "Unexpected number of rights");
		assertEquals("<bold>Open Access</bold>This article is distributed under the terms of the Creative Commons Attribution 4.0 International License (<ext-link ext-link-type=\"uri\" xlink:href=\"http://creativecommons.org/licenses/by/4.0/\">http://creativecommons.org/licenses/by/4.0/</ext-link>), which permits unrestricted use, distribution, and reproduction in any medium, provided you give appropriate credit to the original author(s) and the source, provide a link to the Creative Commons license, and indicate if changes were made. The Creative Commons Public Domain Dedication waiver (<ext-link ext-link-type=\"uri\" xlink:href=\"http://creativecommons.org/publicdomain/zero/1.0/\">http://creativecommons.org/publicdomain/zero/1.0/</ext-link>) applies to the data made available in this article, unless otherwise stated.", rights.iterator().next(), "Unexpected rights value");
		
		LOGGER.info("finished dspaceObject parser test");
		
	}
}
