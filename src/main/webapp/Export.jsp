<!DOCTYPE html>
<html ng-app="eadApp" class="cssmasks">
<head>
    <title>Export File</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
</head>
<body data-ng-controller="AppCtrl">
	<form action=ExportServlet>
    	<center>
    		<td>Pathname</td>
            <td><input type="text" name=pathName></td>
    	</center> 
		<center><input type="submit" name=finish value= Finish></center> 
		<center><input type="button" name=cancel value= Cancel onClick="window.close()"></center> 
	</form>
    
</body>
</html>
