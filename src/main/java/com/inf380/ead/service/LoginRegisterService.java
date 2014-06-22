package com.inf380.ead.service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import org.apache.commons.io.FileUtils;

/**
 * Class that manage file Operation
 */
public class LoginRegisterService {

	private Model model;
	
	public LoginRegisterService(){
		model=new Model();
		model.init();
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


