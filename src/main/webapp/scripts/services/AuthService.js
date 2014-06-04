angular.module("rocketApp")
.factory("AuthService",["$http", "Session", function($http, Session){
	return {
		login: function(credential) {
			$http
			.post("http://localhost:8088/rocket/rest/login", credential)
			.then(function(res){
				Session.create(res.id, res.userid, res.role);
			});
		},
		isAuthenticated: function(){
			return !Session.id;
		},
		isAuthorize: function(authorizedRole){
			if(!angular.isArray(authorizedRole)){
				authorizedRole = [authorizedRole];
			}
			return this.isAuthenticated() && authorizedRole.indexOf(Session.role) != -1;
		}
	};
}]);