package parsers;

import java.io.IOException;
import config.FileManager;
import webserver.DefaultValues;
import webserver.Request;
import webserver.WebServerState;

public class ResponseParser {
	
	private String rootDirectoryPath;
	private Request request;
	
	public ResponseParser(String rootDirectoryPath, Request request) {
		this.rootDirectoryPath = rootDirectoryPath;
		this.request = request;
	}
	
	public String getHttpVersion() {
		return request.getHttpVersion();
	}
	
	public String getStatusCode() {
		if(WebServerState.isStopped()) 
			return "502 Service Unavailable";
		
		if(WebServerState.isMaintenance()) 
			return "502 Service Unavailable";
		
		if(WebServerState.isRunning()) {
			if(isPageNotFound())
				return "404 Not Found";
			return "200 OK";
		}
		
		return "502 Service Unavailable";
	}
	
	public String getContentType() {
		 String contentType="text/plain";	 
		 String requestFile = request.getResourcePath();
		 
		 if(requestFile.equals("/")) 
			 requestFile = DefaultValues.getHomePage();
		 
		 if(isPageNotFound()) {
			 if(WebServerState.isMaintenance()) {
				 requestFile = DefaultValues.getHomePage();
			 }else {
				 requestFile = DefaultValues.getpageNotFound();
			 }
		 }
		 
		 if (requestFile.endsWith(".html") || requestFile.endsWith(".htm"))
			 contentType="text/html";
		 else if (requestFile.endsWith(".css"))
			 contentType="text/css";
		 else if (requestFile.endsWith(".jpg") || requestFile.endsWith(".jpeg"))
			 contentType="image/jpeg";
		 else if (requestFile.endsWith(".gif"))
			 contentType="image/gif";
		 else if (requestFile.endsWith(".class"))
		     contentType="application/octet-stream";
		 	 
		 return contentType;
	}
	
	public byte[] getContentByte() throws IOException {
		String resultPath;
		
		if(request.getResourcePath().equals("/")||request.getResourcePath().isEmpty()) {
			resultPath = rootDirectoryPath + "/" + DefaultValues.getHomePage();
		}
		else if(FileManager.isFile(rootDirectoryPath + request.getResourcePath())) {
			resultPath = rootDirectoryPath + request.getResourcePath();
		}
		else if(WebServerState.isMaintenance()) {
			resultPath = rootDirectoryPath + "/" + DefaultValues.getHomePage();
		}
		else {
			resultPath = rootDirectoryPath  + "/" +  DefaultValues.getpageNotFound();
		}
		
		return FileManager.getContentBytes(resultPath);	
	}
	
	public String getContentString() throws IOException {
		String resultPath;
		
		if(request.getResourcePath().equals("/")||request.getResourcePath().isEmpty()) {
			resultPath = rootDirectoryPath + "/" + DefaultValues.getHomePage();
		}
		else if(FileManager.isFile(rootDirectoryPath + request.getResourcePath())) {
			resultPath = rootDirectoryPath + request.getResourcePath();
		}
		else if(WebServerState.isMaintenance()) {
			resultPath = rootDirectoryPath + "/" + DefaultValues.getHomePage();
		}
		else {
			resultPath = rootDirectoryPath  + "/" +  DefaultValues.getpageNotFound();
		}
		
		return FileManager.getContent(resultPath);	
	}
	
	public boolean isPageNotFound() {
		
		//default page
		if(request.getResourcePath().equals("/")) 
			return false;
		
		//valid path
		if(FileManager.isFile(rootDirectoryPath + request.getResourcePath())) 
			return false;

		return true;
		
	}
}
