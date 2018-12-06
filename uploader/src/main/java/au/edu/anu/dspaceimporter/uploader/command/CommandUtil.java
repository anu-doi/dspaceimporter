package au.edu.anu.dspaceimporter.uploader.command;

import org.kohsuke.args4j.CmdLineParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommandUtil.class);
	
	private static final String UPLOADER = "uploader";
	
	public static void printUsage(String command, Class<?> clazz) {
		try {
			CmdLineParser parser = new CmdLineParser(clazz.newInstance());
			System.out.println(CommandUtil.UPLOADER + " " + command);
			parser.printSingleLineUsage(System.out);
			System.out.println(System.out);
			parser.printUsage(System.out);
		}
		catch (IllegalAccessException e) {
			System.err.println("Exception accessing class " + clazz.getName() + ". " + e.getMessage());
		}
		catch (InstantiationException e) {
			System.err.println("Exception instantiationg class " + clazz.getName() + ". " + e.getMessage());
		}
	}
}
