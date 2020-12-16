package parsers;

import java.util.StringTokenizer;
import exceptions.parsers_exceptions.InvalidRequestException;
import validators.RequestParserValidator;


public class RequestParser {
	
	private String requestSplit[];
	
	public RequestParser(String requestContent) {
		this.requestSplit = splitString(requestContent);
	}
	
	public String getMethod() throws InvalidRequestException {
		String method = requestSplit[0];
		
		if(!RequestParserValidator.validateMethod(method)) {
			throw new InvalidRequestException("Invalid resource request");
		}
		return method;
	}

	public String getResourcePath() throws InvalidRequestException {
		String resourcePath = requestSplit[1];
		
		if(!RequestParserValidator.validateResource(resourcePath)) {
			throw new InvalidRequestException("Invalid resource request");
		}
		return resourcePath;
	}
	
	public String getHttpVersion() throws InvalidRequestException {
		String httpVersion = requestSplit[2];
		
		if(!RequestParserValidator.validateHttpVersion(httpVersion)) {
			throw new InvalidRequestException("Invalid resource request");
		}
		return httpVersion;
	}
	
	public String getHost() throws InvalidRequestException {
		String host = requestSplit[4];
		
		if(!RequestParserValidator.validateHost(host)) {
			throw new InvalidRequestException("Invalid host request");
		}
		return host;
	}
	
	public String[] splitString(String str) {
		String delim = " \n\r\t";
		StringTokenizer tokens = new StringTokenizer(str,delim);
		String result[] = new String[tokens.countTokens()+1];
		int i = 0;
		while(tokens.hasMoreTokens()) {
			result[i]=tokens.nextToken();
			//System.out.println(i+": "+result[i]);
			i=i+1;
		}
		return result;
	}
	
}
