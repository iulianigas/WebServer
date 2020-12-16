package webserver;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TerminalInterface {
	
	public static void printServerSettingsMenu(){
	    System.out.println("***************Server settings***************");
	    System.out.println("Current state: " + WebServerState.getCurrentState());
	    System.out.println("* Set to state 0 (Stopped)");
	    System.out.println("* Set to state 1 (Running)");
	    System.out.println("* Set to state 2 (Maintenance)");
	    System.out.println("* Exit program: x");
	    System.out.println("*********************************************");
	    System.out.println("Enter your option:");
	}
	
	public static void editConfigurationOption() {
		System.out.println("---------------- SETUP --------------------");
		System.out.println("Do you wish to edit your configurations? (y/n)");
		System.out.println("-------------------------------------------");
	    System.out.println("Enter your option:");
	}
	
	public static void editPortNumber() {
		System.out.println("Enter new port value:");
	}
	
	public static void editRootDirectory() {
		System.out.println("Enter new root directory:");
	}
	
	public static void editMaintenanceDirectory() {
		System.out.println("Enter new maintenance directory:");
	}
	
	public static void editConfiguration() {
		Scanner scanner = new Scanner(System.in,StandardCharsets.UTF_8);
		editConfigurationOption();
		char result = scanner.next().charAt(0);
		switch(result) {
		case 'n':
			break;
		case 'y':
			break;
		}
		scanner.close();
	}
	
	public static void warningInvalidValue() {
		System.out.println("TRY AGAIN! The value entered was invalid!");
	}
	
	public static void outputMessage(String str) {
		//if(DefaultValues.debug==1) 
		System.out.println("Message: " + str);
		
	}
	
	public static void outputError(String str) {
		//if(DefaultValues.debug==2) 
		System.out.println("Error: " + str);
	}
	
	public static void outputWarning(String str) {
		//if(DefaultValues.debug==3) 
		System.out.println("Warning: " + str);
	}
	
	
	
}
