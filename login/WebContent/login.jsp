<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <link rel="stylesheet" href="css/styles.css">
<title>Login page </title>
</head>
<body>
<% 
String error=(String)request.getAttribute("error");
if (error ==null )
{
	error="";
}
%>
<div class="container-fluid ">
	<div class='row'>
		<div class='col-xs-8 col-xs-offset-2'>
			<h1 class="text-center">Welcome to Telepin Login Page </h1>
		</div>
	</div>
    <div class="row">
        <div class="col-xs-6 col-xs-offset-3">
            <form  action="Controller_login" method="POST">
            <p><%= error %></p>
    <div class="form-group">
    <label for="username">User name</label>
    <input type="text" class="form-control" id="username" required placeholder="Email" name="username">
  </div>
  <div class="form-group">
    <label for="password">Password</label>
    <input type="password" class="form-control" required id="password" placeholder="Password" name="password">
  </div>
  
    <button type="submit" class="btn btn-default">Log in </button>
</form>

        </div>
    </div>
    
	<footer class="text-center">
	<p>Coded by <a href="https://www.facebook.com/hiimesso">Captin esso</a> !</p>
	</footer>
</div>
<% request.removeAttribute("error"); %>
</body>
</html>