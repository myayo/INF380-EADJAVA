package model;

import java.io.File;
import java.util.ArrayList;

import model.User;

public class User {

	private String login;
	private String password;
	private static ArrayList<File> files;
	
	public User(String l, String p){
		login=l;
		password=p;
		files=new ArrayList<File>();
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static ArrayList<File> getFiles() {
		return files;
	}

	public static void setFiles(ArrayList<File> files) {
		User.files = files;
	}
	
	
}
