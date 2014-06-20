package model;

import java.io.File;
import java.util.ArrayList;

public class Model {
	
	private ArrayList<User> user;
	
	public Model(User u){
		user=new ArrayList<User>();
	}

	public void compile(User u, String dataProject){
		
	}
	
	public void run(User u, String dataProject){
		
	}
	
	public boolean log(String login, String password){
		boolean found=false;
		for(int i=0; i<user.size() && !found; i++){
			if(user.get(i).getLogin().equals(login) && user.get(i).getPassword().equals(password) ){
				found=true;
			}
		}
		return found;
	}
	
	public void register(String login, String password){
		user.add(new User(login, password));
	}
}
