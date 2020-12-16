package validators;

public class RequestParserValidator {

	public static boolean validateResource(String resource) {
		if(resource.startsWith("/"))
			return true;
		return false;
	}
	
	public static boolean validateHost(String host) {
		if(host.startsWith("localhost:") || host.startsWith("127.0.0.1:"))
			return true;
		return false;
	}
	
	public static boolean validateMethod(String method) {
		if(method.equals("GET"))
			return true;
		return false;
	}
	
	public static boolean validateHttpVersion(String http) {
		return true;
	}
}
