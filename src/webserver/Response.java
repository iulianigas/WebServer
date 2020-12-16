package webserver;

import java.util.Arrays;

public class Response  {

	private String httpVersion;
    private String statusCode;
	private String contentType;
	private byte[] contentBytes;
	private String contentString;
	
	public Response(String httpVersion, String statusCode, String contentType, byte[] contentBytes, String contentString) {
		this.httpVersion=httpVersion;
		this.statusCode=statusCode;
		this.contentType=contentType;
		this.contentBytes=contentBytes.clone();
		this.contentString=contentString;
	}

	public String getHttpVersion() {
		return httpVersion;
	}
	
	public String getStatusCode() {
		return statusCode;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public byte[] getContentBytes() {
		return contentBytes.clone();
	}
	
	public String getContentString() {
		return contentString;
	}

	@Override
	public String toString() {
		return "Response [httpVersion=" + httpVersion + ", statusCode=" + statusCode + ", contentType=" + contentType
				+ ", contentString=" + contentString + ", contentBytes=" + Arrays.toString(contentBytes) + "]";
	}
	
	


	
}