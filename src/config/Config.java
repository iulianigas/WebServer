package config;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import exceptions.config_exceptions.ConfigurationException;
import exceptions.config_exceptions.GetSettingFailureException;
import exceptions.config_exceptions.LoadConfigurationFailureException;
import exceptions.config_exceptions.SetSettingFailureException;
import validators.ConfigValidator;

public class Config {
	
	private String configurationFileName;
	private Properties prop;
	
	public Config(String configurationFileName) throws  IOException, LoadConfigurationFailureException {
		this.configurationFileName=configurationFileName;
	}
	
	public void loadConfiguration() throws LoadConfigurationFailureException, IOException {	
		this.prop = new Properties();
		FileReader reader = new FileReader(configurationFileName,StandardCharsets.UTF_8);
		try {
			this.prop.load(reader);
		} catch (IOException ex) {
            throw new IOException("Configuration file '" + configurationFileName + "' was not found");
        } finally {
        	reader.close();
        }
	}
	
	public void saveConfiguration() throws IOException{
		FileWriter output = new FileWriter(this.configurationFileName,StandardCharsets.UTF_8);
		try {
			prop.store(output, "Changed file");
        } catch (IOException ex) {
            throw new IOException("Save Configuration: Error on saveing Config file");
        } finally {
        	output.close();
        }
	}
	
	public String getSetting(String key) throws GetSettingFailureException{
		String value = prop.getProperty(key);
		
		if(!ConfigValidator.validateGetSetting(value)) {
			throw new GetSettingFailureException();
		}
		return value;
	}
	
	public void setSetting(String key, String value) throws SetSettingFailureException {
		prop.setProperty(key, value);
		
		if(!ConfigValidator.validateSetSetting(value,prop.getProperty(key))) {
			throw new SetSettingFailureException();
		}
	}
	
	public void printConfig() {
		System.out.println("PrintConfig -------------------------------");
		prop.forEach((k, v) -> System.out.println("Key : " + k + ", Value : " + v));
		System.out.println("-------------------------------------------");
	}
	
	public static void createConfigFile(String maintenanceDirPath, String rootDirPath, int portNumber, String configPath) throws IOException, ConfigurationException {
		Config newConfig = new Config (configPath);
		newConfig.loadConfiguration();
		newConfig.setSetting("maintenanceDirectory", maintenanceDirPath);
		newConfig.setSetting("rootDirectory", rootDirPath);
		newConfig.setSetting("portNumber", String.valueOf(portNumber));
		newConfig.saveConfiguration();
		
	}

}
