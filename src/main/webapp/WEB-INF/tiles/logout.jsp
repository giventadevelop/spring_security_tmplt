<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html ng-app="app" ng-controller="mainController">
<head>
	<title>Spring Boot with Apache Tiles</title>
	
</head>
<body>
<div class="container" >
  
  <div class="text-center">
    <p>Example of Angular and the normal Bootstrap JavaScript components</p>
    <p class="text-success">This will work</p>
  </div>
  
  
  <div class="container">
		
		 <h3>Logout  Page</h3> 
  
    <c:if test="${not empty error}"> 
        <div class="errorblock"> 
            Your login attempt was not successful, try again.<br /> Caused : 
            ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} 
        </div> 
    </c:if> 
  
    <form name='f' action="/login />" 
        method='POST'> 
  
        <table> 
            <tr> 
                <td>User:</td> 
                <td><input type='text' name='_username' value=''> 
                </td> 
            </tr> 
            <tr> 
                <td>Password:</td> 
                <td><input type='password' name='password' /> 
                </td> 
            </tr> 
            <tr> 
                <td colspan='2'><input name="submit" type="submit" 
                    value="submit" /> 
                </td> 
            </tr> 
            <tr> 
                <td colspan='2'><input name="reset" type="reset" /> 
                </td> 
            </tr> 
        </table> 
  
    </form> 
	</div>
  
  <h2>Buttons</h2>
  <div class="btn-group" data-toggle="buttons">
    <label class="btn btn-primary" ng-model="bigData.breakfast" btn-checkbox>
      Breakfast
    </label>
    <label class="btn btn-primary" ng-model="bigData.lunch" btn-checkbox>
      Lunch
    </label>
    <label class="btn btn-primary" ng-model="bigData.dinner" btn-checkbox>
      Dinner
    </label>
  </div>
    
  <pre><code>{{ bigData | json }}</code></pre>
  
  <h2>Collapse</h2>
  
  <a href="#" class="btn btn-primary" ng-click="isCollapsed = !isCollapsed">
    Toggle Panel
  </a>

  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a href="#" ng-click="isCollapsed = !isCollapsed">
          Collapsible Group Item #1
        </a>
      </h4>
    </div>
    <div collapse="{{isCollapsed}}">
      <div class="panel-body">Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
      </div>
    </div>
  </div>
  
  <pre><code>{{ isCollapsed }}</code></pre>
  
</div>

</body>
</html>
