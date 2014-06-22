package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Model {

	private final ArrayList<User> userList ;


	public Model(){
		userList = new ArrayList<User>();
	}
	
	public void init() throws IOException{

		FileReader fr= new FileReader("/Users/fatoumatananakasse/Downloads/workspace/Login/resources/users.txt");
		BufferedReader br= new BufferedReader(fr);
		String line = null;
		while((line = br.readLine())!= null){
			String[] str = line.split(" ");
			userList.add(new User(str[0], str[1]));
		}
		br.close();
	}

}
