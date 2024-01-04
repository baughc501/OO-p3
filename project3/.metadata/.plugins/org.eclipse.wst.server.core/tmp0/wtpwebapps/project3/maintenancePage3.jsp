<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style>
div {
  padding-left: 80px;
}
div1 {
  padding-right: 60px;
}
div2 {
  padding-right: 30px;
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
<title>Maintenance Page 3</title>
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
		<input type="hidden" value=<%=name%> name="userName">
	</form>
	
<h3>You are editing this Show in Week:<br><br>  
<%
		String weekToEdit = (String) request.getAttribute("showWeekStr");
		
	%>	
	<%=weekToEdit%></h3>
	
	<form  action="/project3/DataServlet" method="get" >	 
		<div><h4>Select items to edit:<br><br>
		<input type="checkbox"  value= "weekCheckbox" name="editWeekCheckbox">
		<div1>New Week:</div1><input type="text" name="weekTextParameter" ><br>
		<input type="checkbox" value= "categoryCheckbox" name="editCategoryCheckbox">
		<div2>New Category:</div2> <input type="text" name="categoryTextParameter"><br>
		<input type="checkbox" value= "rankCheckbox" name="editRankCheckbox">
		<div3>New Rank:</div3> <input type="text" name="rankTextParameter"><br>
		<input type="checkbox" value= "titleCheckbox" name="editTitleCheckbox">
		<div4>New Title:</div4> <input type="text" name="titleTextParameter"><br>
		<input type="checkbox" value= "seasonCheckbox" name="editSeasonCheckbox">
		<div5>New Season:</div5> <input type="text" name="seasonTextParameter"><br>
		<input type="checkbox" value= "hrsViewedCheckbox" name="editHrsViewedCheckbox">
		<div6>New Hrs Viewed: </div6><input type="text" name="hrsViewedTextParameter"><br>
		<input type="checkbox" value= "wksTop10Checkbox" name="editWksTop10Checkbox">
		New Weeks Top 10: <input type="text" name="wksTop10TextParameter"></h4></div>
		<div><input type="submit" value="Edit Selected Items" name="editItemsButton"></div>
	</form>
	
</body>
</html>