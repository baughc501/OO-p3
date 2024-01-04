<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Success Page</title>
</head>
<body bgcolor="black" text="green">
<h4>Logged in as 
<%
		String name = (String) request.getAttribute("userName");
		String editedWeek = (String) request.getAttribute("editedShowWeekStr");
		
	%>
	<%=name%></h4>
	<form action="/project3/DataServlet" method="get">
		<input type="submit" value="Log out" name="logOutButton">
		<input type="submit" value="Home" name="homeButton"><br><br><br>
	</form>
	<h2>Edit Confirmed</h2>
	<h3>Edited show in Week:<br><br>
	<%=editedWeek%></h3>
	<form action="/project3/DataServlet" method="get">
		<input type="hidden" value=<%=editedWeek%> name="userName">
		<input type="hidden" value=<%=name%> name="userName">
	</form>
</body>
</html>