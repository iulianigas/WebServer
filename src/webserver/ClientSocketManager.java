package webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientSocketManager {

	private Socket clientSocket;
	private BufferedReader clientInput;
	private OutputStream clientOutput;
	
	public ClientSocketManager (Socket clientSocket) throws IOException {
		this.clientSocket = clientSocket;
		this.clientInput = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream(),StandardCharsets.UTF_8));
		this.clientOutput = this.clientSocket.getOutputStream();
	}
	
	public Socket getClientSocket() {
		return this.clientSocket;
	}
	
	public BufferedReader getClientInput() {
		return this.clientInput;
	}
	
	public OutputStream getClientOutput() {
		return this.clientOutput;
	}
	
	public int getPortNumber() {
		return this.clientSocket.getPort();
	}
	
	public String getHost() {
		return this.clientSocket.getInetAddress().toString();
	}
	
	public void closeSocket() throws IOException {
		this.clientSocket.close();
	}
	
	public void closeAll() throws IOException {
		this.clientInput.close();
		this.clientOutput.close();
		this.clientSocket.close();
	}
	
}
