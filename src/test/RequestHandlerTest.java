package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import exceptions.parsers_exceptions.InvalidRequestException;
import handlers.RequestHandler;
import parsers.RequestParser;
import webserver.Request;

public class RequestHandlerTest {

	String validRequest= "GET / HTTP/1.1\r\n"
			+ "Host: 127.0.0.1:10014\r\n"
			+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:83.0) Gecko/20100101 Firefox/83.0\r\n"
			+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
			+ "Accept-Language: en-US,en;q=0.5\r\n"
			+ "Accept-Encoding: gzip, deflate\r\n"
			+ "Connection: keep-alive\r\n"
			+ "Upgrade-Insecure-Requests: 1";
	@Test
	public void test() throws InvalidRequestException, IOException {
		RequestParser rp = new RequestParser(validRequest);
		Request request = RequestHandler.getRequest(rp);
		assertEquals("GET",request.getMethod());
		assertEquals("HTTP/1.1",request.getHttpVersion());
		assertEquals("127.0.0.1:10014",request.getHost());
		assertEquals("/",request.getResourcePath());
		
	}

}
