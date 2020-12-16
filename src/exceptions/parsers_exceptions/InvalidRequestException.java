package exceptions.parsers_exceptions;

public class InvalidRequestException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidRequestException(String message) {
		super(message);
	}
	
	public InvalidRequestException() {
		super("Invalid Request Parser Exception");
	}
	
}
