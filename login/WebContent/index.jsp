<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <link rel="stylesheet" href="css/styles.css">
<title>Home</title>
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
		<div class='col-xs-6 col-xs-offset-3'>
			<h1>Welcome to Telepin Registeration Page ! </h1>
		</div>
	</div>
    <div class="row">
        <div class="col-xs-8 col-xs-offset-2">
            <form  action="Controller" method="POST">
            <p><%= error %></p>
    <div class="form-group">
    <label for="username">User name</label>
    <input type="text" class="form-control" id="username" required placeholder="UserName" name="username">
  </div>
  <div class="form-group">
    <label for="password">Password</label>
    <input type="password" class="form-control" required id="password" placeholder="Password" name="password">
  </div>
  Gender :
    <label class="radio-inline">
      <input type="radio" name="gender" value="male" required>Male
    </label>
    <label class="radio-inline">
      <input type="radio" name="gender" value="female">Female
    </label>
    <div class="form-group">
  <label for="usr">Birthdate :</label>
  <input type="date" class="form-control"  name="birthdate" id="usr" required>
</div>
    <button type="submit" class="btn btn-default">Register </button>
</form>

        </div>
    </div>
    <div class='row'>
		<div class='col-xs-6 col-xs-offset-3'>
			<h1>already a user ! <a href="login.jsp">Log in </a></h1>
		</div>
	</div>
	<footer class="text-center">
	<p>Coded by <a href="https://www.facebook.com/hiimesso">Captin esso</a> !</p>
	</footer>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="js/script.js"></script>
<% request.removeAttribute("error"); %>
</body>
</html>