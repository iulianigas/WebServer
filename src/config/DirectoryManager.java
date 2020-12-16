package config;

import java.io.File;

public class DirectoryManager {
	
	public static boolean isDirectory(String directoryPath) {
		File f = new File(directoryPath);
		if(f.exists() && f.isDirectory() && !f.isFile()) { 
		    return true;
		}
		return false;
	}
	
	public static boolean directoryContainsFile(String directoryPath, String fileName) {
		String filePath = directoryPath + "/" +fileName;
		File f = new File(filePath);
		if(f.exists() && f.isFile() && !f.isDirectory()) { 
		    return true;
		}
		return false;
	}
	
	public static boolean directoryContainsDirectory(String directoryPath, String directoryName) {
		String newDirectoryPath = directoryPath + "/" + directoryName;
		File f = new File(newDirectoryPath);
		if(f.exists() && f.isDirectory() && !f.isFile()) { 
		    return true;
		}
		return false;
	}
}
