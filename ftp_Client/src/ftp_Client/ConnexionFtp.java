
package ftp_Client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
	
	public void uploadFileOrDirectory(String remote, String local) {
		try{
			File folder = new File(local);
			if(folder.isDirectory()) {
                String newRemote  = remote + '\\'+ folder.getName();

                this.getFtpClient().makeDirectory(newRemote);
                this.getFtpClient().changeWorkingDirectory(newRemote);
                File[] listOfFiles = folder.listFiles();
                
                for ( File file : listOfFiles){
		            	if(file.isFile()){
		                    //System.out.println(file.getName() + " est un fichier");
		                    try(InputStream input = new FileInputStream(file.getAbsolutePath())){
		                        this.getFtpClient().storeFile(file.getName(), input);	//input endroit ou il recupere le fichier
		                    }																			//recuperartion du file dans le dossier remote
		                    catch(FileNotFoundException ex){
		                        ex.printStackTrace();													//affichage des erreurs a l'ouverture des fichiers
		                    }
		                    catch(IOException ex){
		                        ex.printStackTrace();
		                    }
		            	}
		            	else if(file.isDirectory()){
		                    //System.out.println(file.getName() + " est un dossier.");
		                    String newPath  = this.getFtpClient().printWorkingDirectory() + '\\'+ file.getName();
		                    this.getFtpClient().makeDirectory(newPath);
		                    //System.out.println("dossier cree");
		                    //System.out.println(local + '/'+ file.getName());// 
		                    uploadFileOrDirectory(newRemote ,local + '/'+ file.getName());// on va lister tout ce qui se trouve dans le sous-repertoire
		                    this.getFtpClient().changeToParentDirectory();
		                }
	            	} 
                }
			else {
			    try(InputStream input = new FileInputStream(folder.getAbsolutePath())){
                    this.getFtpClient().storeFile(folder.getName(), input);	//input endroit ou il recupere le fichier
                }																			//recuperartion du file dans le dossier remote
                catch(FileNotFoundException ex){
                    ex.printStackTrace();													//affichage des erreurs a l'ouverture des fichiers
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
			}
			
			}catch(Exception ex){
                ex.printStackTrace();
			}
		
	}
	
	public void getFileOrDirectory(String remote, String local) throws IOException {       
		try {
            FTPFile[] files = this.ftpClient.listFiles(remote);
            if(files.length == 0) {
            	 try(OutputStream output = new FileOutputStream(local))
                 {
                     this.getFtpClient().retrieveFile(remote, output);//output endroit ou il met le fichier
                 }//recuperartion du file dans le dossier remote
                 catch(FileNotFoundException ex){
                     ex.printStackTrace();//affichage des erreurs a l'ouverture des fichiers
                 }
                 catch(IOException ex){
                     ex.printStackTrace();
                 }
            }
            else {
            	//crée dossier puis parcourir dossier 
            	String[] splitResult = remote.split("/");
            	File folder = new File(local +'\\'+ splitResult[splitResult.length-1]);
            	folder.mkdirs();
            	for ( FTPFile file : files){
                    if(file.isFile()){
                        //System.out.println(file.getName() + " est un fichier");
                        String newLocal = folder.getAbsolutePath() + '\\' +  file.getName();
                        try(OutputStream output = new FileOutputStream(newLocal))
                        {
                            this.getFtpClient().retrieveFile(remote + '\\' + file.getName(), output);//output endroit ou il met le fichier
                        }//recuperartion du file dans le dossier remote
                        catch(FileNotFoundException ex){
                            ex.printStackTrace();//affichage des erreurs a l'ouverture des fichiers
                        }
                        catch(IOException ex){
                            ex.printStackTrace();
                        }
                    }
                    else if(file.isDirectory()){
                        
                        String newLocal  =  folder.getAbsolutePath();
                        
                        System.out.println("dossier cree");
                        System.out.println("Remote : " + remote + '/'+ file.getName());//
                        System.out.println("local : " + newLocal);//
                        getFileOrDirectory(remote + '/'+ file.getName(),newLocal);// on va lister tout ce qui se trouve dans le sous-repertoire
                    }
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
