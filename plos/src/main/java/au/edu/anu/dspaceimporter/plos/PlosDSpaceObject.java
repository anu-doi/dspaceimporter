package au.edu.anu.dspaceimporter.plos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.dspaceimporter.annotation.DSpaceField;
import au.edu.anu.dspaceimporter.plos.json.Docs;
import au.edu.anu.dspaceimporter.util.DSpaceObject;

public class PlosDSpaceObject implements DSpaceObject {
	private static final Logger LOGGER = LoggerFactory.getLogger(PlosDSpaceObject.class);
	private static final SimpleDateFormat SOLR_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	private static final SimpleDateFormat DSPACE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	private Docs doc;
	
	public PlosDSpaceObject(Docs doc) {
		this.doc = doc;
	}

	@Override
	@DSpaceField("dc-title")
	public String getTitle() {
		return doc.getTitle();
	}

	@Override
	@DSpaceField("dc-source")
	public String getJournal() {
		return doc.getJournal();
	}

	@Override
	public String getPublisher() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@DSpaceField("dc-contributor-author")
	public List<String> getAuthors() {
		List<String> authors = new ArrayList<String>();
		for (String auth : doc.getAuthors()) {
			int lastSpaceIndex = auth.lastIndexOf(" ");
			if (lastSpaceIndex > 0) {
				String surname = auth.substring(lastSpaceIndex+1);
				String givenName = auth.substring(0, lastSpaceIndex);
				String name = surname + ", " + givenName;
				authors.add(name);
			}
			else {
				authors.add(auth);
			}
		}
		return authors;
	}

	@Override
	@DSpaceField("dc-identifier-issn")
	public String getIssn() {
		return doc.getEissn();
	}

	@Override
	public String getIsbn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@DSpaceField("local-identifier-doi")
	public String getDoi() {
		return doc.getId();
	}

	@Override
	@DSpaceField("dc-date-issued")
	public String getPublicationDate() {
		//2018-09-12T00:00:00Z
		if (null != doc.getPublicationDate()) {
			String publicationDate = doc.getPublicationDate();
			try {
				Date date = SOLR_DATE_FORMAT.parse(publicationDate);
				String returnPubDate = DSPACE_DATE_FORMAT.format(date);
				return returnPubDate;
			}
			catch (ParseException e) {
				LOGGER.error("Exception parsing publication date", e);
			}
		}
		return null;
	}

	@Override
	public List<String> getAffiliations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVolume() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIssue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRights() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@DSpaceField("dc-description-abstract")
	public String getAbstract() {
		try {
			return doc.getAbstrs().get(0);
		}
		catch (NullPointerException e) {
			
		}
		return null;
	}

	@Override
	@DSpaceField("local-description-notes")
	public String getNotes() {
		return "Imported from PLOS";
	}
}
