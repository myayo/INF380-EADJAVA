var wsUri = "ws://"+document.location.host + "/ead/" + "compileRunEndpoint";
var compileRunWebSocket = new WebSocket(wsUri);

compileRunWebSocket.onerror = function(e){onError(e);};
compileRunWebSocket.onopen = function(e){onOpen(e);};
compileRunWebSocket.onclose = function(){onClose();};
compileRunWebSocket.onmessage = function(msg){onMessage(msg);};
compileRunWebSocket.sendCommand = function(command){compileRunWebSocket.send(command);};


function onError(e) {
	console.log(e);
};

function onOpen(e) {
	console.log("Socket open");
};

function onClose() {
	console.log("Socket closed");
};

function onMessage(msg) {
	console.log(msg);
};

function compileAndRun(){
	var command = {
			action : "compilerun",
			path : "src/main/resources/Test",
			mainClassPath: "Main.java"
			
	};
	compileRunWebSocket.sendCommand(command);
}

function compile(){
	var command = {
			action : "compile",
			path : "src/main/resources/Test",
			
	};
	compileRunWebSocket.sendCommand(command);
}
function run(){
	var command = {
			action : "run",
			path : "src/main/resources/Test",
			mainClassPath: "Main.java"
			
	};
	compileRunWebSocket.sendCommand(command);
}

