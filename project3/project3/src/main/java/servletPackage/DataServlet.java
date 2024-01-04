//Chris Baugh
//Project 3
//CSCI 3381
package servletPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DataServlet
 */
@WebServlet("/DataServlet")
public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Shows allData;
	private String allWeeksList;
	private String allShowsList;
	private String userName;
	private String password;
	private String targetWeek;
	private ShowWeek showWeekToEdit;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataServlet() {
        super();
        allData = new Shows("allData","./servletPackage/netflixAllWeeksGlobalProcessed.txt");
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String loginButton = request.getParameter("loginCheck");

    	if(loginButton!=null) {
    		userName = request.getParameter("userNameParameter");
    		request.setAttribute("userName",userName);
    		password = request.getParameter("passwordParameter");

    		if(userName.equals("md") && password.equals("pw")) {
    			response.getWriter().append("<!DOCTYPE html>\r\n" + 
    					"<html>\r\n" + 
    					"<head>\r\n" + 
    					"<meta charset=\"ISO-8859-1\">\r\n" + 
    					"<title>Home Page</title>\r\n" +
    					"</head>\r\n"+
    					"<body bgcolor=\"silver\" text=\"purple\">\r\n"+
    					"<div align=\"center\">"+
    					"<br><br><br><h1>Welcome back to Netflix </h1><br>"+
    					"<h3>Please choose a mode:</h3><br>	<form action=http://localhost:8081/project3/DataServlet\r\n" +
    					"		method=\"get\">\r\n" + 
    					"		<input type=\"hidden\" value=\""+userName+"\" name=\"userName\">\r\n" + 
    					"		<input type=\"submit\"  value=\"Subscriber Mode\" name=\"subscriberModeButton\">\r\n" + 
    					"		<input type=\"submit\" value=\"Maintenance Mode\" name=\"maintenanceModeButton\" >\r\n" + 
    					"	</form>\r\n" +
    					"</div>"+
    					"</body>\r\n" + 
    					"</html>");	

    		}
    		else {
        		RequestDispatcher rd=request.getRequestDispatcher("/index.html");   
        		rd.forward(request,response); 		}

       
    	}
    	else if(request.getParameter("homeButton") != null) {
    		request.setAttribute("userName",userName);
			response.getWriter().append("<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"<head>\r\n" + 
					"<meta charset=\"ISO-8859-1\">\r\n" + 
					"<title>Home Page</title>\r\n" +
					"</head>\r\n"+
					"<body bgcolor=\"silver\" text=\"purple\">\r\n"+
					"<div align=\"center\">"+
					"<br><br><br><h1>Welcome back to Netflix </h1><br>"+
					"<h3>Please choose a mode:</h3><br>	<form action=http://localhost:8081/project3/DataServlet\r\n" +
					"		method=\"get\">\r\n" + 
					"		<input type=\"hidden\" value=\""+userName+"\" name=\"userName\">\r\n" + 
					"		<input type=\"submit\"  value=\"Subscriber Mode\" name=\"subscriberModeButton\">\r\n" + 
					"		<input type=\"submit\" value=\"Maintenance Mode\" name=\"maintenanceModeButton\" >\r\n" + 
					"	</form>\r\n" +	
					"</div>"+
					"</body>\r\n" + 
					"</html>");	

		}
    	
    	//maintenance mode section
    	//builds weeks drop list in maintenance
    	else if(request.getParameter("maintenanceModeButton") != null) {
    		allWeeksList = "<select name=\"weeks\">";
    		Iterator<ShowWeek> iter = allData.getIterator();
    		request.setAttribute("userName",userName);
    		while (iter.hasNext()) {
    			ShowWeek s = iter.next();
    			if (!allWeeksList.contains(s.getWeek())) {
    				allWeeksList += "<option value=\""+s.getWeek()+"\">"+s.getWeek()+"</option>";
    			}	
    		}
    		allWeeksList += "</select>\r\n";
    		request.setAttribute("dropDownOptions",allWeeksList); 	
    		RequestDispatcher rd=request.getRequestDispatcher("/maintenancePage1.jsp");
    		rd.forward(request,response);
    	}
    	// builds the title list of chosen week
    	else if(request.getParameter("getShowsButton") != null) {
    		String weekString = request.getParameter("weeks");
    		request.setAttribute("userName",userName);
    		targetWeek = request.getParameter("weeks");
    		request.setAttribute("targetWeek",targetWeek);
    		ArrayList <ShowWeek> weekList = allData.getOneWeek(weekString);
    		String showChoice = "showsParameter";
    		String showChoiceValue = "<select name=\"shows\">";
    		for (ShowWeek s : weekList) {
    			String title = s.getShowTitle();
    			showChoiceValue += "<option value=\""+title+"\">"+title+"</option>";
    		}
    		showChoiceValue += "</select>";
    		request.setAttribute("showChoice",showChoiceValue);
    		request.setAttribute("showsParameter", showChoiceValue);
    		RequestDispatcher rd=request.getRequestDispatcher("/maintenancePage2.jsp");
    		rd.forward(request,response);
    	}
    	//calls find to get the selected show in week
    	else if(request.getParameter("editShowButton") != null) {
    		request.setAttribute("userName",userName);
    		request.setAttribute("targetWeek",targetWeek);
    		//String weekString = request.getParameter("weeks");
    		String showChoiceStr = request.getParameter("shows");
    		showWeekToEdit = allData.find(showChoiceStr, targetWeek);
    		String showWeekStr = showWeekToEdit.toString();
    		request.setAttribute("showWeekStr",showWeekStr);
    		RequestDispatcher rd=request.getRequestDispatcher("/maintenancePage3.jsp");
    		rd.forward(request,response);
    	}
    	//edit setup
    	//requires checkbox and entry to edit
    	//checkCount is seeing if anything was changed 
    	else if(request.getParameter("editItemsButton") != null) {
    		int checkCount = 0;
    		String weekText = request.getParameter("weekTextParameter");
    		String categoryText = request.getParameter("categoryTextParameter");
    		String titleText = request.getParameter("titleTextParameter");
    		String rankText = request.getParameter("rankTextParameter");
    		String seasonText = request.getParameter("seasonTextParameter");
    		String hrsViewedText = request.getParameter("hrsViewedTextParameter");
    		String wksTop10Text = request.getParameter("wksTop10TextParameter");
    		if(request.getParameter("editWeekCheckbox") != null && weekText != "") {
    			checkCount++;
    			showWeekToEdit.setWeek(request.getParameter("weekTextParameter"));
    			//System.out.println(showWeekToEdit);
    		}
    		if(request.getParameter("editCategoryCheckbox") != null && categoryText != "") {
    			checkCount++;
    			showWeekToEdit.setCategory(request.getParameter("categoryTextParameter"));
    		}
    		if(request.getParameter("editTitleCheckbox") != null && titleText != "") {
    			checkCount++;
    			showWeekToEdit.setShowTitle(request.getParameter("titleTextParameter"));
    		}
    		if(request.getParameter("editRankCheckbox") != null && rankText != "") {
    			checkCount++;
    			showWeekToEdit.setRank(request.getParameter("rankTextParameter"));
    		}
    		if(request.getParameter("editSeasonCheckbox") != null && seasonText != "") {
    			checkCount++;
    			showWeekToEdit.setSeasonTitle(request.getParameter("seasonTextParameter"));
    		}
    		if(request.getParameter("editHrsViewedCheckbox") != null && hrsViewedText != "") {
    			checkCount++;
    			showWeekToEdit.setHrsViewed(request.getParameter("hrsViewedTextParameter"));
    		}
    		if(request.getParameter("editWksTop10Checkbox") != null && wksTop10Text != "") {
    			checkCount++;
    			showWeekToEdit.setWeeksTop10(request.getParameter("wksTop10TextParameter"));
    		}
    		//indicates a change has been successfully submitted
    		//if checkCount has a value > 0 then an edit was performed
    		if(checkCount > 0) {
    			String editedShowWeekStr = showWeekToEdit.toString();
    			request.setAttribute("userName",userName);
        		request.setAttribute("editedShowWeekStr",editedShowWeekStr);
    			RequestDispatcher rd=request.getRequestDispatcher("/successPage.jsp");
        		rd.forward(request,response);
    		}
    		else {
    			request.setAttribute("userName",userName);
    			RequestDispatcher rd=request.getRequestDispatcher("/failPage.html");
        		rd.forward(request,response);
    		}
    		
    	}
    	//setup to add new show in week
    	else if(request.getParameter("addShowWeekButton") != null) {
    		String weekText = request.getParameter("weekTextParameter");
    		String categoryText = request.getParameter("categoryTextParameter");
    		String titleText = request.getParameter("titleTextParameter");
    		String rankText = request.getParameter("rankTextParameter");
    		String seasonText = request.getParameter("seasonTextParameter");
    		String hrsViewedText = request.getParameter("hrsViewedTextParameter");
    		String wksTop10Text = request.getParameter("wksTop10TextParameter");
    		if(weekText != ""&&categoryText != ""&&titleText != ""&&rankText != ""&&seasonText != ""&&hrsViewedText != ""&&wksTop10Text != "") {
    			ShowWeek newWeek = new ShowWeek(weekText, categoryText,rankText,titleText,seasonText,hrsViewedText,wksTop10Text);
    			allData.addShowWeek(newWeek);
    			System.out.println(newWeek);
    			String newWeekStr = newWeek.toString();
    			request.setAttribute("userName",userName);
        		request.setAttribute("newWeekStr",newWeekStr);
    			RequestDispatcher rd=request.getRequestDispatcher("/addSuccessPage.jsp");
        		rd.forward(request,response);
    		}
    		else {
    			request.setAttribute("userName",userName);
    			RequestDispatcher rd=request.getRequestDispatcher("/failPage.html");
        		rd.forward(request,response);
    		}
    		
    	}
    	
    	//subscriber mode section
    	//builds the title list and top shows lists in subscriber mode
    	else if(request.getParameter("subscriberModeButton") != null) {
    		allShowsList = "<select name=\"shows\">";
    		Iterator<ShowWeek> iter = allData.getIterator();
    		request.setAttribute("userName",userName);
    		while (iter.hasNext()) {
    			ShowWeek s = iter.next();
    			if (!allShowsList.contains(s.getShowTitle())) {
    			allShowsList += "<option value=\""+s.getShowTitle()+"\">"+s.getShowTitle()+"</option>";
    			}
    		}
    		allShowsList += "</select>\r\n";
    		request.setAttribute("dropDownOptions",allShowsList);
    		
    		ArrayList <ShowWeek> topList = allData.getTopShows();
   		 	String parameter1="selectionList";
   		 	String topShowsList = "<select name=\"topShows\">";
   		 	for (ShowWeek s : topList) {
   		 		String title = s.getShowTitle();
   		 		topShowsList += "<option value=\""+title+"\">"+title+"</option>";
   		 		}
   		 	request.setAttribute(parameter1,topShowsList);
    		RequestDispatcher rd=request.getRequestDispatcher("/subscriberPage1.jsp");
    		rd.forward(request,response);
    	}
    	//watch show buttons
    	else if(request.getParameter("watchShowButton") != null) {
    		request.setAttribute("userName",userName);
    		RequestDispatcher rd=request.getRequestDispatcher("/watchPage.jsp");   
    		rd.forward(request,response);
    	}
    	else if(request.getParameter("watchTopShowButton") != null) {
    		request.setAttribute("userName",userName);
    		RequestDispatcher rd=request.getRequestDispatcher("/watchPage.jsp");   
    		rd.forward(request,response);
    	}
    	//log out anywhere this button exists
    	else if(request.getParameter("logOutButton") != null) {
    		RequestDispatcher rd=request.getRequestDispatcher("/index.html");   
    		rd.forward(request,response);
    	}
    	
    	//catch all, log in failed
    	else {
    		RequestDispatcher rd=request.getRequestDispatcher("/index.html");   
    		rd.forward(request,response); 		}

    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
