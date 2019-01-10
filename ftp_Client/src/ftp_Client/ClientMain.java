package ftp_Client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Dimension;

public class ClientMain extends JFrame {

	 private javax.swing.JPanel jContentPane = null;
	 private JTree              jTreeLocal        = null;
	 private JTree              jTreeRemote        = null;	 
	   JLabel l1, l2, l3;
       JTextField tf1;
       JButton btn1;
       JPasswordField p1;
	 
	ClientMain(){
		super ("ClientFtp");
	      WindowListener l = new WindowAdapter() {
	          public void windowClosing(WindowEvent e){
	             System.exit(0);
	          }
	       };
	       
	       
	       //
	    
	      
	        JFrame frame = new JFrame("Login Form");
	        l1 = new JLabel("Login Form");
	        l1.setForeground(Color.blue);
	        l1.setFont(new Font("Serif", Font.BOLD, 20));
	       
	        l2 = new JLabel("Username");
	        l3 = new JLabel("Password");
	        tf1 = new JTextField();
	        p1 = new JPasswordField();
	        btn1 = new JButton("Login");
	       
	        l1.setBounds(100, 30, 400, 30);
	        l2.setBounds(80, 70, 200, 30);
	        l3.setBounds(80, 110, 200, 30);
	        tf1.setBounds(300, 70, 200, 30);
	        p1.setBounds(300, 110, 200, 30);
	        btn1.setBounds(150, 160, 100, 30);
	       
	        frame.add(l1);
	        frame.add(l2);
	        frame.add(tf1);
	        frame.add(l3);
	        frame.add(p1);
	        frame.add(btn1);
	       
	        frame.setSize(400, 400);
	        frame.setLayout(null);
	        frame.setVisible(true);

	       
	       //

	       addWindowListener(l);
	       setSize(200,100);
	       setVisible(true);
	       
	       //Instancier la fênetre principale
			final JPanel composantsAPlacer = new JPanel();
			GridLayout grid = new GridLayout(2,3);
			composantsAPlacer.setLayout(grid);

			

			// Instancier le champs login password
			
			JTextField  testField1 = new JTextField ("mon texte");
			composantsAPlacer.add(testField1);
			
			
			
			JPasswordField  passwordField1 = new JPasswordField ("");
			passwordField1.setPreferredSize(new Dimension(50,20 ));
			composantsAPlacer.add(passwordField1);
			composantsAPlacer.add(new JButton("Button "));

			
			
			
			//composantsAPlacer.add(new JButton("Button 2 "));
			composantsAPlacer.add(getLocal());

			JPanel panel2 = new JPanel();
			GridLayout grid2 = new GridLayout(3,3);

			panel2.setLayout(grid2);

			composantsAPlacer.add(getRemote());

			panel2.add(new JButton("Button 4 "));
			panel2.add(new JButton("Button 5 "));
			panel2.add(new JButton("Button 6"));
			panel2.add(new JButton("Button 7"));
			panel2.add(new JButton("Button 8 "));

			this.setContentPane(composantsAPlacer);

	  
	}

	private JTree getLocal() {
		if (jTreeLocal == null) {
			jTreeLocal = new JTree();
		}
		return jTreeLocal;
	}
	private JTree getRemote() {
		if (jTreeRemote == null) {
			jTreeRemote = new JTree();
		}
		return jTreeRemote;
	}


	private javax.swing.JPanel getJContentPane() {
		if (jContentPane == null) {
		    jContentPane = new javax.swing.JPanel();
		    jContentPane.setLayout(new java.awt.BorderLayout());
		    jContentPane.add(getLocal(), java.awt.BorderLayout.CENTER);
		  }
		 return jContentPane;
	}
	

	
	public static void main(String[] args) {
		
		JFrame frame = new ClientMain();

				
		
	//	Client client = new Client("127.0.0.1", 21);
		String local = "C:\\ftp\\testFTP";

		ConnexionFtp connexion_server= null;
       
        try {
        	connexion_server = new ConnexionFtp();
            //directories =  ftpClient.listDirectories();
            //directories =  ftpClient.changeWorkingDirectory(pathname);
        	
        	
			
            String filePath = "/test";
            
            /*Ebauche de la fonction UploadFileOrDirectory
            String remote="testserv.txt";
            String local2= "C:\\Users\\Kahina\\Desktop\\test2.txt";
            InputStream input = new FileInputStream(local2);
            connexion_server.getFtpClient().storeFile( remote,  input);
            */
            String remote="/";
            String local2= "C:\\Users\\Kahina\\Desktop\\test";
            
            //test fonction upload
            //connexion_server.uploadFileOrDirectory(remote, local2);
            
             //tester la fonction download
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
	
	}

}
