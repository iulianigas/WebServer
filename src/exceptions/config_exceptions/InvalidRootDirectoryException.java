package exceptions.config_exceptions;

public class InvalidRootDirectoryException extends ConfigurationException {
	
	private static final long serialVersionUID = 1L;
	
	public InvalidRootDirectoryException() {
		super("Invalid Root Directory");
	}
}
