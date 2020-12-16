package webserver;

import config.Persist;
import exceptions.config_exceptions.ConfigurationException;
import exceptions.parsers_exceptions.InvalidRequestException;
import handlers.RequestHandler;
import handlers.ResponseHandler;
import java.io.*;

public class WebServerThread extends Thread {
	
	private ClientSocketManager clientSocketManager;
	private Persist persist;

	public WebServerThread(ClientSocketManager clientSocketManager, Persist persist) {
		this.clientSocketManager = clientSocketManager;
		this.persist=persist;
	}

	public void run(){
		TerminalInterface.outputMessage("New Communication Thread started");

		try {
			if(WebServerState.isRunning()) 
				performRunningMode();
	
					
			if(WebServerState.isMaintenance()) 
				performMaintenanceMode();
			
					
			/*if(WebServerState.isStopped()) {
			}*/	

			this.clientSocketManager.closeAll();
		} catch (IOException e) {
			System.err.println("Problem with Communication Server");
		} catch (InvalidRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void performRunningMode() throws IOException, InvalidRequestException, ConfigurationException {	
		Request request = RequestHandler.getRequest(this.clientSocketManager.getClientInput());
		Response response = ResponseHandler.getResponse(this.persist.getRootDirectory(), request);
		ResponseHandler.sendResponse(this.clientSocketManager.getClientSocket(), this.clientSocketManager.getClientOutput(), response);	
	}
	
	public void performMaintenanceMode() throws IOException, ConfigurationException, InvalidRequestException {
		Request request = RequestHandler.getRequest(this.clientSocketManager.getClientInput());
		Response response = ResponseHandler.getResponse(this.persist.getMaintenanceDirectory(),request);
		ResponseHandler.sendResponse(this.clientSocketManager.getClientSocket(), this.clientSocketManager.getClientOutput(), response);	
	}

		
}