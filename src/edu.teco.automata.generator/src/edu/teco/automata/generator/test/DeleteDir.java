package edu.teco.automata.generator.test;

import java.io.File;

public class DeleteDir {
  public static void main(String args[]) {
    deleteDirectory(new File(args[0]));
  }

  static public boolean deleteDirectory(File path) {
	boolean ret=true;
    if( path.exists() ) {
      File[] files = path.listFiles();
      for(int i=0; i<files.length; i++) {
         if(files[i].isDirectory()) {
           ret=deleteDirectory(files[i])&&ret;
         }
         else {
        	 ret=files[i].delete()&&ret;
         }
      }
      return( path.delete()&&ret );
    }
    else return true;
  }
}