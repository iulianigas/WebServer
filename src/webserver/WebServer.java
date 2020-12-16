package webserver;

import java.io.IOException;
import java.net.ServerSocket;

import config.Config;
import config.Persist;
import exceptions.config_exceptions.ConfigurationException;
import exceptions.config_exceptions.LoadConfigurationFailureException;
import exceptions.webserver_exceptions.WebServerStateTransitionException;

public class WebServer {
	
	private Persist persist;
	
	public WebServer(String configFilePath) throws LoadConfigurationFailureException, IOException {
		this.persist = new Persist(new Config(configFilePath));
	}
	
	public void startWebServer() throws ConfigurationException, IOException, WebServerStateTransitionException {
		
		while(true) {
			if(WebServerState.isRunning())
				performStartMode();
			
			if (WebServerState.isMaintenance()) 
				performStartMode();
						
			if(WebServerState.isStopped()) 
				performStoppedMode();
				
		}
	}
		
	public void performStartMode() throws ConfigurationException {
		
		TerminalInterface.outputMessage("Starting. Enter into state: " + WebServerState.getCurrentState());
		
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(this.persist.getPortNumber());
			TerminalInterface.outputMessage("Connection Socket Created");
			
			try {
				while (!WebServerState.isStopped()) {
					TerminalInterface.outputMessage("Waiting for Connection");
					ClientSocketManager clientSocket = new ClientSocketManager(serverSocket.accept());
					Thread thread = new Thread(new WebServerThread(clientSocket, this.persist));
					thread.start();
				} 
			}catch (IOException e) {
				System.err.println("Accept failed.");
			}
			
			try {
				serverSocket.close();
			} catch (IOException e) {
				System.err.println("Could not close port: 10008.");
			}
			
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + this.persist.getPortNumber());
		}
	}

	
	public void performStoppedMode() throws WebServerStateTransitionException {
		//TerminalInterface.outputMessage("Stopped. Enter into state: " + WebServerState.getCurrentState());
	}
	
	
}

