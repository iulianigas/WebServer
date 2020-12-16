package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import config.DirectoryManager;

public class DirectoryManagerTest {
	
	String validDirectoryPath, invalidDirectoryPath, validFilePath;
	String validFileName, invalidFileName, validDirectoryName, invalidDirectoryName;
	
	@Before
	public void init() {
		this.validDirectoryPath = "C:/Users/Iulian/Documents/GitHub/webserver/resources/testing/File_DirectoryMaintenance/ValidDirectory";
		this.invalidDirectoryPath = "C:/Users/Iulian/Documents/GitHub/webserver/resources/testing/File_DirectoryMaintenance/InExistentDirectory";
		this.validFilePath = "C:/Users/Iulian/Documents/GitHub/webserver/resources/testing/File_DirectoryMaintenance/ValidDirectory/validFile.html";
		this.validFileName = "validFile.html"; //found in validDirectoryPath
		this.invalidFileName = "invalidFile";
		this.validDirectoryName = "validDir"; //found in validDirectoryPath
		this.invalidDirectoryName = "invalidDir";
	}

	@Test
	public void testValidIsDirectory() {
		boolean result = DirectoryManager.isDirectory(validDirectoryPath);
		assertEquals(true,result);
	}
	
	@Test
	public void testInvalidIsDirectoryInexistentDirectory() {
		boolean result = DirectoryManager.isDirectory(invalidDirectoryPath);
		assertEquals(false,result);
	}
	
	@Test
	public void testInvalidIsDirectoryUsingFile() {
		boolean result = DirectoryManager.isDirectory(validFilePath);
		assertEquals(false,result);
	}
	
	@Test
	public void testValidDirectoryContainsFile() {
		boolean result = DirectoryManager.directoryContainsFile(validDirectoryPath,validFileName);
		assertEquals(true,result);
	}
	
	@Test
	public void testInvalidDirectoryContainsFile() {
		boolean result = DirectoryManager.directoryContainsFile(validDirectoryPath,invalidFileName);
		assertEquals(false,result);
	}
	
	@Test
	public void testValidDirectoryContainsDirectory() {
		boolean result = DirectoryManager.directoryContainsDirectory(validDirectoryPath,validDirectoryName);
		assertEquals(true,result);
	}
	
	@Test
	public void testInvalidDirectoryContainsDirectory() {
		boolean result = DirectoryManager.directoryContainsDirectory(validDirectoryPath,invalidDirectoryName);
		assertEquals(false,result);
	}
	
	

}
