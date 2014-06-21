package com.inf380.ead.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class CompileRunDebugService {

	/************Constructor***************/
	public CompileRunDebugService(){

	}

	/************Methods***************/

	/**
	 * compile all the java file present in the package sourcesDir
	 */
	public String compile( String sourcesDirPathName, String classOutputDirPathName ) throws IOException {	
		String result = null;
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
			success=(ret==0);
			if(!success){
				result="Compilation Failed! /r";
				try {
					result=result+getLines("----compilation error----",p.getErrorStream());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(success){
			result="Compilation Succeed!";
		}
		return result;
	}


	/**
	 * run the class containing the method main
	 */
	public String run(String mainClassName, String classOutputDirPathName) {
		System.out.println( "Running main from class "+ mainClassName+"..." );
		String result=null;
		String error=null;
		try {
			//run the class containing the method main with the command java
			Process p = Runtime.getRuntime().exec( "java -cp "+classOutputDirPathName+" "+ mainClassName );
			error=getLines("----stderr----", p.getErrorStream());
			if(error==null){
				result=getLines("----Result----", p.getInputStream());
			}
			else{
				result=error;
			}
			//wait process end
			p.waitFor();
		} 
		catch( InterruptedException ie ) { System.out.println( ie );} 
		catch (IOException e) {e.printStackTrace();}
		catch (Exception e) {e.printStackTrace();}
		return result;
	}

	/**
	 * compile the java files in the directory sourcesDir
	 * put the generate class in the directory classOutputDir
	 * then execute the class containing the method main
	 */
	public String compileRun(String sourcesDirPathName, String classOutputDirPathName , String mainClass){
		String result = null;
		try {
			result=compile(sourcesDirPathName,classOutputDirPathName);
			if(result.equals("Compilation Succeed!")){
				result=run(mainClass, classOutputDirPathName);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Print the lines in the stream
	 */
	public String getLines(String name, InputStream is) throws Exception {
		String result = null;
		String line = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		line = br.readLine();
		if(line!=null){
			result=result+"/r"+name;
			while (line!= null) {
				result=result+"/r"+line;
				line = br.readLine();
			}
		}
		return result;
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

