package validators;

import config.DirectoryManager;
import webserver.DefaultValues;

public class MaintenanceDirectoryValidator {
	
	public static boolean validate(String maintenanceDirectory) {
		if (DirectoryManager.isDirectory(maintenanceDirectory)) {
			if(DirectoryManager.directoryContainsFile(maintenanceDirectory, DefaultValues.getHomePage())) {
				return true;
			}			
		}
		return false;
	}
}
