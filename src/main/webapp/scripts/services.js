angular.module("eadApp")
.factory("FileService", function() {
	
	var FileService = {};
	
	FileService.connect = function(){
		if(FileService.ws && FileService.ws.readyState == WebSocket.OPEN){
			return;
		}
		
		var wsUrl = "ws://"+document.location.host + "/ead/" + "fileEndPoint";
		var webSocket = new WebSocket(wsUrl);
		webSocket.onopen = function(){
			console.log("CONNECTED");
		};
		webSocket.onerror = function(){
			console.log("Failed to open a connection");
		};
		webSocket.onclose = function(){
			console.log("DISCONNECTED");
		};
		webSocket.onmessage = function(message){
			FileService.callback(message.data);
		};
		
		FileService.ws = webSocket;
	};
	
	FileService.subscribe = function(callback){
		FileService.callback = callback;
	};
	
	FileService.getProjects = function(username){
			var json = JSON.stringify({
				action : "getProject",
				username : username
			});
					FileService.ws.send(json);
		
		
	};
	
	FileService.loadProjectFiles = function(project, username){
		var json = JSON.stringify({
			action : "loadProjectFile",
			username: username,
			path : project
		});
		FileService.ws.send(json);
	};
	
	return FileService;
	
}).factory("CompileRunService", function(){
	var compileRunService = {};
	
	compileRunService.connect = function(){
		if(compileRunService.ws && compileRunService.ws.readyState == WebSocket.OPEN){
			return;
		}
		var wsUrl = "ws://"+document.location.host + "/ead/" + "compileRunEndpoint";
		var webSocket = new WebSocket(wsUrl);
		
		webSocket.onopen = function(){
			compileRunService.callback("CONNECTED");
		};
		
		webSocket.onerror = function(){
			compileRunService.callback("Failed to open a connection");
		};
		
		webSocket.onclose = function(){
			compileRunService.callback("DISCONNECTED");
		};
		
		webSocket.message = function(message){
			compileRunService.callback(message);
		};
		
		compileRunService.ws = webSocket;
	};
	
	compileRunService.subscribe = function(callback){
		compileRunService.callback = callback;
	};
	
	compileRunService.compile = function(path){
		 compileRunService.ws.send(JSON.stringify({
			 action: "compile",
			 path: path
		 }));
	};
	
	compileRunService.compileAndRun = function(path, mainClass, username){
		
		 compileRunService.ws.send(JSON.stringify({
			 action: "compilerun",
			 path: path,
			 mainClassName: mainClass,
			 username: username
		 }));
	};
	
	compileRunService.Run = function(path, mainClass){
		 compileRunService.ws.send({
			 action: "run",
			 path: path,
			 mainClassPath: mainClass
		 });
	};
	
	return compileRunService;
	
});