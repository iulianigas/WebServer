package exceptions.config_exceptions;

public class LoadConfigurationFailureException extends ConfigurationException {

	private static final long serialVersionUID = 1L;
	
	public LoadConfigurationFailureException(String message) {
		super(message);
	}
}
