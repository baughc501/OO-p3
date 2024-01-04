<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Watch Show</title>
</head>
<body background="film2.jpg" text="blue">
<h4>Logged in as 
<%
		String name = (String) request.getAttribute("userName");
		
	%>
	<%=name%></h4>
	
	<form action="/project3/DataServlet" method="get">
	 	<input type="hidden" value=<%=name%> name="userName">
		<input type="submit" value="Log out" name="logOutButton">
		<input type="submit" value="Home" name="homeButton"><br>
	</form>
<br><br><br><div style="margin-left: 250px;"><h1>Enjoy the Show!</h1></div>
<video style="margin-left: 250px;" width="600" height="400" controls autoplay>
  <source src="intro.mp4" type="video/mp4">
 
</video>
</body>
</html>