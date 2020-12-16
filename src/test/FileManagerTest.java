package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import config.FileManager;

public class FileManagerTest {

	String validFile, invalidFile, validDirectory;
	String expectedContent, unexpectedContent;
	
	@Before
	public void init() {
		this.validFile = "C:/Users/Iulian/Documents/GitHub/webserver/resources/testing/File_DirectoryMaintenance/ValidDirectory/validFile.html";
		this.invalidFile = "C:/Users/Iulian/Documents/GitHub/webserver/resources/testing/File_DirectoryMaintenance/InExistentFile";
		this.validDirectory = "C:/Users/Iulian/Documents/GitHub/webserver/resources/testing/File_DirectoryMaintenance/ValidDirectory";
		
		this.expectedContent = "<html>\r\n"
				+ " <p> hello </p>\r\n"
				+ "</html>";
		
		this.unexpectedContent = "<html>\r\n"
				+ " <p> something else</p>\r\n"
				+ "</html>";	
	}

	@Test
	public void testValidIsFile() {
		boolean result = FileManager.isFile(validFile);
		assertEquals(true,result);
	}
	
	@Test
	public void testInvalidIsFileInexistentFile() {
		boolean result = FileManager.isFile(invalidFile);
		assertEquals(false,result);
	}
	
	@Test
	public void testInvalidIsFileUsingDirectory() {
		boolean result = FileManager.isFile(validDirectory);
		assertEquals(false,result);
	}
	
	@Test
	public void testValidGetContent() throws IOException {
		FileManager.writeContentToFile(validFile, expectedContent);

		String result = FileManager.getContent(validFile);
		assertEquals(expectedContent,result);
	}
	
	@Test
	public void testInvalidGetContent() throws IOException {
		FileManager.writeContentToFile(validFile, expectedContent);
		
		String content = FileManager.getContent(validFile);
		boolean result = unexpectedContent.equals(content);
		assertEquals(false,result);
	}
	
	@Test( expected = IOException.class)
	public void testInvalidGetContentFromInvalidFile() throws IOException {
		FileManager.getContent(invalidFile);
	}
	
	@Test
	public void testWriteContentToExistingFile() throws IOException {
		FileManager.writeContentToFile(validFile, expectedContent);
		assertEquals(expectedContent,FileManager.getContent(validFile));
	}
	
	@Test
	public void testWriteContentToNonExistingFile() throws IOException {
		FileManager.writeContentToFile(invalidFile, expectedContent);
		assertEquals(expectedContent,FileManager.getContent(validFile));
	}

}
