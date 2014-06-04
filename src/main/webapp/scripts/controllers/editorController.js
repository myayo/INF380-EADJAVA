angular.module("eadApp").controller("editorCtrl", function($scope){
	
	//the file explorer should add file here
	$scope.openFiles = [
	       {name : 'Hello.java', content:"public class Hello{\n public void main(String[] args){\n System.out.println(\"Hello INF380\");\n}\n}"},       
	       {name : 'Main.java', content: 'a'}];
	
	$scope.selectedIndex = 0;
	
	$scope.aceLoaded = function(_editor){
		// Editor part
	    var _session = _editor.getSession();
	    var _renderer = _editor.renderer;

	    // Options
	    _session.setUndoManager(new ace.UndoManager());
	    _renderer.setShowGutter(true);
	    _renderer.setTheme("ace/theme/twilight");
	    _session.setMode("ace/mode/java");

	    // Events
	    _editor.on("change", function(){ 
	    	if (_editor.curOp && _editor.curOp.command.name) console.log("user change");
	    	  else console.log("other change");
	    });
	};
	
	$scope.closeTab = function(index){
		$scope.openFiles.splice(index, 1);
		
	};
	
	$scope.addTab = function(file){
		$scope.openFiles.push({name: file.name});
	};
	
	$scope.setSelectedIndex= function(index){
		$scope.selectedIndex = index;
	};
	
	
	
});