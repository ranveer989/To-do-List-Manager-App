<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList"%>
<html>
<head>

<title>To-Do List Manager</title>
<link rel="stylesheet" type="text/css" href="../res/view.css" media="all">
<script type="text/javascript" src="../res/view.js"></script>
</script>
</head>

<body id="main_body">

	<img id="top" src="../images/top.png" alt="">
	<div id="form_container">
		<h1>To-Do List Manager</h1>
			
		<form id="form_1146737" class="appnitro" >
			<div class="form_description">
				<h2>To-Do List Manager</h2>
				<p>This is a simple Cloud based to-do list manager.</p>
			</div>		
		</form>	
		
		<c:choose>
			<c:when test="${user != null}">
				<p style="text-align: center">
					Signout Here <a href="${logout_url}">here</a><br />
				</p>
				<p style="text-align: center">
					<a href="${addLink}">ADD TO-DO</a><br />
				</p>
				
				<script>
					if (document.getElementById("list")) 
						document.getElementById("list").parentNode.removeChild(document.getElementById("list"));
					
				</script>
				<%
					ArrayList<String> todos = (ArrayList<String>) request.getAttribute("to-do-list");
					ArrayList<Boolean> check_list = (ArrayList<Boolean>) request.getAttribute("status");
							if (todos != null) {
								out.write("<div id= \"list\" align=\"center\" class=\"boxed1\">");
								out.write("<form method=\"post\">");
								out.write("<h2 align=\"center\">Your To-do List</h2><br/>");
								for (int i = 0; i < todos.size(); i++) {
									if (check_list.get(i))
										out.write("<input type=\"checkbox\"  checked name=\"" + (i + "chk") +"\" >"+todos.get(i));
									else
										out.write("<input type=\"checkbox\" name=\"" + (i + "chk") +"\" >"+ todos.get(i));
									out.write("<input type=\"submit\" name=\"" + (i+"edt")+"\" value=\"Edit\" \\>");
									out.write("<input type=\"submit\" name=\"" + (i+"dlt")+"\" value=\"Delete\" \\>");
									out.write("<img id=\"bottom\" src=\"../images/bottom.png\" alt=\"\">");
									out.write("<br/>");
								}
								out.write("</br><input type=\"submit\" style=\"height:8em; width:10em\" name=\"commit\" value=\"SYNC\" \\></br></br>");
								out.write("</form>");
								out.write("</div>");
							}
				%>
			</c:when>
			<c:otherwise>
				<div align="center">
					<br /> <br /> <a href="${login_url}">Sign in or register</a>
				</div>
				<br />
			</c:otherwise>
		</c:choose>
		<br /><br />
		
		<div id="footer">
			Ranveer Rajpoot
		</div>
	</div>
	<img id="bottom" src="../images/bottom.png" alt="">

	
</body>
</html>