<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>

	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Add To-Do</title>
	<link rel="stylesheet" type="text/css" href="../res/view.css" media="all">
	<script type="text/javascript" src="../res/view.js"></script>

</head>
<body>

	<img id="top" src="../images/top.png" alt="">
	<div id="form_container">
		<h1>To-Do List Manager</h1>
			
		<form id="form_1146737" class="appnitro">
			<div class="form_description">
				<h2>To-Do List Manager</h2>
				<p>This is a simple Cloud based to-do list manager.</p>		
			</div>		
		</form>	
					<c:choose>
				<c:when test="${ OK !=null }">
						<br /> <br />
						<div align="center" action="/">
						<h3>Add To-do</h3>
						<form method="post" >
							<br /> <input type="text" name="name" /><br /> 
							<input type="text" name="date" /><br />
							<input name="add" type="submit" /><br /><br />
						</form>
				</c:when>
				<c:otherwise>
						<div align="center">
							<h2>You are not Logged In</h2>
							<br/><br/> <a href="${login_url}">Sign in or Register</a>
						</div>
						<br /><br />
				</c:otherwise>
			</c:choose>
		</div>

		<div id="footer">
			Ranveer Rajpoot
		</div>
	</div>
	<img id="bottom" src="../images/bottom.png" alt="">
	<br />
</body>
</html>