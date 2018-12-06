package au.edu.anu.dspaceimporter.uploader.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.kohsuke.args4j.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.dspaceimporter.exception.UploaderException;
import au.edu.anu.dspaceimporter.springer.SpringerDspaceObject;
import au.edu.anu.dspaceimporter.springer.SpringerQuery;
import au.edu.anu.dspaceimporter.springer.xml.Article;
import au.edu.anu.dspaceimporter.springer.xml.Records;
import au.edu.anu.dspaceimporter.util.DSpaceImporterConfiguration;
import au.edu.anu.dspaceimporter.util.DSpaceObject;

public class SpringerCommand extends UploadSubCommand {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringerCommand.class);
	
	public static final String TYPE = "springer";
	
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
		if (help) {
			CommandUtil.printUsage(TYPE, this.getClass());
			return;
		}
		if (!upload) {
			CommandUtil.printUsage(TYPE, this.getClass());
			return;
		}
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (earliestDate != null) {
				sdf.parse(earliestDate);
			}
		}
		catch (ParseException e) {
			throw new UploadCommandException("Incorrect date format");
		}
		
		SpringerQuery query = new SpringerQuery();
		if (null == collection) {
			collection = (String) DSpaceImporterConfiguration.getProperty("uploader", "default.collection.handle");
		}
		
		try {
			Records records = query.getRecords(earliestDate, latestDate);
			if (null != records) {
				List<DSpaceObject> dspaceObjects = new ArrayList<DSpaceObject>();
				for (Article article : records.getArticles()) {
					DSpaceObject dspaceObject = new SpringerDspaceObject(article);
					LOGGER.info("Found Title: {}", dspaceObject.getTitle());
					dspaceObjects.add(dspaceObject);
				}
				processRecords(dspaceObjects, collection);
			}
			else {
				LOGGER.error("No records found");
			}
		}
		catch (JAXBException e) {
			throw new UploadCommandException("Error extracting response object", e);
		}
		catch (ParseException e) {
			throw new UploadCommandException("Exception dealing with dates", e);
		}
		catch (UploaderException e) {
			throw new UploadCommandException("Exception ", e);
		}
	}
	
//	private DSpaceObjec
}
