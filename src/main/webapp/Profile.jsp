<!DOCTYPE html>
<html ng-app="eadApp" class="cssmasks">
<head>
    <title>Rocket App</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link href="styles/bootstrap.min.css" rel="stylesheet">
	<link href="styles/style.css" rel="stylesheet">
	<link href="styles/abn_tree.css" rel="stylesheet">
</head>
<body data-ng-controller="AppCtrl">
    
    <div class="wrapper">
    	<div class="box">
    		<div class="row row-offcanvas row row-offcanvas-left">
    			<div class="column col-sm-2 col-xs-1 sidebar-offcanvas" id="sidebar" data-ng-include data-src="'views/explorer.html'">
    			</div>
    			 <!-- Editors  -->
            	<div class="column col-sm-10 col-xs-11" id="main">
            		<!-- top menu-->
	              	<div class="navbar navbar-blue navbar-static-top" data-ng-include data-src="'views/menuProfile.html'">  
	                </div>
	                <!-- /top menu -->
	                <!-- Editor -->
	                <div class="padding">
	                	<div class="full col-sm-9">
	                		<div class="row" data-ng-include data-src="'views/editor.html'">
	                		
	                		</div>
	                		<div class="full col-sm-9">
	                			<div class="row" data-ng-include data-src="'views/Terminal.html'"></div>
	                		</div>
	                	</div>
	                </div>
            	</div>
    		</div>
    	</div>
    </div>
    
    
    <script src="scripts/vendors/jquery-2.1.1.min.js"></script>
    <script src="scripts/vendors/angular.min.js"></script>
    <script type="text/javascript" src="scripts/vendors/ace.js"></script>
    <script src="scripts/vendors/mode-java.js"></script>
    <script src="scripts/vendors/theme-twilight.js"></script>
	<script type="text/javascript" src="scripts/vendors/ui-ace.js"></script>
	<script src="scripts/vendors/ng-context-menu.js"></script>
    <script src="scripts/vendors/abn_tree_directive.js"></script>
    <script src="scripts/vendors/bootstrap.min.js"></script>
    <script src="scripts/eadApp.js"></script>
    <script src="scripts/services.js"></script>
    <script src="scripts/directives.js"></script>
    <script src="scripts/controllers.js"></script>
</body>
</html>
