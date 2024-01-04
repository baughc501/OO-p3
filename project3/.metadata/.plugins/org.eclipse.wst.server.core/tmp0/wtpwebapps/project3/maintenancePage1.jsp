<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style>
div {
  padding-left: 80px;
}
div1 {
  padding-right: 61px;
}
div2 {
  padding-right: 31px;
}
div3 {
  padding-right: 57px;
}
div4 {
  padding-right: 63px;
}
div5 {
  padding-right: 47px;
}
div6 {
  padding-right: 15px;
}
</style>
<head>
<meta charset="ISO-8859-1">
<title>Maintenance Page 1</title>
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
	
	<form action="/project3/DataServlet" method="get">
	    <input type="hidden" value=<%=name%> name="userName">
		<%
			String selectionText = (String) request.getAttribute("dropDownOptions");
		%>
		<div><h3>Select a week to edit</h3><%=selectionText%><br><br> 
		<input type="submit" value="Get Shows this Week" name="getShowsButton" ></div><br>	
	</form>
	
	<form  action="/project3/DataServlet" method="get" >	 
		<div><h3>Add a new show in week</h3>(enter N/A for empty fields)		
		<h4><div1>New Week:</div1><input type="text" name="weekTextParameter" ><br>		
		<div2>New Category:</div2> <input type="text" name="categoryTextParameter"><br>		
		<div3>New Rank:</div3> <input type="text" name="rankTextParameter"><br>
		<div4>New Title:</div4> <input type="text" name="titleTextParameter"><br>
		<div5>New Season:</div5> <input type="text" name="seasonTextParameter"><br>
		<div6>New Hrs Viewed: </div6><input type="text" name="hrsViewedTextParameter"><br>
		New Weeks Top 10: <input type="text" name="wksTop10TextParameter"></h4></div>
		<div><input type="submit" value="Add New Show in Week" name="addShowWeekButton"></div>
	</form>
	
</body>
</html>