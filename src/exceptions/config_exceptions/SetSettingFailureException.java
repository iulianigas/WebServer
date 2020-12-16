package exceptions.config_exceptions;

public class SetSettingFailureException extends ConfigurationException{
	
	private static final long serialVersionUID = 1L;
	
	public SetSettingFailureException() {
		super("Config File exception: Setting was not set");
	}
}
