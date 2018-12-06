package au.edu.anu.dspaceimporter.exception;

public class UploaderException extends Exception {
	private static final long serialVersionUID = 1L;

	public UploaderException(String message, Throwable cause) {
		super(message, cause);
	}
	public UploaderException(String message) {
		super(message);
	}
}
