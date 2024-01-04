<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Maintenance Page 2</title>
</head>
<body bgcolor="black" text="green">
<h4>Logged in as 
<%
		String name = (String) request.getAttribute("userName");
		
	%>
	<%=name%></h4>
	
	<form action="/project3/DataServlet" method="get">
		<input type="submit" value="Log out" name="logOutButton">
		<input type="submit" value="Home" name="homeButton"><br><br><br>
	</form>
	
<h3>Viewing shows week of:  
<%
		String targetWeek = (String) request.getAttribute("targetWeek");
		
	%>	
	<%=targetWeek%></h3> 
	<form action="/project3/DataServlet" method="get">
		<input type="hidden" value=<%=name%> name="userName">
		<input type="hidden" value=<%=targetWeek%> name="targetWeek">
		<%
		String selectionText = (String) request.getAttribute("showsParameter");
		%>
		<h4>Please select a show to edit:</h4><%=selectionText%><br> <input
			type="submit" value="Edit show" name="editShowButton">
	</form>
</body>
</html>