<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Master Home</title>
<%@ page isELIgnored="false"%>

<link
	href="<c:url value="/resources/font-awesome/css/font-awesome.css"/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/bootstrap.min.css"/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet"
	type="text/css" />
<link
	href="<c:url value="/resources/css/plugins/datatable/dataTables.bootstrap.css"/>"
	rel="stylesheet" type="text/css" />


</head>
<body ng-app="app">
	<nav
		class="navbar navbar-inverse navbar-light bg-light justify-content-between">
	<a class="navbar-brand">Travel Management System (TMS)</a>
	<p class="p_tags_icons_home">Home</p>
	<p class="p_tags_icons">Logout</p>
	<div id="userForm-nav">
		<a href="<c:url value='/showUser' />"><i
			class="fa fa-home fa-2x fa-fw" aria-hidden="true"></i></a> <a
			href="<c:url value='/logout' />"><i
			class="fa fa-sign-out fa-2x fa-fw" aria-hidden="true"></i></a>

	</div>
	</nav>
	<div align="center" style="width: 80%; margin: 0 auto"
		ng-controller="viewListController">

		<h1 id="allHeadingCss">User List</h1>
		<div style="float: left; width: 60%; text-align: right;"></div>
		<div style="float: right; font-size: 14px;"></div>

		<table id="viewtable" class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>Sr. No.</th>
					<th>Unit</th>
					<th>Name</th>
					<th>Email</th>
					<th>Telephone</th>
					<th>Date of Birth</th>
					<th>User Role</th>
					<th>Address</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	<footer id="footer" style="background-color: #485460;">
	<div class="text-center p-3">MOHAK</div>
	</footer>
	</div>
	</div>
	<script src="<c:url value="/resources/js/angularjs/angular.min.js"/>"
		type="text/javascript"></script>
	<script
		src="<c:url value="/resources/js/controllers/viewListController.js"/>"
		type="text/javascript"></script>
	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"
		type="text/javascript"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"
		type="text/javascript"></script>
	<script
	src="<c:url value="/resources/js/plugins/datatable/jquery.dataTables.js"/>"
	type="text/javascript"></script>
<script
	src="<c:url value="/resources/js/plugins/datatable/dataTables.bootstrap.js"/>"
	type="text/javascript"></script>

</body>
</html>