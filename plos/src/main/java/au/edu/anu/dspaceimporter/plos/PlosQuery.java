package au.edu.anu.dspaceimporter.plos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.dspaceimporter.plos.json.Docs;
import au.edu.anu.dspaceimporter.plos.json.ParentResponse;
import au.edu.anu.dspaceimporter.util.DSpaceImporterConfiguration;

public class PlosQuery {
	private static final Logger LOGGER = LoggerFactory.getLogger(PlosQuery.class);
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T00:00:00Z'");

	public List<Docs> getDocs(Date earliestDate, Date latestDate) throws SolrServerException {
		return getDocs(null, earliestDate, latestDate);
	}
	
	public List<Docs> getDocs(Integer rows, Date earliestDate, Date latestDate) throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		
		String institution = (String) DSpaceImporterConfiguration.getProperty("plos", "plos.institution");
		
		StringBuilder query = new StringBuilder();
		query.append("affiliate:\"");
		query.append(institution);
		query.append("\"");
		if (null != earliestDate && null != latestDate) {
			String startDate = sdf.format(earliestDate);
			String endDate = sdf.format(latestDate);
			query.append(" AND publication_date:[");
			query.append(startDate);
			query.append(" TO ");
			query.append(endDate);
			query.append("]");
		}
		else if (null != earliestDate) {
			query.append(" AND publication_date:");
			String date = sdf.format(earliestDate);
			query.append(date);
		}
		solrQuery.setQuery(query.toString());
		
		String solrServerUrl = (String) DSpaceImporterConfiguration.getProperty("plos", "plos.url");
		String apiKey = (String) DSpaceImporterConfiguration.getProperty("plos", "plos.apiKey");
		
		Client client = ClientBuilder.newClient();
		client.register(JacksonFeature.class);
		WebTarget target = client.target(solrServerUrl).queryParam("api_key", apiKey).queryParam("q", query.toString()).queryParam("wt", "json");
		LOGGER.info("Retrieving records from: {}", target.getUri());
		
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		
		if (200 == response.getStatus()) {
			ParentResponse plosResponse = response.readEntity(ParentResponse.class);
			if (null != plosResponse) {
				return plosResponse.getResponse().getDocs();
			}
		}
		return null;
	}
}
