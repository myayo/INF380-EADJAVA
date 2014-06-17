package com.inf380.ead.service;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * Class that manage file Operation
 */
public class FileService {

	/**
	 * Create a file or a directory and store it to 
	 * the server fileSystem
	 * @param pathname : the relativePath of the file or the directory
	 * @param type : the type i.e (File or directory)
	 * @param content: the content of the file
	 * @throws IOException 
	 */
	public void createOrUpdateFile(String pathname, String type, String content) throws IOException{
		File file= new File(pathname);
		//create or save directory
		if(type.equals("directory")){
			if(!file.exists()){
				file.mkdir();
			}
		}else if(type.equals("file")){
			//create File
			if(!file.exists()){
				file.createNewFile();
			}
			//add content
			FileWriter writer = new FileWriter(file);
			writer.write(content);
			writer.flush();
			writer.close();
		}
	}

	/**
	 * Delete file or directory according to his pathname
	 * @param pathname : the relativePath of the file or the directory
	 * @throws IOException
	 */
	public void deleteFile(String pathname) throws IOException{
		File file= new File(pathname);
		if(file.isDirectory()){
			FileUtils.deleteDirectory(file);
		}else{
			file.delete();
		}
	}
}


