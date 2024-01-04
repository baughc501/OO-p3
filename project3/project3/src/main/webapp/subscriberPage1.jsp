<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Subscriber Page 1</title>
</head>
<body background="background.jpg" text="red">
<h4>Logged in as 
<%
		String name = (String) request.getAttribute("userName");
		
	%>
	<%=name%></h4>
	
	<form action="/project3/DataServlet" method="get">
	 	<input type="hidden" value=<%=name%> name="userName">
		<input type="submit" value="Log out" name="logOutButton">
		<input type="submit" value="Home" name="homeButton"><br><br><br>
	</form>
	
	<form action="/project3/DataServlet" method="get">
	   
		<%
			String selectionText = (String) request.getAttribute("dropDownOptions");
		%>
		<div style="margin-left: 50px;"><h3>Choose a show to watch:</h3><%=selectionText%></div><br> 
		<input style="margin-left: 50px;" type="submit" value="Watch Show!" name="watchShowButton"><br><br><br><br><br><br>
	</form>
	
	<form action="/project3/DataServlet" method="get">   
		<%
			String selectionText2 = (String) request.getAttribute("selectionList");
		%>
		<div style="margin-left: 50px;"><h3>Today's Top Shows:</h3><%=selectionText2%></div><br>
		<br><input style="margin-left: 50px;" type="submit" value="Watch Top Show!" name="watchTopShowButton">
	</form>
</body>
</html>
