package handlers;

import java.io.BufferedReader;
import java.io.IOException;
import exceptions.parsers_exceptions.InvalidRequestException;
import parsers.RequestParser;
import webserver.Request;

public class RequestHandler {

	public static String getRequestFromClientInput(BufferedReader clientInput) throws IOException {
		String inputLine;
		StringBuilder request = new StringBuilder();
		
		while ((inputLine = clientInput.readLine()) != null) {
			//System.out.println(inputLine);
			request.append(inputLine);
			request.append('\n');
			
			if (inputLine.trim().equals(""))
				break;
		}
		return request.toString();
	}
	
	public static Request getRequest(BufferedReader clientInput) throws InvalidRequestException, IOException {
		String requestContent = getRequestFromClientInput(clientInput);
		RequestParser rp = new RequestParser(requestContent);

		return new Request(
				rp.getMethod(),
				rp.getResourcePath(),
				rp.getHttpVersion(),
				rp.getHost());
	}
	
	public static Request getRequest(RequestParser rp) throws InvalidRequestException, IOException {
		return new Request(
				rp.getMethod(),
				rp.getResourcePath(),
				rp.getHttpVersion(),
				rp.getHost());
	}
}
