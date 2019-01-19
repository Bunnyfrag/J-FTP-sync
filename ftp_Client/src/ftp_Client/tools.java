package ftp_Client;

import java.io.File;

public class tools {
	
	 private static String OS = null;
	   public static String getOsName()
	   {
	      if(OS == null) { OS = System.getProperty("os.name"); }
	      return OS;
	   }
	   public static boolean isWindows()
	   {
	      return getOsName().startsWith("Windows");
	   }

	   //public static boolean isUnix()
	
	public static String relativePath( final File base, final File file ) {
        final int rootLength = base.getAbsolutePath().length();
        final String absFileName = file.getAbsolutePath();
        final String relFileName = absFileName.substring(rootLength + 1);
        return relFileName;
    }
	public static String relativePath( final String base, final String file ) {
        final int rootLength = base.length();
        final String absFileName = file;
        final String relFileName = absFileName.substring(rootLength + 1);
        return relFileName;
    }
}
