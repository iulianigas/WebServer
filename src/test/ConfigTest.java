package test;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import config.Config;
import config.FileManager;
import exceptions.config_exceptions.ConfigurationException;
import exceptions.config_exceptions.GetSettingFailureException;
import exceptions.config_exceptions.LoadConfigurationFailureException;

public class ConfigTest {
	
	String validConfigFileName, invalidConfigFileName;
	
	@Before
	public void init() throws IOException {
		String content = "key = value \n"
				+ "number = 1 \n"
				+ "path = C:/Users/Iulian/GitHub \n"
				+ "empty = \n";
		this.validConfigFileName = "resources/testing/testingconfig.properties";
		this.invalidConfigFileName = "resources/testing/testingconfig_nonExistentFile.properties";
		
		//create validConfigFileName
		String filePath = "resources/testing/testingconfig.properties";
		FileManager.writeContentToFile(filePath, content);	
	}
	
	
	@Test
	public void testValidLoadConfigurationFile() throws LoadConfigurationFailureException, IOException  {
		//loading is done in the constructor
		Config conf = new Config(validConfigFileName);
		conf.loadConfiguration();
	}
	
	@Test (expected = IOException.class)
	public void testInvalidLoadConfigurationFile() throws ConfigurationException, IOException {
		//loading is done in the constructor
		Config conf = new Config(invalidConfigFileName);
		conf.loadConfiguration();
	}
	
	@Test
	public void testValidGetSetting() throws ConfigurationException, IOException {
		Config conf = new Config(validConfigFileName);
		conf.loadConfiguration();
		
		assertEquals("value",conf.getSetting("key"));
		assertEquals("1", conf.getSetting("number"));
		assertEquals("C:/Users/Iulian/Documents/GitHub", conf.getSetting("path"));
		assertEquals("", conf.getSetting("empty"));
	}
	
	@Test (expected = GetSettingFailureException.class)
	public void testInvalidGetSetting() throws ConfigurationException, IOException {
		Config conf = new Config(validConfigFileName);
		conf.loadConfiguration();
		
		conf.getSetting("InvalidKey");
	}
	

	@Test
	public void testSetSettingChangeValue() throws ConfigurationException, IOException {
		Config conf = new Config(validConfigFileName);
		conf.loadConfiguration();
		
		conf.setSetting("number", "2");
		assertEquals("2", conf.getSetting("number"));
	}
	
	@Test
	public void testSetSettingAddNewValue() throws ConfigurationException, IOException {
		Config conf = new Config(validConfigFileName);
		conf.loadConfiguration();
		
		conf.setSetting("newKey", "newValue");
		assertEquals("newValue", conf.getSetting("newKey"));
	}
	
	@Test (expected = GetSettingFailureException.class)
	public void testNotUsingSaveConfiguration() throws ConfigurationException, IOException {
		//setSetting without saving
		Config conf1 = new Config(validConfigFileName);
		conf1.loadConfiguration();
		
		conf1.setSetting("newKey", "newValue");
		assertEquals("newValue", conf1.getSetting("newKey"));
				
		//check if value is saved to file
		Config conf2 = new Config(validConfigFileName);
		conf2.loadConfiguration();
		conf2.getSetting("newKey");
	}
	
	@Test
	public void testUsingSaveConfiguration() throws ConfigurationException, IOException {
		//setSetting with saving
		Config conf1 = new Config(validConfigFileName);
		conf1.loadConfiguration();
		
		conf1.setSetting("newKey", "newValue");
		assertEquals("newValue", conf1.getSetting("newKey"));
		conf1.saveConfiguration();
		
		//check if value is saved to file
		Config conf2 = new Config(validConfigFileName);
		conf2.loadConfiguration();
		conf2.getSetting("newKey");
	}
}
