package ftp_Client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.*;

public class ClientMain {

	
	public static void main(String[] args) {
	//	Client client = new Client("127.0.0.1", 21);
		String local = "C:\\ftp";

		ConnexionFtp connexion_server= null;
       
        try {
        	connexion_server = new ConnexionFtp();
            //directories =  ftpClient.listDirectories();
            //directories =  ftpClient.changeWorkingDirectory(pathname);
        	
            String filePath = "/commons-net-3.6";

            connexion_server.getFileOrDirectory(filePath,local);
            //String time = connexion_server.getFtpClient().getModificationTime(filePath);
            //System.out.println("Server Reply: " + time);
            
            /*for(FTPFile directory : directories)
            {
                System.out.println(directory.getName());
                FTPFile[] files = new FTPFile[1000];
                files = connexion_server.getFtpClient().listFiles(directory.getName());
                for(FTPFile file : files)
                {
                    System.out.println("|_"+file.getName());
                }
            }*/
            
            //OutputStream output;
           // output = new FileOutputStream(local);
          //  connexion_server.getFtpClient().retrieveFile("NombreAleatoire", output);
            connexion_server.getFtpClient().logout();

        } catch (IOException e) {
            System.err.println(e.getMessage());
            

        }
        if((connexion_server==null)) {
        	System.out.println("Error Null pointer");
        }
        
        /*try {
			connexion_server.displayDir("/");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}*/
	}

}
