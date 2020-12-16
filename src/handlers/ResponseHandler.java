package handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

import parsers.ResponseParser;
import webserver.Request;
import webserver.Response;

public class ResponseHandler {

	public static void sendResponse(Socket clientSocket, OutputStream clientOutput, Response response) throws IOException {
        clientOutput.write((response.getHttpVersion()+" \r\n" + response.getStatusCode()).getBytes(Charset.forName("UTF-8")));
        clientOutput.write(("ContentType: " + response.getContentType() + "\r\n").getBytes(Charset.forName("UTF-8")));
        clientOutput.write("\r\n".getBytes(Charset.forName("UTF-8")));
        clientOutput.write(response.getContentBytes());
        clientOutput.write("\r\n\r\n".getBytes(Charset.forName("UTF-8")));
        clientOutput.close();
        clientSocket.close();
    }
	
	public static Response getResponse(String rootDir, Request request) throws IOException {
		ResponseParser rp = new ResponseParser(rootDir,request);
		return new Response(rp.getHttpVersion(),
				rp.getStatusCode(),
				rp.getContentType(),
				rp.getContentByte(),
				rp.getContentString());
	}

}
