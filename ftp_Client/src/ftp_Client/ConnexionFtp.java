
package ftp_Client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class ConnexionFtp {
	 private FTPClient ftpClient ;
	 
	 ConnexionFtp() throws SocketException, IOException{
		 this.ftpClient = new FTPClient();
		 ftpClient.connect("localhost" , 21 );
         ftpClient.login("kahina","");
         System.out.println("Connection etablie");
         ftpClient.changeWorkingDirectory("/");
	 }

	public FTPClient getFtpClient() {
		return ftpClient;
	}

	public void setFtpClient(FTPClient ftpClient) {
		this.ftpClient = ftpClient;
	}
	
	public void getFileOrDirectory(String remote, String local) throws IOException {
        //FTPFile[] content = this.getFtpClient().listFiles(remote);
       /*for( FTPFile file:content ){
		
        	if(file.isFile()) {
        		local+=file.getName();
        		OutputStream output;
        		output = new FileOutputStream(local);
        		this.getFtpClient().retrieveFile(remote, output);
                System.out.println(  " File :"+remote+" created in :"+local );
        	}else if(file.isDirectory()) {
        		local+= '\\'+file.getName();
        		
                new File(local).mkdirs();
                System.out.println(  " Directory :"+file.getName()+" created in :"+local );

               getFileOrDirectory(remote+"/"+ file.getName(), local );
        	}
        }*/
        
        
		try{
            FTPFile[] files = this.ftpClient.listFiles(remote);
            for ( FTPFile file : files){

                if(file.isFile()){
                    System.out.println(file.getName() + " est un fichier");

                    String newLocal = local + '\\' + file.getName();
                    try(OutputStream output = new FileOutputStream(newLocal))
                    {
                        this.getFtpClient().retrieveFile(remote + '\\' + file.getName(), output);//output endroit ou il met le fichier
                    }//récuperartion du file dans le dossier remote
                    catch(FileNotFoundException ex){
                        ex.printStackTrace();//affichage des erreurs à l'ouverture des fichiers
                    }
                    catch(IOException ex){
                        ex.printStackTrace();
                    }
                }
                else if(file.isDirectory()){
                    System.out.println(file.getName() + " est un dossier.");
                    String newLocal  = local + '\\'+ file.getName();
                    new File(newLocal).mkdirs();
                    System.out.println("dossier créer");
                    System.out.println(remote + '/'+ file.getName());// 
                    getFileOrDirectory(remote + '/'+ file.getName(),newLocal);// on va lister tout ce qui se trouve dans le sous-repertoire

                }
            }
        }
        catch(IOException ex){
            ex.printStackTrace();

        }
		
	}
   
	public void displayDir(String path) throws IOException{
		if(this.ftpClient != null){
            FTPFile[] content = this.getFtpClient().listFiles(path);

            for( FTPFile item:content ){
                if(item.isFile()){
                    System.out.println(  " File:" + item.getName()  );
                }else if(item.isDirectory()){
                    System.out.println(  " Dir:" + item.getName() + ": " );
                    displayDir( path + "/" + item.getName() );
                    System.out.println("End dir");
                }
            }
        }
    }

}
