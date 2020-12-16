package exceptions.config_exceptions;

public class GetSettingFailureException extends ConfigurationException{

	private static final long serialVersionUID = 1L;

	public GetSettingFailureException() {
		super("Config File exception: Setting was not found");
	}
}
