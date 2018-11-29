/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftp_Client.helper;

import java.io.File;

/**
 *
 * @author esteban
 */
public class FileLookup {
    private File syncDir;
    
    public FileLookup(String s){
        syncDir = new File(s);
    }
    
    public static void displayDir(File file){
        File[] content = file.listFiles();
        for( File item:content ){
            if(item.isFile()){
                System.out.println( item.getPath() + " File:" + item.getName()  );
            }else if(item.isDirectory()){
                System.out.println( item.getPath() + " Dir:" + item.getName() + ": " );
                displayDir(item);
                System.out.println("End dir");
            }
        }
    }
    
    public void display(){
        displayDir(syncDir);
    }
}
