<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@ page isELIgnored="false"%>

<link href="<c:url value="/resources/css/bootstrap.min.css"/>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value="/resources/font-awesome/css/font-awesome.css"/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet"
	type="text/css" />
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"
	type="text/javascript"></script>

</head>
<body>
	<nav
		class="navbar navbar-inverse navbar-light bg-light justify-content-between">
	<a class="navbar-brand">Travel Management System (TMS)</a> </nav>
	<div>
		<div class="row justify-content-center">
			<section class="col-12 colsm-6 col-md-3"> 
			<form:form action="loginProcess" name="myForm" method="post"
				modelAttribute="loginDTO" class="container" style="width: 400px;" id="form-css">
				<div>
					<h1 id="login-h">Please Sign In</h1>
					<br> <label>Username : </label> <input name="username"
						type="text" id="username" path="username"
						placeholder="Enter your username" autocomplete="off"
						class="form-control " style="width: 300px;" /> <br> <span
						id="show_error"></span>
				</div>

				<div>
					<label>Password :</label> <input name="password" type="password"
						id="password" placeholder="Enter your password" autocomplete="off"
						path="password" class="form-control" style="width: 300px;" /> <br>
					<span id="show_pass_error"></span>
				</div>
				<div class="text-danger font-weight-bold">${msg}</div>

				<div id="save-btn">
					<input type="submit" name="submit" class="btn btn-success"
						value="Login" id="submit">
						
					<div id="register-btn" style="display: inline">
						<a href="/addUser" target="popup"
						onclick="myPopup('http://localhost:8082/SpringJDBCDemo/addUser','popup',1200,500);return false;">
							<!-- onclick="window.open('http://localhost:8082/SpringJDBCDemo/addUser','popup','width=1200,height=500'); return false;"> -->
							Register User </a>
					</div>
				</div>

			</form:form> </section>
		</div>
		<footer id="footer" style="background-color: #485460;">
		<div class="text-center p-3">MOHAK</div>
		</footer>
	</div>

</body>
<script type="text/javascript">
	$(document).ready(
			function() {
				$('#submit').click(
						function() {
							var user = $('#username').val();
							var password = $('#password').val();
							var upperCase = new RegExp('[A-Z]');
							var numbers = new RegExp('[0=9]');
											if (user == "") {
												$('#show_error').html('** The username should not be empty');
												$('#show_error').css('color','red');
												return false;
											}
											if ((user.length <= 2) || (user.length >= 26)) {
												$('#show_error').html('** The username must be between 3 to 25 letters');
												$('#show_error').css('color','red');
												return false;
											}

											if ((password.length <= 2)
													|| (password.length >= 12)) {
												$("#show_pass_error").html('** Length should be between 3 - 12');
												$("#show_pass_error").css('color', 'red');
												return false;
											}
										});
					});
	
	function myPopup(myURL, title, myWidth, myHeight) {
        var left = (screen.width - myWidth) / 2;
        var top = (screen.height - myHeight) / 4;
        var myWindow = window.open(myURL, title,'toolbar=no,location=no,directories=no,status=no,menub ar=no,scrollbar=no,resizable=no,copyhistory=yes, width=' + myWidth + ', height=' + myHeight + ', top=' + top + ', left=' + left);
     }
	
</script>

</html>