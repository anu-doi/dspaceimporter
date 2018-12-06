package au.edu.anu.dspaceimporter.plos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.InputStream;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import au.edu.anu.dspaceimporter.plos.json.Docs;
import au.edu.anu.dspaceimporter.plos.json.PlosResponse;

public class PlosDSpaceObjectTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(PlosDSpaceObjectTest.class);
	
	private static PlosResponse response;
	
	@BeforeAll
	public static void setup() {
		try {
			InputStream inputStream = PlosDSpaceObjectTest.class.getClass().getResourceAsStream("/plos_example.json");
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
			JaxbAnnotationModule module = new JaxbAnnotationModule();
			objectMapper.registerModule(module);
			response = objectMapper.readValue(inputStream, PlosResponse.class);
		}
		catch (Exception e) {
			LOGGER.error("Issue extracting response to object", e);
		}
	}
	
	@Test
	public void testDoc1() {
		assertNotNull(response, "No response found");
		List<Docs> docs = response.getDocs();
		assertNotNull(docs, "docs are unexpectedly null");
		assertEquals(3, docs.size(), "Unexpected number of docs");
		Docs doc = docs.get(0);
		PlosDSpaceObject dspaceObject = new PlosDSpaceObject(doc);
		assertNotNull(dspaceObject, "Dspace object is null");
		
		assertEquals("10.1371/journal.pone.0203948", dspaceObject.getDoi());
		assertEquals("PLOS ONE", dspaceObject.getJournal(), "Unexpected journal");
		assertEquals("1932-6203", dspaceObject.getIssn(), "Unexpected e-issn");
		assertEquals("2018-09-21", dspaceObject.getPublicationDate(), "Unexpected publication date");
//		assertEquals("Research Article", dspaceObject.getArticleType(), "Unexpected article type");

		List<String> authors = dspaceObject.getAuthors();
		assertNotNull(authors, "Authors are unexpectedly null");
		assertEquals(5, authors.size(), "Unexpected number of authors");
		assertEquals("Sutherland, Tara D.", authors.get(0), "Unexpected author value");
		assertEquals("Sriskantha, Alagacone", authors.get(1), "Unexpected author value");
		assertEquals("Rapson, Trevor D.", authors.get(2), "Unexpected author value");
		assertEquals("Kaehler, Benjamin D.", authors.get(3), "Unexpected author value");
		assertEquals("Huttley, Gavin A.", authors.get(4), "Unexpected author value");
		
		assertEquals("\nMany of the challenges we currently face as an advanced society have been solved in unique ways by biological systems. One such challenge is developing strategies to avoid microbial infection. Social aculeates (wasps, bees and ants) mitigate the risk of infection to their colonies using a wide range of adaptations and mechanisms. These adaptations and mechanisms are reliant on intricate social structures and are energetically costly for the colony. It seems likely that these species must have had alternative and simpler mechanisms in place to ensure the maintenance of hygienic domicile conditions prior to the evolution of these complex behaviours. Features of the aculeate coiled-coil silk proteins are reminiscent of those of naturally occurring α-helical antimicrobial peptides (AMPs). In this study, we demonstrate that peptides derived from the aculeate silk proteins have antimicrobial activity. We reconstruct the predicted ancestral silk sequences of an aculeate ancestor that pre-dates the evolution of sociality and demonstrate that these ancestral sequences also contained peptides with antimicrobial properties. It is possible that the silks evolved as an antifouling material and facilitated the evolution of sociality. These materials serve as model materials for consideration in future biomaterial development.\n", dspaceObject.getAbstract(), "Unexpected abstract value");
		
		assertEquals("Did aculeate silk evolve as an antifouling material?", dspaceObject.getTitle(), "Unexpected title value");
		
		LOGGER.info("Finished doc 1 test");
	}
	
	@Test
	public void testDoc2() {
		assertNotNull(response, "No response found");
		List<Docs> docs = response.getDocs();
		assertNotNull(docs, "docs are unexpectedly null");
		assertEquals(3, docs.size(), "Unexpected number of docs");
		Docs doc = docs.get(1);
		PlosDSpaceObject dspaceObject = new PlosDSpaceObject(doc);
		assertNotNull(dspaceObject, "Dspace object is null");
//		assertEquals()
		assertEquals("10.1371/journal.pbio.2005642", dspaceObject.getDoi());
		assertEquals("PLOS Biology", dspaceObject.getJournal(), "Unexpected journal");
		assertEquals("1545-7885", dspaceObject.getIssn(), "Unexpected e-issn");
		assertEquals("2018-09-12", dspaceObject.getPublicationDate(), "Unexpected publication date");
//		assertEquals("Research Article", dspaceObject.getArticleType(), "Unexpected article type");

		List<String> authors = dspaceObject.getAuthors();
		assertNotNull(authors, "Authors are unexpectedly null");
		assertEquals(15, authors.size(), "Unexpected number of authors");
		assertEquals("Uboldi, Alessandro D.", authors.get(0), "Unexpected author value");
		assertEquals("Wilde, Mary-Louise", authors.get(1), "Unexpected author value");
		assertEquals("McRae, Emi A.", authors.get(2), "Unexpected author value");
		assertEquals("Stewart, Rebecca J.", authors.get(3), "Unexpected author value");
		assertEquals("Dagley, Laura F.", authors.get(4), "Unexpected author value");
		assertEquals("Yang, Luning", authors.get(5), "Unexpected author value");
		assertEquals("Katris, Nicholas J.", authors.get(6), "Unexpected author value");
		assertEquals("Hapuarachchi, Sanduni V.", authors.get(7), "Unexpected author value");
		assertEquals("Coffey, Michael J.", authors.get(8), "Unexpected author value");
		assertEquals("Lehane, Adele M.", authors.get(9), "Unexpected author value");
		assertEquals("Botte, Cyrille Y.", authors.get(10), "Unexpected author value");
		assertEquals("Waller, Ross F.", authors.get(11), "Unexpected author value");
		assertEquals("Webb, Andrew I.", authors.get(12), "Unexpected author value");
		assertEquals("McConville, Malcolm J.", authors.get(13), "Unexpected author value");
		assertEquals("Tonkin, Christopher J.", authors.get(14), "Unexpected author value");
		
		assertEquals("\nThe phylum Apicomplexa comprises a group of obligate intracellular parasites that alternate between intracellular replicating stages and actively motile extracellular forms that move through tissue. Parasite cytosolic Ca2+ signalling activates motility, but how this is switched off after invasion is complete to allow for replication to begin is not understood. Here, we show that the cyclic adenosine monophosphate (cAMP)-dependent protein kinase A catalytic subunit 1 (PKAc1) of Toxoplasma is responsible for suppression of Ca2+ signalling upon host cell invasion. We demonstrate that PKAc1 is sequestered to the parasite periphery by dual acylation of PKA regulatory subunit 1 (PKAr1). Upon genetic depletion of PKAc1 we show that newly invaded parasites exit host cells shortly thereafter, in a perforin-like protein 1 (PLP-1)-dependent fashion. Furthermore, we demonstrate that loss of PKAc1 prevents rapid down-regulation of cytosolic [Ca2+] levels shortly after invasion. We also provide evidence that loss of PKAc1 sensitises parasites to cyclic GMP (cGMP)-induced Ca2+ signalling, thus demonstrating a functional link between cAMP and these other signalling modalities. Together, this work provides a new paradigm in understanding how Toxoplasma and related apicomplexan parasites regulate infectivity.\nAuthor summary: Central to pathogenesis and infectivity of Toxoplasma and related parasites is their ability to move through tissue, invade host cells, and establish a replicative niche. Ca2+-dependent signalling pathways are important for activating motility, host cell invasion, and egress, yet how this signalling is turned off after invasion is unclear. Here, we show that a cAMP-dependent protein kinase A (PKA) is essential for rapid suppression of Ca2+ signalling upon completion of host cell invasion. Parasites lacking this kinase rapidly invoke an egress program to re-exit host cells, thus preventing the establishment of a stable infection. This finding therefore highlights the first factor required for Toxoplasma (and any related apicomplexan parasite) to switch from invasive to the replicative forms. ", dspaceObject.getAbstract(), "Unexpected abstract value");
		
		assertEquals("Protein kinase A negatively regulates Ca<sup>2+</sup> signalling in <i>Toxoplasma gondii</i>", dspaceObject.getTitle(), "Unexpected title value");
		
		LOGGER.info("Finished doc 2 test");
	}
	
	@Test
	public void testDoc3() {
		assertNotNull(response, "No response found");
		List<Docs> docs = response.getDocs();
		assertNotNull(docs, "docs are unexpectedly null");
		assertEquals(3, docs.size(), "Unexpected number of docs");
		Docs doc = docs.get(2);
		PlosDSpaceObject dspaceObject = new PlosDSpaceObject(doc);
		assertNotNull(dspaceObject, "Dspace object is null");
		assertEquals("10.1371/journal.pone.0201696", dspaceObject.getDoi());
		assertEquals("PLOS ONE", dspaceObject.getJournal(), "Unexpected journal");
		assertEquals("1932-6203", dspaceObject.getIssn(), "Unexpected e-issn");
		assertEquals("2018-09-12", dspaceObject.getPublicationDate(), "Unexpected publication date");
//		assertEquals("Research Article", dspaceObject.getArticleType(), "Unexpected article type");

		List<String> authors = dspaceObject.getAuthors();
		assertNotNull(authors, "Authors are unexpectedly null");
		assertEquals(10, authors.size(), "Unexpected number of authors");
		assertEquals("Priest, Naomi", authors.get(0), "Unexpected author value");
		assertEquals("Slopen, Natalie", authors.get(1), "Unexpected author value");
		assertEquals("Woolford, Susan", authors.get(2), "Unexpected author value");
		assertEquals("Philip, Jeny Tony", authors.get(3), "Unexpected author value");
		assertEquals("Singer, Dianne", authors.get(4), "Unexpected author value");
		assertEquals("Kauffman, Anna Daly", authors.get(5), "Unexpected author value");
		assertEquals("Mosely, Kathryn", authors.get(6), "Unexpected author value");
		assertEquals("Davis, Matthew", authors.get(7), "Unexpected author value");
		assertEquals("Ransome, Yusuf", authors.get(8), "Unexpected author value");
		assertEquals("Williams, David", authors.get(9), "Unexpected author value");
		
		assertEquals("\nThis study examined the prevalence of racial/ethnic stereotypes among White adults who work or volunteer with children, and whether stereotyping of racial/ethnic groups varied towards different age groups. Participants were 1022 White adults who volunteer and/or work with children in the United States who completed a cross-sectional, online survey. Results indicate high proportions of adults who work or volunteer with children endorsed negative stereotypes towards Blacks and other ethnic minorities. Respondents were most likely to endorse negative stereotypes towards Blacks, and least likely towards Asians (relative to Whites). Moreover, endorsement of negative stereotypes by race was moderated by target age. Stereotypes were often lower towards young children but higher towards teens.\n", dspaceObject.getAbstract(), "Unexpected abstract value");
		
		assertEquals("Stereotyping across intersections of race and age: Racial stereotyping among White adults working with children", dspaceObject.getTitle(), "Unexpected title value");
		
		LOGGER.info("Finished doc 3 test");
	}
}
