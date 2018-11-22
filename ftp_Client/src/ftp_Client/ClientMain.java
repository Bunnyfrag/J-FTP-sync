package ftp_Client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.*;

public class ClientMain {

	
	public static void main(String[] args) {
	//	Client client = new Client("127.0.0.1", 21);
		String local = "C:\\ftp\\NombreAleatoire";

		
        FTPClient ftpClient = new FTPClient();
        FTPFile[] directories = new FTPFile[1000];
        try {
            ftpClient.connect("localhost" , 21 );
            ftpClient.login("kahina","");
            System.out.println("Connection etablit");
            directories =  ftpClient.listDirectories();

            for(FTPFile directory : directories)
            {
                System.out.println(directory.getName());
                FTPFile[] files = new FTPFile[1000];
                files = ftpClient.listFiles(directory.getName());
                for(FTPFile file : files)
                {
                    System.out.println("|_"+file.getName());
                }
            }
            OutputStream output;
            output = new FileOutputStream(local);
            ftpClient.retrieveFile("NombreAleatoire", output);
        } catch (IOException e) {
            System.err.println(e.getMessage());

        }
	}
}
