package au.edu.anu.dspaceimporter.dspace;

import java.util.Iterator;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.simmetrics.metrics.StringMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.dspaceimporter.util.DSpaceImporterConfiguration;
import au.edu.anu.dspaceimporter.util.DSpaceObject;

public class QueryDSpace {
	private static final Logger LOGGER = LoggerFactory.getLogger(QueryDSpace.class);
	private static final double METRIC_MEASURE = 0.8;
	
	public boolean checkForMatch(DSpaceObject record) {
		SolrQuery solrQuery = new SolrQuery();
		
		String title = record.getTitle();
		solrQuery.setFields("dc.title", "handle", "search.resourceid", "dc.contributor.author", "score");
		solrQuery.setQuery("dc.title:("+ClientUtils.escapeQueryChars(title)+")");
		
		String dateIssued = record.getPublicationDate();
		
		if (dateIssued != null) {
			int indexOfDash = dateIssued.indexOf("-");
			String year = null;
			if (indexOfDash > 0) {
				year = dateIssued.substring(0, indexOfDash);
			}
			else {
				year = dateIssued;
			}
			solrQuery.addFilterQuery("dateIssued.year:" + year);
		}
		
		List<String> authors = record.getAuthors();
		StringBuilder authorMatch = new StringBuilder();
		boolean isFirst = true;
		for (String author : authors) {
			int commaIndex = author.indexOf(",");
			if (commaIndex > 0) {
				author = author.substring(0, commaIndex);
			}
			if (!isFirst) {
				authorMatch.append(" AND ");
			}
			else {
				isFirst = false;
			}
			authorMatch.append("dc.contributor.author:(");
			authorMatch.append(ClientUtils.escapeQueryChars(author));
			authorMatch.append(")");
		}
		if (authorMatch.length() > 0) {
			solrQuery.addFilterQuery(authorMatch.toString());
		}
		
		LOGGER.debug("Searching solr: {}", solrQuery.toString());
		
		boolean matchFound = false;
		
		String solrServerLocation = (String) DSpaceImporterConfiguration.getProperty("dspace",  "solr.server");
		SolrServer solrServer = new HttpSolrServer(solrServerLocation);
		try {
			QueryResponse response = solrServer.query(solrQuery);
			SolrDocumentList list = response.getResults();
			Iterator<SolrDocument> it = list.iterator();
			while (it.hasNext()) {
				SolrDocument doc = it.next();
				String handle = (String) doc.get("handle");
				@SuppressWarnings("unchecked")
				List<String> solrTitles = (List<String>) doc.get("dc.title");
				if (null != solrTitles && solrTitles.size() > 0) {
					if (performMetrics(title, solrTitles.get(0))) {
						LOGGER.debug("Potential match found with the handle {}. Comparison of \"{}\"  to \"{}\"", handle, title, solrTitles.get(0));
						matchFound = true;
					}
				}
			}
		}
		catch (SolrServerException e) {
			LOGGER.error("Exception querying Solr", e);
		}
		LOGGER.debug("Match found value: {}", matchFound);
		return matchFound;
	}
	
	private boolean performMetrics(String value1, String value2) {
		float metric = StringMetrics.simonWhite().compare(value1, value2);
		if (metric > METRIC_MEASURE) {
			return true;
		}
		return false;
	}
}
