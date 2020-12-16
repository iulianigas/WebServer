package config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {
	
	public static boolean isFile(String filePath) {
		File f = new File(filePath);
		if(f.exists() && f.isFile() && !f.isDirectory()) { 
		    return true;
		}
		return false;
	}
	
	public static String getContent(String filePath) throws IOException {
		//System.out.println("filePath: "+filePath);
		try {
			if(isFile(filePath)) {
				String content = new String(Files.readAllBytes(Paths.get(filePath)),StandardCharsets.UTF_8);
				return content;
			}else {
				throw new IOException("A file was not found at the given path '"+filePath+"'");
			}
		}
		catch (IOException e){
			throw new IOException("Error occured while getting file content.");
		}
	}
	
	public static byte[] getContentBytes(String filePath) throws IOException {
		//System.out.println("filePath: "+filePath);
		try {
			if(isFile(filePath)) {
				return Files.readAllBytes(Paths.get(filePath));

			}else {
				throw new IOException("A file was not found at the given path '"+filePath+"'");
			}
		}
		catch (IOException e){
			throw new IOException("Error occured while getting file content.");
		}
	}
	
	public static void writeContentToFile(String filePath, String content) throws IOException {
		FileWriter myWriter = new FileWriter(filePath,StandardCharsets.UTF_8);
		try {
		    myWriter.write(content);
		} catch (IOException e) {
			throw new IOException("Error occured while writing content to file.");
	    } finally {
	    	myWriter.close();
	    }
	}	
	
}
