<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html ng-app="app" ng-controller="mainController">
<head>
<title>Spring Boot with Apache Tiles</title>

</head>
<body>

 <form id="logout-form" class="form-horizontal" action="/logout" method="post">
      <div class="col-sm-12 controls">
										<a id="btn-login" href="#" class="btn btn-success"
											type="submit" onclick="logout_user()">Logout </a>
										
									</div>
    </form>
	
		<div class="text-center">
			<p>Protected Page</p>
			<p class="text-success">This will work</p>
		</div>


		
</body>
</html>
