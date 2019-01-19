/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftp_Client.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;

import ftp_Client.DiffFileComp;
import ftp_Client.tools;

/**
 *
 * @author esteban
 */
public class FileLookup {
    private File syncDir;
    
    public FileLookup(String s){
        syncDir = new File(s);
    }
    
    
    
    public static ArrayList<DiffFileComp> list(File file, File ogFile) {
    	if( ogFile == null ) {
    		ogFile = file;
    	}
    	ArrayList<DiffFileComp> List = new ArrayList<DiffFileComp>();
    	
    	File[] content = file.listFiles();
        for( File item:content ){
            if(item.isFile()){
            	BasicFileAttributes attr = null;
				try {
					attr = Files.readAttributes(item.toPath(), BasicFileAttributes.class);
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
                
                DiffFileComp el = new DiffFileComp( item.getPath() ,tools.relativePath(ogFile,item), new Date(attr.lastModifiedTime().toMillis() ) );
                List.add( el );
                
            }else if( item.isDirectory() ){
                List.addAll( list(item,file) );
            }
        }
        return List;
    }
    
    public static void removeFile( String path ){
    	try{
		    Files.delete( Paths.get(path) );
		 }
		 catch(FileNotFoundException ex){
		     ex.printStackTrace();
		 }
		 catch(IOException ex){
		     ex.printStackTrace();
		 }
    }
    
    public static void displayDir(File file){
        File[] content = file.listFiles();
        for( File item:content ){
            if(item.isFile()){
            	BasicFileAttributes attr = null;
				try {
					attr = Files.readAttributes(item.toPath(), BasicFileAttributes.class);
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
                System.out.println( attr.lastModifiedTime() + " File:" + item.getName()  );
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
