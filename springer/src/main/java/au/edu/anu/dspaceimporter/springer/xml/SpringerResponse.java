package au.edu.anu.dspaceimporter.springer.xml;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * Example query: http://api.springernature.com/openaccess/jats?api_key=<api key>&q=orgname:%22Australian%20National%20University%22+AND+date:2018-09-12
 * 
 * Example json (not using this class: http://api.springernature.com/openaccess/json?api_key=<api key>&q=orgname:%22Australian%20National%20University%22+AND+year:2018
 * 
 * @author u5125986
 *
 */
@XmlRootElement(name="response")
public class SpringerResponse {
	private String apiMessage;
	private String query;
	private String apiKey;
	private Result result;
	private Records records;
	
	public String getApiKey() {
		return apiKey;
	}
	
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(String apiMessage) {
		this.apiMessage = apiMessage;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Records getRecords() {
		return records;
	}

	public void setRecords(Records records) {
		this.records = records;
	}
	
}
