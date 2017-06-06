<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <link rel="stylesheet" href="css/styles.css">
<title>File Upload</title>
</head>
<body>
<% 
String error=(String)request.getAttribute("error");
if (error ==null )
{
	error="";
}
String message=(String)request.getAttribute("message");
if (message ==null )
{
	message="";
}
%>
<div class="container">
    <h1 class="text-center">Upload CSV file to register users</h1>
    <form method="post" action="UploadServlet"
        enctype="multipart/form-data">
        Select file to upload: <input type="file" name="file" size="60" /><br />
        <br /> <input type="submit" value="Upload" />
    </form>
    <p><%= error %></p>
    <hr />
    <h2><%= message %></h2>
    <hr />
    <form action="Logout" method="post">
<input type="submit" value="logout">


</form>

<hr />
<a href="welcome.jsp">Back to Home </a> 
<footer class="text-center">
	<p>Coded by <a href="https://www.facebook.com/hiimesso">Captin esso</a> !</p>
	</footer>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="js/script.js"></script>
<% request.removeAttribute("error");
request.removeAttribute("message");
%>
</body>
</html>