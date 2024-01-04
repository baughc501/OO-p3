<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Success Page</title>
</head>
<body bgcolor="black" text="green">
<h4>Logged in as 
<%
		String name = (String) request.getAttribute("userName");
		String newWeek = (String) request.getAttribute("newWeekStr");
		
	%>
	<%=name%></h4>
	<form action="/project3/DataServlet" method="get">
		<input type="submit" value="Log out" name="logOutButton">
		<input type="submit" value="Home" name="homeButton"><br><br><br>
	</form>
	<h2>Add Show in Week Confirmed</h2>
	<h3>added show in Week:<br>
	<%=newWeek%></h3>
	<form action="/project3/DataServlet" method="get">
		<input type="hidden" value=<%=newWeek%> name="userName">
		<input type="hidden" value=<%=name%> name="userName">
	</form>
</body>
</html>