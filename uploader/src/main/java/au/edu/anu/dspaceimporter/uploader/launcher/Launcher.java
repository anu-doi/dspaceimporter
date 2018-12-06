package au.edu.anu.dspaceimporter.uploader.launcher;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.dspaceimporter.uploader.command.UploadCommand;
import au.edu.anu.dspaceimporter.uploader.command.UploadCommandException;

public class Launcher {
	private static final Logger LOGGER = LoggerFactory.getLogger(Launcher.class);
	
	public static void main(String[] args) {
		LOGGER.info("Test");
		UploadCommand command = new UploadCommand();
		
		CmdLineParser parser = new CmdLineParser(command);
		try {
			parser.parseArgument(args);
//			parser.get
			if (command.getCmd() != null) {
				command.getCmd().execute();
			}
			else {
				parser.printSingleLineUsage(System.out);
				System.out.println("");
				parser.printUsage(System.out);
			}
		}
		catch(CmdLineException | UploadCommandException e) {
			System.err.println(e.getMessage());
			parser.printSingleLineUsage(System.out);;
			System.out.println("");;
			parser.printUsage(System.out);
		}
	}
}
