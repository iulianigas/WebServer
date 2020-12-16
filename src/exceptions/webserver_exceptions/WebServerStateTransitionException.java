package exceptions.webserver_exceptions;

public class WebServerStateTransitionException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public WebServerStateTransitionException() {
		super("State transition failed");
	}
}
