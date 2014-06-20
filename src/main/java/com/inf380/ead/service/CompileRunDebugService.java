package com.inf380.ead.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Compiler {
   
	public Compiler(){
		
	}
	
	/************Methods***************/
	
	/**
	 * compile all the java file present in the package sourcesDir
	 */
	public boolean compile( String sourcesDirPathName, String classOutputDirPathName ) throws IOException {
		boolean success=true;
		System.out.println( "Compiling files from "+ sourcesDirPathName+"..." );
		//compile all the java files with the command javac
		//the class files generated are put in the directory classOutputDir
		ArrayList<File> javaFiles=getPkgFiles(sourcesDirPathName);
		for(int i=0; i<javaFiles.size() && success; i++){
			Process p = Runtime.getRuntime().exec( "javac -d "+classOutputDirPathName+" "+javaFiles.get(i).getAbsolutePath() );
			try {
				//wait process end
				p.waitFor();
			} catch( InterruptedException ie ) { System.out.println( ie ); }
			int ret = p.exitValue();
			//
			success=(ret==0);
			if(!success){
				System.out.println("Compilation Failed!");
				try {
					printLines("----Compilation error----",p.getErrorStream());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(success){
			System.out.println("Compilation Succeed!");
		}
		return success;
	}
		
	
	/**
	 * run the class containing the method main
	 */
	 public void run(String mainClassName, String classOutputDirPathName) {
		    System.out.println( "Running main from class "+ mainClassName+"..." );
			try {
				//run the class containing the method main with the command java
				Process p = Runtime.getRuntime().exec( "java -cp "+classOutputDirPathName+" "+ mainClassName );
				//print the output of the run if any
				printLines("----Result----", p.getInputStream());
				//print the error if any
		        printLines("----stderr----", p.getErrorStream());
		        //wait process end
			    p.waitFor();
				int ret = p.exitValue();
			} 
			catch( InterruptedException ie ) { System.out.println( ie );} 
			catch (IOException e) {e.printStackTrace();}
			catch (Exception e) {e.printStackTrace();}
	    }
	 
	/**
	 * compile the java files in the directory sourcesDir
	 * put the generate class in the directory classOutputDir
	 * then execute the class containing the method main
	 */
		public void compileRun(String sourcesDirPathName, String classOutputDirPathName , String mainClass){
			try {
				boolean result=compile(sourcesDirPathName,classOutputDirPathName);
				if(result){
					run(mainClass, classOutputDirPathName);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	 /**
	  * Print the lines in the stream
	  */
    public void printLines(String name, InputStream is) throws Exception {
        String line = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        line = br.readLine();
        if(line!=null){
	        System.out.println(name ); 
	        while (line!= null) {
	            System.out.println(line);
	            line = br.readLine();
	        }
        }
      }
    
	/** 
	 * return the list of java files present in the package
	 */  
	public ArrayList<File> getPkgFiles(String pkgPathName){
	    File[] files=new File(pkgPathName).listFiles();
	    ArrayList<File> javaFiles=new ArrayList<File>();
	    for(int i=0; i<files.length; i++){
	    	if(files[i].getName().endsWith(".java")){
	    		javaFiles.add(files[i]);
	    	}
	    }
		return javaFiles;
		
	}
 
}
 
