package com.inf380.ead.service;
import java.io.File;
public class FileService {
	public String filename;
	public String javaname1;
	public FileService(String fn,String jn){
		filename=fn;
		javaname1=jn;
	}
	public void showname(){
		System.out.println("filename= "+filename+" javaname= "+javaname1);
	}
	public void createfile(){
		File file_name=new File(filename);
		
		file_name.mkdirs();
	}
public void createjava(){
	String javafile1=filename+"/"+javaname1+".java";
	File java_name=new File(javafile1);
	try
	{
		java_name.createNewFile();
	}
	catch(java.io.IOException ex)
	{
		ex.printStackTrace();
	}
}
}



