package config;

import java.io.IOException;
import exceptions.config_exceptions.ConfigurationException;
import exceptions.config_exceptions.InvalidMaintenanceDirectoryException;
import exceptions.config_exceptions.InvalidPortNumberException;
import exceptions.config_exceptions.InvalidRootDirectoryException;
import exceptions.config_exceptions.LoadConfigurationFailureException;
import validators.MaintenanceDirectoryValidator;
import validators.PortNumberValidator;
import validators.RootDirectoryValidator;

public class Persist {
	
	private Config config;
		
	public Persist(Config config) throws LoadConfigurationFailureException, IOException {
		this.config = config;
		config.loadConfiguration();
	}
	
	public Persist() {
	}
	
	public String getRootDirectory() throws ConfigurationException {
		String rootDirectory = config.getSetting("rootDirectory");
		if(!RootDirectoryValidator.validate(rootDirectory))
		{
			throw new InvalidRootDirectoryException();
		}
		return rootDirectory;
	}
	
	public String getMaintenanceDirectory() throws ConfigurationException {
		String maintenanceDirectory = config.getSetting("maintenanceDirectory");
		if(!MaintenanceDirectoryValidator.validate(maintenanceDirectory))
		{
			throw new InvalidMaintenanceDirectoryException();
		}
		return maintenanceDirectory;
	}
	
	public int getPortNumber() throws NumberFormatException, ConfigurationException {
		int portNumber = Integer.parseInt(config.getSetting("portNumber"));
		if(!PortNumberValidator.validate(portNumber))
		{
			throw new InvalidPortNumberException();
		}
		return portNumber;
	}
	
	
	public void setRootDirectory(String rootDirectory) throws IOException, ConfigurationException {
		if(!RootDirectoryValidator.validate(rootDirectory))
		{
			throw new InvalidRootDirectoryException();
		}
		config.setSetting("rootDirectory",rootDirectory);
		config.saveConfiguration();
	}
	
	public void setMaintenanceDirectory(String maintenanceDirectory) throws IOException, ConfigurationException {
		if(!MaintenanceDirectoryValidator.validate(maintenanceDirectory)) {
			throw new InvalidMaintenanceDirectoryException();
		}
		config.setSetting("maintenanceDirectory",maintenanceDirectory);
		config.saveConfiguration();
	}
	
	public void setPortNumber(int portNumber) throws IOException, ConfigurationException {
		if (!PortNumberValidator.validate(portNumber)) {
			throw new InvalidPortNumberException();
		}
		config.setSetting("portNumber",String.valueOf(portNumber));
		config.saveConfiguration();
	}
	
}
