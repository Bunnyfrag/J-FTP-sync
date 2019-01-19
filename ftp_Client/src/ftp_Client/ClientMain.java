package ftp_Client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

import ftp_Client.helper.FileLookup;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.*;
import java.awt.Dimension;

public class ClientMain extends JFrame implements ActionListener{

	 private javax.swing.JPanel jContentPane = null;
	 private JTree              jTreeLocal        = null;
	 private JTree              jTreeRemote        = null;	 
	   JLabel l1, l2, l3;
       JTextField tf1;
       JButton btn1;
       JPasswordField p1;
       AbstractButton t1 = null;
       String uname ="";
       String password="";

	 
	ClientMain(){
		super ("ClientFtp");
	      WindowListener l = new WindowAdapter() {
	          public void windowClosing(WindowEvent e){
	             System.exit(0);
	          }
	       };
	       
	   
	       final JPanel composantsAPlacer = new JPanel();

	        l1 = new JLabel("Login Form");
	        l1.setForeground(Color.blue);
	        l1.setFont(new Font("Serif", Font.BOLD, 20));
	       
	        l2 = new JLabel("Username");
	        l3 = new JLabel("Password");
	        tf1 = new JTextField();
	        p1 = new JPasswordField();
	        btn1 = new JButton("Login");
	        
	   
	        l1.setBounds(100, 30, 400, 30);
	        l2.setBounds(80, 70, 100, 30);
	        l3.setBounds(307, 70, 83, 30);
	        tf1.setBounds(192, 70, 100, 30);
	        p1.setBounds(389, 70, 100, 30);
	        btn1.setBounds(539, 70, 100, 30);
	       
	        composantsAPlacer.add(l1);
	        composantsAPlacer.add(l2);
	        composantsAPlacer.add(tf1);
	        composantsAPlacer.add(l3);
	        composantsAPlacer.add(p1);
	        composantsAPlacer.add(btn1);
	        
			
	        btn1.addActionListener(this);
	       
	        composantsAPlacer.setSize(623, 416);
	        composantsAPlacer.setLayout(null);
	        composantsAPlacer.setVisible(true);

	       
	       

	       addWindowListener(l);
	       setSize(800,707);
	       setVisible(true);
	       
	       //Instancier la fênetre principale
			/*final JPanel composantsAPlacer = new JPanel();
			GridLayout grid = new GridLayout(2,3);
			composantsAPlacer.setLayout(grid);

			

			// Instancier le champs login password
			
			JTextField  testField1 = new JTextField ("mon texte");
			composantsAPlacer.add(testField1);
			
			
			
			JPasswordField  passwordField1 = new JPasswordField ("");
			passwordField1.setPreferredSize(new Dimension(50,20 ));
			composantsAPlacer.add(passwordField1);
			JButton    but=new JButton("ici");
			composantsAPlacer.add(but);
			
			but.addActionListener(this);*/

			
			//composantsAPlacer.add(new JButton("Button 2 "));
			composantsAPlacer.add(getLocal());

			composantsAPlacer.add(getRemote());

			
			this.setContentPane(composantsAPlacer);
			
			JTree tree = new JTree();
			tree.setBounds(0, 206, 500, 445);
			composantsAPlacer.add(tree);

	  
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

				
		
	//Client client = new Client("127.0.0.1", 21);
		String local = "C:\\ftp";
		//String local = "C:\\Users\\Kahina\\Téléchargements";
	     //File inputFile = new File("C:\\ftp\\USERDATA.txt");

		DiffList a = new DiffList( FileLookup.list( new File(local),null ), local );
		DiffList b = null;
		System.out.println( "a: " + a );

		ConnexionFtp connexion_server= null;
       
        try {
        	connexion_server = new ConnexionFtp();
            //directories =  ftpClient.listDirectories();
            //directories =  ftpClient.changeWorkingDirectory(pathname);
        	
            //Scanner in = new Scanner(new File("C:\\ftp\\USERDATA.txt"));

			
            String filePath = "/test";
            
    
            String remote="/";
            //String local2= "C:\\Users\\Kahina\\Desktop\\test";
            String local2="/";
        
            //test fonction upload
            //connexion_server.uploadFileOrDirectory(remote, local2);
            
             //tester la fonction download
             //connexion_server.downloadFileOrDirectory(filePath,local);
             //test fonction compare
             b = new DiffList( connexion_server.list(local2,null), local2 );
             
             connexion_server.removeFileOrDirectory(local,true);
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
       // System.out.println( "b: " + b );
       // a.compare( b, connexion_server, false, false );
	
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
        uname= tf1.getText();
        password=p1.getText();
    	System.out.println(uname);

        if(uname.equals("nnn") && password.equals(""))
        {
           //Welcome wel = new Welcome();
           //wel.setVisible(true);
           JLabel label = new JLabel("Welcome:"+uname);
           ///wel.getContentPane().add(label);
         }
         else
         {
           JOptionPane.showMessageDialog(this,"Incorrect login or password",
           "Error",JOptionPane.ERROR_MESSAGE); 
         }
		
	}
}
