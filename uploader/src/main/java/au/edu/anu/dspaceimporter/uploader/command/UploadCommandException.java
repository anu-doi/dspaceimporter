package au.edu.anu.dspaceimporter.uploader.command;

public class UploadCommandException extends Exception {
	private static final long serialVersionUID = 1L;

	public UploadCommandException(String message) {
		super(message);
	}
	
	public UploadCommandException(String message, Throwable cause) {
		super(message, cause);
	}
}
