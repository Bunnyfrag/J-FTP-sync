package ftp_Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import ftp_Client.helper.FileLookup;

public class DiffList extends java.lang.Object implements java.lang.Cloneable {
	
	private ArrayList<DiffFileComp> List;
	private String dirPath;
	public DiffList(ArrayList<DiffFileComp> list , String dir) {
		super();
		List = list;
		dirPath = dir;
	}

	public ArrayList<DiffFileComp> getList() {
		return List;
	}

	public void setList(ArrayList<DiffFileComp> list) {
		List = list;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		String res = "";
		for (DiffFileComp file : List) {
			res += file.toString() + " | ";
		}
		return res;
	}

	/*
	 * Comparer la liste locale Ã  la distante
	 */
	public void compare(DiffList otherList, ConnexionFtp ftp, boolean cleanLoc, boolean cleanDist) {
		
		
		ArrayList<DiffFileComp> diffBuffer;
		diffBuffer = (ArrayList<DiffFileComp>) otherList.List.clone();
		
		for (DiffFileComp file : List) {
			
			Date compDate = file.getLastEdit();
			String fileId = file.getFullPath();
			boolean found = false;
			
			for (DiffFileComp file2 : diffBuffer) {
				if( file2.getFullPath().equals( fileId ) ) {
					if( compDate.compareTo( file2.getLastEdit() ) < 0) {
						//dl de fileId
						
						System.out.println( " < dl" + fileId );
						ftp.downloadFileOrDirectory(fileId, otherList.dirPath+tools.relativePath(this.dirPath, fileId));
						
					}else if( compDate.compareTo( file2.getLastEdit() ) > 0) {
						//upload de fileId
						
						System.out.println( "up > " + fileId );
						//ftp.uploadFileOrDirectory(otherList.dirPath+tools.relativePath(this.dirPath, fileId), fileId);
						ftp.uploadFileOrDirectory(otherList.dirPath, fileId);
						
					} 
					diffBuffer.remove(file2);
					found = true;
					break;
					
				}
			}
			
			if(!found) {
				
				//si cleanup local
				if( cleanLoc ) {
					System.out.println( " >x  " + fileId );
					FileLookup.removeFile(fileId);
				}else{
					//upload de fileId
					System.out.println( " up > " + fileId );
					//ftp.uploadFileOrDirectory(otherList.dirPath+tools.relativePath(this.dirPath, fileId), fileId);
					ftp.uploadFileOrDirectory(otherList.dirPath, fileId);
				}
			}
		}

		for (DiffFileComp missingFile : diffBuffer) {
			String fileId = missingFile.getFullPath();
			
			//si cleanup distant
			if( cleanDist ) {
				try {
					System.out.println( " <x " + fileId );
					ftp.removeFileOrDirectory(fileId, false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				//download
				
				System.out.println( " < dl " + fileId );
				ftp.downloadFileOrDirectory(fileId, otherList.dirPath+tools.relativePath(this.dirPath, missingFile.getFullPath()));
			}
		}
		
		
	}
}