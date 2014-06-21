package com.inf380.ead.endpoint;

import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

import com.inf380.ead.service.CompileRunDebugService;

@ServerEndpoint("/compileRunEndpoint")
public class CompileRunEndpoint {


	/**
	 * message = {action : 'run' ou 'compile' ou 'compilerun'
	 * 			  path : 'chemin vers le dossier des fichiers sources'
	 * 			  mainClassPath: 'chemin vers le fichier main'}
	 * @throws IOException 
	 */
	@OnMessage
	public String onMessage(String message) throws IOException{
		String result = "";
		//decode message
		JsonObject jsonObject = Json.createReader(new StringReader(message)).readObject();
		CompileRunDebugService compileRunDebugService = new CompileRunDebugService();
		String action = jsonObject.getString("action");
		String path = jsonObject.getString("path");
		String mainClassPath = "";
		switch (action) {
		case "compile":
			result = compileRunDebugService.compile(path, path + "/bin");
			break;
		case "run":
			mainClassPath = jsonObject.getString("mainClassPath"); 
			result = compileRunDebugService.run(mainClassPath, path+ "/bin");
			break;
		case "compilerun":
			mainClassPath = jsonObject.getString("mainClassPath");
			result = compileRunDebugService.compileRun(path, path+ "/bin", mainClassPath);
			break;
		}
		return result;
	}
}
