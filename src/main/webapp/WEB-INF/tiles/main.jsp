<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html ng-app="app" ng-controller="mainController">
<head>
<title>Spring Boot with Apache Tiles</title>

</head>
<body>

  <!-- Source for the bootstrap login form tthe
  urls
  https://bootsnipp.com/tags/login
  https://bootsnipp.com/snippets/featured/login-amp-signup-forms-in-panel
   -->
	<div class="container">

		<div class="text-center">
			<p>Example of Angular and the normal Bootstrap JavaScript
				components</p>
			<p class="text-success">This will work</p>
		</div>


		<div class="container">

			<h3>Login with Username and Password (Custom Page)</h3>


			<div class="container">
				<div id="loginbox" style="margin-top: 50px;"
					class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
					<div class="panel panel-info">
						<div class="panel-heading">
							<div class="panel-title">Sign In</div>
							<div
								style="float: right; font-size: 80%; position: relative; top: -10px">
								<a href="#">Forgot password?</a>
							</div>
						</div>

						<div style="padding-top: 30px" class="panel-body">

							<c:if test="${param.error != null}">
								<div id="login-alert" class="alert alert-danger col-sm-12">
									Your login attempt was not successful, try again.<br /> Caused
									: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}

								</div>
							</c:if>
							<form id="login-form" class="form-horizontal" role="form"
								action="/login" method='POST'>

								<div style="margin-bottom: 25px" class="input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-user"></i></span> <input
										id="login-username" type="text" class="form-control"
										name="username" value="" placeholder="username or email">
								</div>

								<div style="margin-bottom: 25px" class="input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-lock"></i></span> <input
										id="login-password" type="password" class="form-control"
										name="password" placeholder="password">
								</div>



								<div class="input-group">
									<div class="checkbox">
										<label> <input id="login-remember" type="checkbox"
											name="remember" value="1"> Remember me
										</label>
									</div>
								</div>


								<div style="margin-top: 10px" class="form-group">
									<!-- Button -->

									<div class="col-sm-12 controls">
										<a id="btn-login" href="#" class="btn btn-success"
											type="submit" onclick="submit_form()">Login </a>
										<!--  <a id="btn-fblogin" href="#" class="btn btn-primary">Login with Facebook</a> -->
										<a id="btn-fblogin" href="#" class="btn btn-primary"
											name="reset" type="reset" onclick="login_form_reset()">Reset</a>
											
									</div>
								</div>


								<div class="form-group">
									<div class="col-md-12 control">
										<div
											style="border-top: 1px solid #888; padding-top: 15px; font-size: 85%">
											Don't have an account! <a href="#"
												onClick="$('#loginbox').hide(); $('#signupbox').show()">
												Sign Up Here </a>
										</div>
									</div>
								</div>
							</form>



						</div>
					</div>
				</div>

			<a id="protected_page" href="/protectedpage" class="btn btn-primary">protectedpage</a>
				
			</div>

			<h2>Buttons</h2>
			<div class="btn-group" data-toggle="buttons">
				<label class="btn btn-primary" ng-model="bigData.breakfast"
					btn-checkbox> Breakfast </label> <label class="btn btn-primary"
					ng-model="bigData.lunch" btn-checkbox> Lunch </label> <label
					class="btn btn-primary" ng-model="bigData.dinner" btn-checkbox>
					Dinner </label>
			</div>

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
