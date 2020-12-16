package test;

import org.junit.Test;
import exceptions.webserver_exceptions.WebServerStateTransitionException;
import webserver.WebServerState;

public class WebServerStateTest {

	@Test
	public void testValidSetRunningFromStopped() throws WebServerStateTransitionException {
		WebServerState.setStopped();
		WebServerState.setRunning();		
	}

	@Test (expected = WebServerStateTransitionException.class)
	public void testInvalidSetMaintenanceFromStopped() throws WebServerStateTransitionException{
		WebServerState.setStopped();
		WebServerState.setMaintenance();		
	}
	
	@Test 
	public void testValidSetMaintenanceFromRunning() throws WebServerStateTransitionException{
		WebServerState.setStopped();
		WebServerState.setRunning();
		WebServerState.setMaintenance();	
	}
	
	@Test 
	public void testValidSetRunningFromMaintenance() throws WebServerStateTransitionException{
		WebServerState.setStopped();
		WebServerState.setRunning();
		WebServerState.setMaintenance();
		WebServerState.setRunning();
	}

}
