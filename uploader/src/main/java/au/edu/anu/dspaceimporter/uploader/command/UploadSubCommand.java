package au.edu.anu.dspaceimporter.uploader.command;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.swordapp.client.ClientConfiguration;
import org.swordapp.client.SWORDClient;

import au.edu.anu.dspaceimporter.annotation.DSpaceObjectParser;
import au.edu.anu.dspaceimporter.dspace.QueryDSpace;
import au.edu.anu.dspaceimporter.sword.SwordProcessor;
import au.edu.anu.dspaceimporter.sword.SwordServerInfo;
import au.edu.anu.dspaceimporter.sword.data.BitstreamInfo;
import au.edu.anu.dspaceimporter.sword.data.StagingSwordRequestData;
import au.edu.anu.dspaceimporter.sword.data.SwordRequestData;
import au.edu.anu.dspaceimporter.sword.data.SwordRequestDataProvider;
import au.edu.anu.dspaceimporter.sword.exception.WorkflowException;
import au.edu.anu.dspaceimporter.util.DSpaceImporterConfiguration;
import au.edu.anu.dspaceimporter.util.DSpaceObject;

public abstract class UploadSubCommand {
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadSubCommand.class);
	
	abstract public void execute() throws UploadCommandException;
	
	public void processRecords(List<DSpaceObject> records, String collection) {
		QueryDSpace queryDspace = new QueryDSpace();
		DSpaceObjectParser parser = new DSpaceObjectParser();
		
		SwordRequestDataProvider dataProvider = new StagingSwordRequestData();
		
		for (DSpaceObject record : records) {
			boolean match = queryDspace.checkForMatch(record);
			if (match) {
				LOGGER.debug("Potential match found for: {}", record.getTitle());
			}
			else {
				LOGGER.debug("No match found for: {}", record.getTitle());
				try {
					Map<String, Set<String>> dspaceValues = parser.getDSPaceValues(record);
					// TODO if needed
//					Set<BitstreamInfo> bitstreams = getBitstreams(record);
					Set<BitstreamInfo> bitstreams = null;
					SwordRequestData data = new SwordRequestData(collection, dspaceValues, bitstreams, false);
					dataProvider.getSwordRequests().add(data);
				}
				catch (IllegalAccessException | InvocationTargetException e) {
					LOGGER.error("Exception processing dspace values", e);
				}
			}
		}
		if (dataProvider.getSwordRequests().size() > 0) {
			processSword(dataProvider);
		}
	}
	
	public void processSword(SwordRequestDataProvider dataProvider) {
		String serverUrl = (String) DSpaceImporterConfiguration.getProperty("uploader", "sword.server");
		String username = (String) DSpaceImporterConfiguration.getProperty("uploader", "sword.username");
		String password = (String) DSpaceImporterConfiguration.getProperty("uploader", "sword.password");
		
		String serviceDocumentUrl = serverUrl + "/servicedocument";
		LOGGER.info("Service document url: ", serviceDocumentUrl);
		
		ClientConfiguration config = new ClientConfiguration();
		SWORDClient swordClient = new SWORDClient(config);
		SwordServerInfo info = new SwordServerInfo(serviceDocumentUrl, username, password);
		SwordProcessor processor = new SwordProcessor(swordClient, info, dataProvider);
		try {
			processor.process();
//			if (matches.size() > 0) {
//				sendDuplicateList(matches);
//			}
		}
		catch (WorkflowException e) {
			LOGGER.error("Exception processing sword records", e);
		}
	}
}
