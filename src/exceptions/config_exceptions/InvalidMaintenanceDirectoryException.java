package exceptions.config_exceptions;

public class InvalidMaintenanceDirectoryException extends ConfigurationException {
	
	private static final long serialVersionUID = 1L;
	
	public InvalidMaintenanceDirectoryException() {
		super("Invalid Maintenance Directory");
	}
}
