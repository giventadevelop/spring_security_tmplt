
<!DOCTYPE html>
<html ng-app="app" ng-controller="mainController">
<head>
	<title>Spring Boot with Apache Tiles</title>
	<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<script>
function submit_form() {
//var name = document.getElementById("login-form").value;
//var email = document.getElementById("email").value;
document.getElementById("login-form").submit();

/* if (validation()) // Calling validation function
{
document.getElementById("form_id").submit(); //form submission
alert(" Name : " + name + " n Email : " + email + " n Form Id : " + document.getElementById("form_id").getAttribute("id") + "nn Form Submitted Successfully......");
} */


}

//form reset

function login_form_reset() {

	document.getElementById("login-form").reset();
}


function logout_user() {
	//var name = document.getElementById("login-form").value;
	//var email = document.getElementById("email").value;
	document.getElementById("logout-form").submit();

	/* if (validation()) // Calling validation function
	{
	document.getElementById("form_id").submit(); //form submission
	alert(" Name : " + name + " n Email : " + email + " n Form Id : " + document.getElementById("form_id").getAttribute("id") + "nn Form Submitted Successfully......");
	} */


	}


</script>
	
</head>

<h2>This is Header</h2>
<hr>