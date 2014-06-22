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


	public boolean log(String login, String password){
		boolean found=false;
		for(int i=0; i<userList.size() && !found; i++){
			if(userList.get(i).getLogin().equals(login) && userList.get(i).getPassword().equals(password) ){
				found=true;
			}
		}
		return found;
	}

	public boolean userExist(String username){
		boolean found=false;
		for(int i=0; i<userList.size() && !found; i++){
			if(userList.get(i).getLogin().equals(username)){
				found=true;
			}
		}
		return found;
	}
	
	public void register(String login, String password) throws IOException{
		userList.add(new User(login, password));
		FileWriter file = new FileWriter(new File("/Users/fatoumatananakasse/Downloads/workspace/Login/resources/users.txt"), true);
		PrintWriter writer = new PrintWriter(file);
		writer.println(login + " "+ password);
		writer.close();
	}
}
