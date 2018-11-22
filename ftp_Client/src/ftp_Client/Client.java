package ftp_Client;

import java.io.IOException;
import java.net.Socket;

import ftp_Client.helper.Communication;

import org.apache.commons.net.ftp.*;

public class Client {

	private ClientAction runnable;

	public Client(String adress, int port) {
		run(adress, port);
	}
	


	private void run(String adress, int port) {

		try  {
			Socket clientSocket = new Socket(adress, port);
			runnable = new ClientAction(clientSocket);
			new Thread(runnable).start();
			Communication.ecriture(clientSocket, "Salut je suis client ");
			Thread.sleep(1000);
			runnable.terminate();

		} catch (IOException | InterruptedException e) {
			System.out.println(e.getMessage());
		}finally {
		}
	}
}
