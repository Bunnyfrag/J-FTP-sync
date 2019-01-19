package ftp_Client;

import java.util.Date;

public class DiffFileComp {
	
	private String fullPath;
	private String shortPath;
	private Date lastEdit;
	
	public DiffFileComp(String fullPath, String shortPath, Date lastEdit) {
		this.fullPath = fullPath;
		this.lastEdit = lastEdit;
		this.shortPath = shortPath;
		System.out.println( this.toString() );
	}

	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return fullPath + " | "+ shortPath +" :" + lastEdit.toString();
	}


	public String getFullPath() {
		return fullPath;
	}
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	public Date getLastEdit() {
		return lastEdit;
	}
	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}
	
}
