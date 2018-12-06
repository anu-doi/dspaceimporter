package au.edu.anu.dspaceimporter.springer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.dspaceimporter.exception.UploaderException;
import au.edu.anu.dspaceimporter.springer.xml.Records;
import au.edu.anu.dspaceimporter.springer.xml.SpringerResponse;
import au.edu.anu.dspaceimporter.util.DSpaceImporterConfiguration;

public class SpringerQuery {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringerQuery.class);
	
	public Records getRecords() throws JAXBException, ParseException, UploaderException {
		return getRecords(null, null, null);
	}
	
	public Records getRecords(String earliestDate, String latestDate) throws JAXBException, ParseException, UploaderException {
		return getRecords(10, earliestDate, latestDate);
	}
	
	public Records getRecords(Integer rows, String earliestDate, String latestDate) throws JAXBException, ParseException
			, UploaderException {
		return getRecords(0, rows, earliestDate, latestDate);
	}
	
	public Records getRecords(Integer Start, Integer rows, String earliestDate, String latestDate) throws JAXBException
			, ParseException, UploaderException {
		String url = (String)DSpaceImporterConfiguration.getProperty("springer", "springer.url");
		String apiKey = (String) DSpaceImporterConfiguration.getProperty("springer", "springer.apikey");
		if (null == url || null == apiKey) {
			LOGGER.error("Properties incorrectly set, unable to retrieve records.");
			return null;
		}
		String query = generateQuery(earliestDate, latestDate);
		LOGGER.debug("Query is: {}", query);
		//TODO query by year potentially?
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url).queryParam("api_key", apiKey).queryParam("q", query);
		LOGGER.info("Sending query: {}", target.getUri());
		
		Response response = target.request(MediaType.APPLICATION_XML).get();
		LOGGER.info("Response status: {}", response.getStatus());
		if (200 == response.getStatus()) {
			SpringerResponse springerResponse = response.readEntity(SpringerResponse.class);
			if (null != springerResponse ) {
				LOGGER.info("Total Number: {}, Records displayed: {}", springerResponse.getResult().getTotal(), springerResponse.getResult().getRecordsDisplayed());
				return springerResponse.getRecords();
			}
		}
		return null;
	}
	
	private String generateQuery(String earliestDate, String latestDate) throws ParseException, UploaderException {
		String institution = (String) DSpaceImporterConfiguration.getProperty("springer", "springer.institution");
		if (null == institution) {
			LOGGER.error("Properties incorrectly set, unable to retrieve records.");
			return null;
		}
		StringBuilder query = new StringBuilder();
		query.append("orgname:\"");
		query.append(institution);
		query.append("\"");
		if (null != earliestDate) {
			LOGGER.debug("Earliest date is not null");
			if (latestDate != null) {
				LOGGER.debug("Latest date is not null");
				LOGGER.info("Retrieving data from {} to {}", earliestDate, latestDate);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date earlier = sdf.parse(earliestDate);
				Date later = sdf.parse(latestDate);
				if (!later.after(earlier)) {
					throw new UploaderException("Earliest date is after latest date");
				}
				Calendar startDate = Calendar.getInstance();
				startDate.setTime(earlier);
				Calendar endDate = Calendar.getInstance();
				endDate.setTime(later);
				boolean first = true;
				while (startDate.before(endDate)) {
					String date = sdf.format(startDate.getTime());
					LOGGER.debug("Date to add date: {}", date);
					if (first) {
						first = false;
						query.append(" AND (date:");
					}
					else {
						query.append(" OR date:");
					}
					query.append(date);
					startDate.add(Calendar.DATE, 1);
				}
				query.append(")");
			}
			else {
				LOGGER.debug("earliest date: {}", earliestDate);
				query.append(" AND date:");
				query.append(earliestDate);
			}
		}
		return query.toString();
	}
}
