<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
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
	<br />
	<c:choose>
		<c:when test="${ OK==null }">
			<div align="center">
				<br /> <br /> <a href="${login_url}">Sign in or register</a>
			</div>
			<br/><br/>
		</c:when>
		
		<c:otherwise>
			<div align="center" action="/">
				<form method="post">
					<br /> <br />
					<h2>Update Task</h2>
					<br /> <input type="text" name="name"
						value=<%=request.getAttribute("name")%> /><br /> <input
						type="text" name="date" value=<%=request.getAttribute("date")%> /><br />
					 <input sname="Update" type="submit" />
				</form>
				<br /> <br />
			</div><br /><br />
		</c:otherwise>
	</c:choose>
	
	<div id="footer">
			Ranveer Rajpoot
		</div>
	</div>
	<img id="bottom" src="../images/bottom.png" alt="">
</body>
</html>