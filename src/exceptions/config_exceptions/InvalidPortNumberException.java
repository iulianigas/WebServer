package exceptions.config_exceptions;

public class InvalidPortNumberException extends ConfigurationException {
	
	private static final long serialVersionUID = 1L;
	
	public InvalidPortNumberException() {
		super("Invalid Port Number");
	}
}
