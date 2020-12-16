package webserver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import config.Config;
import config.FileManager;
import exceptions.config_exceptions.ConfigurationException;
import exceptions.config_exceptions.LoadConfigurationFailureException;
import exceptions.webserver_exceptions.WebServerStateTransitionException;

public class Main {

	
	public static void main(String[] args) throws IOException, ConfigurationException, WebServerStateTransitionException {
		
		String configFilePath = "C:/Users/Iulian/Documents/GitHub/webserver/WebserverTestingDirectories/config.properties";
		
		/*WebServer webServer= new WebServer(configFilePath);
		WebServerState.setRunning();
		WebServerState.setMaintenance();
		webServer.startWebServer();*/
		
		new Thread(new Runnable() {
	        @Override
	      public void run() {
	           try (Scanner in = new Scanner(System.in,StandardCharsets.UTF_8)) {
				while (true) {
				        TerminalInterface.printServerSettingsMenu();

				        try {
				            switch (in.nextInt()) {
				                case 0:
				                    WebServerState.setStopped();
				                    break;
				                case 1:
				                    WebServerState.setRunning();
				                    break;
				                case 2:
				                	 WebServerState.setMaintenance();
				                    break;
				                case 3:
				                    System.exit(0);
				            }
				        } catch (WebServerStateTransitionException e) {
				            e.printStackTrace();
				        }
				    }
	           }
	        }
	    }).start();		
		
		new Thread(new Runnable() {
	        @Override
	        public void run() {
	            try {
	            	WebServer webServer= new WebServer(configFilePath);
	        		webServer.startWebServer();
	            } catch (IOException e) {
	                e.printStackTrace();
	            } catch (ConfigurationException e) {
					e.printStackTrace();
	            } catch (WebServerStateTransitionException e) {
					e.printStackTrace();
				}
	        }
	     }).start();
	        
	}
	
	public void testConfig() throws LoadConfigurationFailureException, IOException{
		Config properties = new Config("resources/config/config.properties");
		properties.loadConfiguration();
		properties.printConfig();
	}
	
	public void testPersist() throws IOException, ConfigurationException{
		String content = "";
		
		String filePath = "..\\webserver\\resources\\testing\\Persist\\testPersistconfig.properties";
		FileManager.writeContentToFile(filePath, content);
		Config config = new Config (filePath);
		config.printConfig();
	}
}
