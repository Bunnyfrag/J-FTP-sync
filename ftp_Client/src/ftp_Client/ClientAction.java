package ftp_Client;

import java.io.IOException;
import java.net.Socket;

import ftp_Client.helper.Communication;

public class ClientAction implements Runnable {

	private Socket socket;
	private Boolean running;
	public  ClientAction(Socket socket) {
		this.socket = socket;
		this.running =true;
	}
	
	public void terminate() {
		this.running = false;
	}
	@Override
	public void run()
	{	
		while(running) {
			try {
			System.out.println("Server sent : "+ Communication.lecture(socket));
			}
			catch (IOException e) {
				
				System.out.println("Exception " + e.getMessage());
			}
		}
	}
	
	

}
