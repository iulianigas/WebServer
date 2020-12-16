package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import exceptions.parsers_exceptions.InvalidRequestException;
import parsers.RequestParser;

public class RequestParserTest {
	
	RequestParser validRequestParser1, validRequestParser2, validRequestParser3;
	RequestParser invalidRequestParser1;
	
	@Before
	public void init() {
	
		String validRequests[]= {"GET / HTTP/1.1\r\n"
				+ "Host: 127.0.0.1:10014\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:83.0) Gecko/20100101 Firefox/83.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1",
				
				"GET /index.html HTTP/1.1\r\n"
				+ "Host: localhost:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1",
				
				"GET /dir/page1.html HTTP/1.1\r\n"
				+ "Host: localhost:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1"};
		
		String invalidRequests[]= {"PUT /dir HTTP/1.1\r\n"
				+ "Host: localhost:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1"};
		
		this.validRequestParser1= new RequestParser(validRequests[0]);
		this.validRequestParser2= new RequestParser(validRequests[1]);
		this.validRequestParser3= new RequestParser(validRequests[2]);
		
		this.invalidRequestParser1= new RequestParser(invalidRequests[0]);
	}
	

	@Test
	public void testValidMethod() throws InvalidRequestException {
		assertEquals("GET",validRequestParser1.getMethod());
		assertEquals("GET",validRequestParser2.getMethod());
		assertEquals("GET",validRequestParser3.getMethod());
	}
	
	@Test (expected = InvalidRequestException.class)
	public void testInvalidMethod() throws InvalidRequestException {
		assertEquals("PUT",invalidRequestParser1.getMethod());
	}
	
	@Test
	public void testValidResourcePath() throws InvalidRequestException {				
		assertEquals("/",validRequestParser1.getResourcePath());
		assertEquals("/index.html",validRequestParser2.getResourcePath());
		assertEquals("/dir/page1.html",validRequestParser3.getResourcePath());
	}
	
	@Test (expected = InvalidRequestException.class)
	public void testInvalidResourcePath() throws InvalidRequestException {
		assertEquals("/dir",invalidRequestParser1.getResourcePath());		
	}
	
	@Test
	public void testValidHttpVersion() throws InvalidRequestException {					
		assertEquals("HTTP/1.1",validRequestParser1.getHttpVersion());
		assertEquals("HTTP/1.1",validRequestParser2.getHttpVersion());
		assertEquals("HTTP/1.1",validRequestParser3.getHttpVersion());
	}
	
	@Test (expected = InvalidRequestException.class)
	public void testInvalidHttpVersion() throws InvalidRequestException {
		invalidRequestParser1.getHttpVersion();	
	}
	
	@Test
	public void testValidHost() throws InvalidRequestException {					
		assertEquals("127.0.0.1:10014",validRequestParser1.getHost());
		assertEquals("localhost:10008",validRequestParser2.getHost());
		assertEquals("localhost:10008",validRequestParser3.getHost());	
	}
	
	@Test (expected = InvalidRequestException.class)
	public void testInvalidHost() throws InvalidRequestException {
		invalidRequestParser1.getHost();	
	}
	
	/*@Test
	public void testValidGetRequest() throws InvalidRequestException {					
		for(String validRequest: validRequests) {
				RequestParser requestParser= new RequestParser(validRequest);
				//Request request = RequestHandler.getRequest(requestParser);
		}		
	}
	
	@Test (expected = InvalidRequestException.class)
	public void testInvalidGetRequest() throws InvalidRequestException {
		for(String invalidRequest: invalidRequests) {
			RequestParser requestParser= new RequestParser(invalidRequest);
			//Request request = RequestHandler.getRequest(requestParser);
		}		
	}*/
	
	
	
	

}
