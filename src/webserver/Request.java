package webserver;


public class Request  {
	
	  private String method;
	  private String resourcePath;
	  private String httpVersion;
	  private String host;
	 
	  public Request(String method, String resourcePath, String httpVersion, String host)  {
	    this.method = method;
	    this.resourcePath = resourcePath;
	    this.httpVersion = httpVersion;
	    this.host = host;
	  }
	
	  public String getMethod()  {
	    return this.method;
	  }
	
	  public String getResourcePath()  {
	    return this.resourcePath;
	  }
	
	  public String getHttpVersion()  {
	    return this.httpVersion;
	  }
	  
	  public String getHost()  {
		return this.host;
	  }
	
	  @Override
	  public String toString() {
		return "Request [method=" + method 
				+ ", resourcePath=" + resourcePath 
				+ ", httpVersion=" + httpVersion 
				+ ", host="+ host + "]";
	  }
 

  
}
