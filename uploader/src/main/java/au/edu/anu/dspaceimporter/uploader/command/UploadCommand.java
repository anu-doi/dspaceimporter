package au.edu.anu.dspaceimporter.uploader.command;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.SubCommand;
import org.kohsuke.args4j.spi.SubCommandHandler;
import org.kohsuke.args4j.spi.SubCommands;

public class UploadCommand {
	
	@Argument(handler=SubCommandHandler.class, metaVar="<command> [options]", usage="Current commands are springer, Please use 'uploader <command> -h' for further information.")
	@SubCommands({
		@SubCommand(name=SpringerCommand.TYPE, impl=SpringerCommand.class)
		, @SubCommand(name=PlosCommand.TYPE, impl=PlosCommand.class)
	})
	UploadSubCommand cmd;
	
	@Option(name="-h", aliases="--help", usage="Display this")
	private boolean help = false;
	
	public UploadSubCommand getCmd() {
		return cmd;
	}

	public void setCmd(UploadSubCommand cmd) {
		this.cmd = cmd;
	}

	public boolean isHelp() {
		return help;
	}

	public void setHelp(boolean help) {
		this.help = help;
	}
}
