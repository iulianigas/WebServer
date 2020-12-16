package test;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import exceptions.parsers_exceptions.InvalidRequestException;
import exceptions.webserver_exceptions.WebServerStateTransitionException;
import handlers.RequestHandler;
import parsers.RequestParser;
import parsers.ResponseParser;
import webserver.WebServerState;

public class ResponeParserTest {
	
	ResponseParser validResponse1_html, validResponse2_html, validResponse_css, validResponse_jpg, homePageResponse, pageNotFoundResponse;
	String rootDir;
	
	@Before
	public void init() throws InvalidRequestException, IOException {
	
		String requests[]= {"GET / HTTP/1.1\r\n"
				+ "Host: 127.0.0.1:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1",
				
				"GET /page1.html HTTP/1.1\r\n"
				+ "Host: localhost:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1",
				
				"GET /page2.html HTTP/1.1\r\n"
				+ "Host: localhost:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1",
				
				"GET /kitten.jpg HTTP/1.1\r\n"
				+ "Host: localhost:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1",
				
				"GET /style.css HTTP/1.1\r\n"
				+ "Host: localhost:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1",
				
				"GET /inexistent.php HTTP/1.1\r\n"
				+ "Host: localhost:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1"};
	
		this.rootDir = "../WebServer/WebserverTestingDirectories/RootDirectory/htdocs";
		this.homePageResponse= new ResponseParser(rootDir,RequestHandler.getRequest(new RequestParser(requests[0])));
		this.validResponse1_html= new ResponseParser(rootDir,RequestHandler.getRequest(new RequestParser(requests[1])));
		this.validResponse2_html= new ResponseParser(rootDir,RequestHandler.getRequest(new RequestParser(requests[2])));
		this.validResponse_jpg= new ResponseParser(rootDir,RequestHandler.getRequest(new RequestParser(requests[3])));
		this.validResponse_css= new ResponseParser(rootDir,RequestHandler.getRequest(new RequestParser(requests[4])));
		this.pageNotFoundResponse= new ResponseParser(rootDir,RequestHandler.getRequest(new RequestParser(requests[5])));
	}

	
	@Test
	public void testHttpVersion(){
		assertEquals("HTTP/1.1",validResponse1_html.getHttpVersion());
		assertEquals("HTTP/1.1",validResponse2_html.getHttpVersion());
		assertEquals("HTTP/1.1",validResponse_jpg.getHttpVersion());
		assertEquals("HTTP/1.1",validResponse_css.getHttpVersion());
		assertEquals("HTTP/1.1",homePageResponse.getHttpVersion());
		assertEquals("HTTP/1.1",pageNotFoundResponse.getHttpVersion());
	}
	
	@Test
	public void testStatusCode() throws WebServerStateTransitionException{
		WebServerState.setStopped();
		assertEquals("502 Service Unavailable",validResponse1_html.getStatusCode());
		assertEquals("502 Service Unavailable",homePageResponse.getStatusCode());
		assertEquals("502 Service Unavailable",pageNotFoundResponse.getStatusCode());
		
		WebServerState.setRunning();
		assertEquals("200 OK",validResponse1_html.getStatusCode());
		assertEquals("200 OK",validResponse2_html.getStatusCode());
		assertEquals("200 OK",validResponse_jpg.getStatusCode());
		assertEquals("200 OK",validResponse_css.getStatusCode());
		assertEquals("200 OK",homePageResponse.getStatusCode());
		assertEquals("404 Not Found",pageNotFoundResponse.getStatusCode());
		
		WebServerState.setMaintenance();
		assertEquals("502 Service Unavailable",validResponse1_html.getStatusCode());
		assertEquals("502 Service Unavailable",homePageResponse.getStatusCode());
		assertEquals("502 Service Unavailable",pageNotFoundResponse.getStatusCode());
	}
	
	@Test
	public void testContentType() {
		assertEquals("text/html",validResponse1_html.getContentType());
		assertEquals("text/html",validResponse2_html.getContentType());
		assertEquals("image/jpeg",validResponse_jpg.getContentType());
		assertEquals("text/css",validResponse_css.getContentType());
		assertEquals("text/html",homePageResponse.getContentType());
		assertEquals("text/html",pageNotFoundResponse.getContentType());
		
	}
	
	@Test
	public void testValidResponseContent() throws IOException {
		String expected ="<html lang=\"en\">\r\n"
				+ "	<head>\r\n"
				+ "	  <title>Just doing my homework</title>\r\n"
				+ "	  <link href=\"style.css\" type=\"text/css\" rel=\"stylesheet\">\r\n"
				+ "	 </head>\r\n"
				+ "	\r\n"
				+ "	<body>	 \r\n"
				+ "		<p>AND JUST LIKE THAT I FINISHED MY HOMEWORK <br><br>- Ashley M. H. -</p>	\r\n"
				+ "		<button class=\"button\" type=\"button\" data-hover=\"Now back to finishing all the other 1000 projects that I am behind on!\" data-active=\"Super yey!\"><span>YEY!</span></button>			\r\n"
				+ "	</body>\r\n"
				+ "</html>";
		assertEquals(expected,homePageResponse.getContentString());
		
		expected="<html>\r\n"
				+ " <p>Welcome to the page1!</p>\r\n"
				+ " <img src=\"kitten.jpg\" alt=\"kitten\">\r\n"
				+ "</html>";
		assertEquals(expected,validResponse1_html.getContentString());
	}
	
	@Test
	public void testPageNotFoundResponseContent() throws IOException {
		String expected ="<html>\r\n"
				+ " <p>Page was not found!</p>\r\n"
				+ "</html>";
		assertEquals(expected,pageNotFoundResponse.getContentString());
	}
	
	@Test
	public void testCSSResponseContent() throws IOException {
		String expected ="*,\r\n"
				+ "*:before,\r\n"
				+ "*:after {\r\n"
				+ "    -webkit-box-sizing: border-box;\r\n"
				+ "    -moz-box-sizing: border-box;\r\n"
				+ "    box-sizing: border-box;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "body {\r\n"
				+ "    text-align: center;\r\n"
				+ "    background: #00C8FF;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "p {\r\n"
				+ "    font-size: 1.6em;\r\n"
				+ "    font-family: 'Lato', sans-serif;\r\n"
				+ "    background-color: #fff;\r\n"
				+ "    padding: 1em;\r\n"
				+ "    color: #002240;\r\n"
				+ "    margin-top: 0;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "/* .button */\r\n"
				+ "\r\n"
				+ ".button {\r\n"
				+ "	width:40%;\r\n"
				+ "	height:40%;\r\n"
				+ "    display: inline-block;\r\n"
				+ "    position: relative;\r\n"
				+ "    margin: 1em;\r\n"
				+ "    padding: 0.67em;\r\n"
				+ "    border: 4px solid #FFF;\r\n"
				+ "    overflow: hidden;\r\n"
				+ "    text-decoration: none;\r\n"
				+ "    font-size: 2vw;\r\n"
				+ "    outline: none;\r\n"
				+ "    color: #FFF;\r\n"
				+ "    background: transparent;\r\n"
				+ "    font-family: 'raleway', sans-serif;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".button span {\r\n"
				+ "    -webkit-transition: 0.6s;\r\n"
				+ "    -moz-transition: 0.6s;\r\n"
				+ "    -o-transition: 0.6s;\r\n"
				+ "    transition: 0.6s;\r\n"
				+ "    -webkit-transition-delay: 0.2s;\r\n"
				+ "    -moz-transition-delay: 0.2s;\r\n"
				+ "    -o-transition-delay: 0.2s;\r\n"
				+ "    transition-delay: 0.2s;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".button:before,\r\n"
				+ ".button:after {\r\n"
				+ "    content: '';\r\n"
				+ "    position: absolute;\r\n"
				+ "    top: 0.67em;\r\n"
				+ "    left: 0;\r\n"
				+ "    width: 100%;\r\n"
				+ "    text-align: center;\r\n"
				+ "    opacity: 0;\r\n"
				+ "    -webkit-transition: .4s,opacity .6s;\r\n"
				+ "    -moz-transition: .4s,opacity .6s;\r\n"
				+ "    -o-transition: .4s,opacity .6s;\r\n"
				+ "    transition: .4s,opacity .6s;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "/* :before */\r\n"
				+ "\r\n"
				+ ".button:before {\r\n"
				+ "    content: attr(data-hover);\r\n"
				+ "    -webkit-transform: translate(-150%,0);\r\n"
				+ "    -moz-transform: translate(-150%,0);\r\n"
				+ "    -ms-transform: translate(-150%,0);\r\n"
				+ "    -o-transform: translate(-150%,0);\r\n"
				+ "    transform: translate(-150%,0);\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "/* :after */\r\n"
				+ "\r\n"
				+ ".button:after {\r\n"
				+ "    content: attr(data-active);\r\n"
				+ "    -webkit-transform: translate(150%,0);\r\n"
				+ "    -moz-transform: translate(150%,0);\r\n"
				+ "    -ms-transform: translate(150%,0);\r\n"
				+ "    -o-transform: translate(150%,0);\r\n"
				+ "    transform: translate(150%,0);\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "/* Span on :hover and :active */\r\n"
				+ "\r\n"
				+ ".button:hover span,\r\n"
				+ ".button:active span {\r\n"
				+ "    opacity: 0;\r\n"
				+ "    -webkit-transform: scale(0.3);\r\n"
				+ "    -moz-transform: scale(0.3);\r\n"
				+ "    -ms-transform: scale(0.3);\r\n"
				+ "    -o-transform: scale(0.3);\r\n"
				+ "    transform: scale(0.3);\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "/*  \r\n"
				+ "    We show :before pseudo-element on :hover \r\n"
				+ "    and :after pseudo-element on :active \r\n"
				+ "*/\r\n"
				+ "\r\n"
				+ ".button:hover:before,\r\n"
				+ ".button:active:after {\r\n"
				+ "    opacity: 1;\r\n"
				+ "    -webkit-transform: translate(0,0);\r\n"
				+ "    -moz-transform: translate(0,0);\r\n"
				+ "    -ms-transform: translate(0,0);\r\n"
				+ "    -o-transform: translate(0,0);\r\n"
				+ "    transform: translate(0,0);\r\n"
				+ "    -webkit-transition-delay: .4s;\r\n"
				+ "    -moz-transition-delay: .4s;\r\n"
				+ "    -o-transition-delay: .4s;\r\n"
				+ "    transition-delay: .4s;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "/* \r\n"
				+ "  We hide :before pseudo-element on :active\r\n"
				+ "*/\r\n"
				+ "\r\n"
				+ ".button:active:before {\r\n"
				+ "    -webkit-transform: translate(-150%,0);\r\n"
				+ "    -moz-transform: translate(-150%,0);\r\n"
				+ "    -ms-transform: translate(-150%,0);\r\n"
				+ "    -o-transform: translate(-150%,0);\r\n"
				+ "    transform: translate(-150%,0);\r\n"
				+ "    -webkit-transition-delay: 0s;\r\n"
				+ "    -moz-transition-delay: 0s;\r\n"
				+ "    -o-transition-delay: 0s;\r\n"
				+ "    transition-delay: 0s;\r\n"
				+ "}";
		assertEquals(expected,validResponse_css.getContentString());
	}

}
