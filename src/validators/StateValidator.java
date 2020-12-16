package validators;

import exceptions.webserver_exceptions.WebServerStateTransitionException;

public class StateValidator {
	
	private static final int running = 2;
	
	public static boolean validateSetStopped(int currentState) {
		//can be set to stop no matter the current state
		return true;
	}
	
	public static boolean validateSetRunning(int currentState) {
		//can be set to running no matter the current state
		return true;
	}
	
	public static boolean validateSetMaintenance(int currentState) throws WebServerStateTransitionException {
		//can be set to maintenance if running
		if(currentState!=running) {
			return false;
		}
		return true;
	}
}
