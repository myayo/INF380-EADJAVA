package com.inf380.ead.endpoint;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

import com.inf380.ead.service.FileService;

@ServerEndpoint("/fileEndPoint")
public class FileEndPoint {

	/**
	 * messageCreateOrUpdate = {<br>
	 * 			action : "createOrUpdate fichier ou dossier",
	 * 			path : "lien ou on stock",
	 * 			type : "fichier ou dossier",
	 * 			src : "le contenu dans le cas d'un fichier",
	 * 			username: "nom d'utilisateur"
	 * }
	 * 
	 * messageGet = {
	 * 			action : 'get file'
	 * 			path : 'lien du fichier ou  du dossier'
	 * 			username : 'nom d'utilisateur'
	 * }
	 * @throws IOException 
	 * 
	 */
	@OnMessage
	public String onMessage(String message) throws IOException{
		System.out.println("receiving message  : "+message);
		String result = "";
		FileService fileService = new FileService();
		JsonObject jsonObject = Json.createReader(new StringReader(message)).readObject();
		String action = jsonObject.getString("action");
		String path = "";
		String username = jsonObject.getString("username");
		switch (action) {
		case "createOrUpdate":
			path = jsonObject.getString("path");
			String type = jsonObject.getString("type");
			String src = null;
			if(type.equals("file")){
				src = jsonObject.getString("src");
			}
			fileService.createOrUpdateFile(path, type, src);
			break;
		case "get":
			//get the file
			break;

		case "getProject":
			//get the file
			List<String> projectsName = fileService.getProjects(username);
			JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
			for (String project : projectsName) {
				arrayBuilder.add(project);
			}
			JsonObject obj = Json.createObjectBuilder().add("action", action).add("projects", arrayBuilder).build();
			result = obj.toString();
			break;
		case "loadProjectFile":
			//get the file tree;
			path = jsonObject.getString("path");
			JsonObject fileTree = Json.createObjectBuilder().add("action", action)
					.add("files", fileService.getFileTree(fileService.getProjectsBaseUrl()+ username + File.separator + path, username)).build();
			result = fileTree.toString();
		default:
			break;
		}
		System.out.println("result " + result);
		return result;
	}

}
