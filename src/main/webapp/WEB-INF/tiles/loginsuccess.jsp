<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html ng-app="app" ng-controller="mainController">
<head>
<title>Spring Boot with Apache Tiles</title>

</head>
<body>
	

		<div class="text-center">
			<p>Example of Angular and the normal Bootstrap JavaScript
				components</p>
			<p class="text-success">This will work</p>
		</div>


		<div class="container">

			<h3>Login Successful</h3>

			

<a id="protected_page" href="/protectedpage" class="btn btn-primary">protectedpage</a>
			

			<h2>Buttons</h2>
			

			<pre>
				<code>{{ bigData | json }}</code>
			</pre>

			<h2>Collapse</h2>

			<a href="#" class="btn btn-primary"
				ng-click="isCollapsed = !isCollapsed"> Toggle Panel </a>

			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a href="#" ng-click="isCollapsed = !isCollapsed"> Collapsible
							Group Item #1 </a>
					</h4>
				</div>
				<div collapse="{{isCollapsed}}">
					<div class="panel-body">Ad vegan excepteur butcher vice lomo.
						Leggings occaecat craft beer farm-to-table, raw denim aesthetic
						synth nesciunt you probably haven't heard of them accusamus labore
						sustainable VHS.</div>
				</div>
			</div>

			<pre>
				<code>{{ isCollapsed }}</code>
			</pre>

		</div>
</body>
</html>
