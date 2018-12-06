package au.edu.anu.dspaceimporter.uploader.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.kohsuke.args4j.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.dspaceimporter.plos.PlosDSpaceObject;
import au.edu.anu.dspaceimporter.plos.PlosQuery;
import au.edu.anu.dspaceimporter.plos.json.Docs;
import au.edu.anu.dspaceimporter.util.DSpaceImporterConfiguration;
import au.edu.anu.dspaceimporter.util.DSpaceObject;

public class PlosCommand extends UploadSubCommand {
	private static final Logger LOGGER = LoggerFactory.getLogger(PlosCommand.class);
	
	public static final String TYPE = "plos";
	
	@Option(name="-h", aliases="--help", help=true)
	private boolean help = false;
	
	@Option(name="-u", aliases="--upload", required=true)
	private boolean upload = false;
	
//	@Option(name="-s", aliases="--earliest-date", usage="Date to find the records from (format yyyy-MM-dd)", depends= {"-e"})
	@Option(name="-s", aliases="--earliest-date", required=false, usage="Date to find the records from (format yyyy-MM-dd)")
	private String earliestDate = null;
	
	@Option(name="-e", aliases="--latest-date", usage="Date to find the records to (format yyyy-MM-dd)", depends= {"-s"})
	private String latestDate = null;
	
	@Option(name="-c", aliases="--collection-handle", required=false, usage="For new items submit to a collection other than the default")
	private String collection;
	
	@Override
	public void execute() throws UploadCommandException {
		if (help || !upload) {
			CommandUtil.printUsage(TYPE, this.getClass());
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		Date endDate = null;
		try {
			if (null != earliestDate) {
				startDate = sdf.parse(earliestDate);
			}
			if (null != latestDate) {
				endDate = sdf.parse(latestDate);
			}
		}
		catch (ParseException e) {
			throw new UploadCommandException("Unable to parse the earliest and/or latest dates", e);
		}
		
		PlosQuery query = new PlosQuery();
		if (null == collection) {
			collection = (String) DSpaceImporterConfiguration.getProperty("uploader", "default.collection.handle");
		}
		LOGGER.info("Collection is: {}", collection);
		try {
			List<Docs> docs = query.getDocs(startDate, endDate);
			if (docs != null && docs.size() > 0) {
				List<DSpaceObject> dspaceObjects = new ArrayList<DSpaceObject>();
				for (Docs doc : docs) {
					DSpaceObject dspaceObject = new PlosDSpaceObject(doc);
					LOGGER.info("TItle: {}", dspaceObject.getTitle());
					dspaceObjects.add(dspaceObject);
				}
				processRecords(dspaceObjects, collection);
			}
			else {
				LOGGER.error("No records found");
			}
		}
		catch (SolrServerException e) {
			throw new UploadCommandException("Exception retrieving/procesing information from the plos server", e);
		}
	}
}
