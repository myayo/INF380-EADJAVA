angular.module("eadApp")
.controller("AppCtrl", ["$scope", function($scope){
	$scope.projetFiles = [
{ "label" : "User", "roleId" : "role1", 
	"children" : [{ "label" : "subUser1", "roleId" : "role11", 
	"children" : [] },{ "label" : "subUser2", "roleId" : "role12", 
	"children" : [{ "label" : "subUser2-1", "roleId" : "role121",
	"children" : [{ "label" : "subUser2-1-1", "roleId" : "role1211",
	"children" : [] },{ "label" : "subUser2-1-2", "roleId" : "role1212", 
	"children" : [] }]}]}]},{ "label" : "Admin", "roleId" : "role2",
		"children" : [] },{ "label" : "Guest", "roleId" : "role3", "children" : [] }
	                                                                           ];
}]);