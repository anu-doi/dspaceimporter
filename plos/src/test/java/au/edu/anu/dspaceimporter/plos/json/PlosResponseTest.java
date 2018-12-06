package au.edu.anu.dspaceimporter.plos.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.InputStream;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

public class PlosResponseTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(PlosResponseTest.class);
	
	@Test
	public void test() {
		ParentResponse parentResponse = null;
		
		try {
			InputStream inputStream = this.getClass().getResourceAsStream("/plos_example.json");
			if (inputStream == null) {
				LOGGER.info("Input stream is null");
			}
			ObjectMapper objectMapper = new ObjectMapper();
			JaxbAnnotationModule module = new JaxbAnnotationModule();
			objectMapper.registerModule(module);
			parentResponse = objectMapper.readValue(inputStream, ParentResponse.class);
		}
		catch (Exception e) {
			fail("Issue extracting response to object", e);
		}
		
		assertNotNull(parentResponse, "Parent response object unexpectedly null");
		
		PlosResponse response = parentResponse.getResponse();
		assertNotNull(response, "Response unexpectedly null");
		
		List<Docs> docs = response.getDocs();
		assertNotNull(docs, "Docs are unexpectedly null");
		assertEquals(3, docs.size(), "Unexpected number of docs");
		
		Docs doc1 = docs.get(0);
		assertEquals("10.1371/journal.pone.0203948", doc1.getId(), "Unexpected id");
		assertEquals("PLOS ONE", doc1.getJournal(), "Unexpected journal");
		assertEquals("1932-6203", doc1.getEissn(), "Unexpected e-issn");
		assertEquals("2018-09-21T00:00:00Z", doc1.getPublicationDate(), "Unexpected publication date");
		assertEquals("Research Article", doc1.getArticleType(), "Unexpected article type");
		
		List<String> authors = doc1.getAuthors();
		assertNotNull(authors, "Authors are unexpectedly null");
		assertEquals(5, authors.size(), "Unexpected number of authors");
		assertEquals("Tara D. Sutherland", authors.get(0), "Unexpected author value");
		assertEquals("Alagacone Sriskantha", authors.get(1), "Unexpected author value");
		assertEquals("Trevor D. Rapson", authors.get(2), "Unexpected author value");
		assertEquals("Benjamin D. Kaehler", authors.get(3), "Unexpected author value");
		assertEquals("Gavin A. Huttley", authors.get(4), "Unexpected author value");
		
		List<String> abstracts = doc1.getAbstrs();
		assertNotNull(abstracts, "Abstracts are unexpectedly null");
		assertEquals(1, abstracts.size());
		assertEquals("\nMany of the challenges we currently face as an advanced society have been solved in unique ways by biological systems. One such challenge is developing strategies to avoid microbial infection. Social aculeates (wasps, bees and ants) mitigate the risk of infection to their colonies using a wide range of adaptations and mechanisms. These adaptations and mechanisms are reliant on intricate social structures and are energetically costly for the colony. It seems likely that these species must have had alternative and simpler mechanisms in place to ensure the maintenance of hygienic domicile conditions prior to the evolution of these complex behaviours. Features of the aculeate coiled-coil silk proteins are reminiscent of those of naturally occurring α-helical antimicrobial peptides (AMPs). In this study, we demonstrate that peptides derived from the aculeate silk proteins have antimicrobial activity. We reconstruct the predicted ancestral silk sequences of an aculeate ancestor that pre-dates the evolution of sociality and demonstrate that these ancestral sequences also contained peptides with antimicrobial properties. It is possible that the silks evolved as an antifouling material and facilitated the evolution of sociality. These materials serve as model materials for consideration in future biomaterial development.\n", abstracts.get(0));
		
		assertEquals("Did aculeate silk evolve as an antifouling material?", doc1.getTitle(), "Unexpected title value");
		assertEquals(new Double(7.5340056), doc1.getScore(), "Unexpected score value");
	}
}
