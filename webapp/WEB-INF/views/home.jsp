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
<link
	href="<c:url value="/resources/css/plugins/datatable/jquery.dataTables.css"/>"
	rel="stylesheet" type="text/css" />

</head>
<body ng-app="app">
	<nav
		class="navbar navbar-inverse navbar-light bg-light justify-content-between">
	<a class="navbar-brand">Travel Management System (TMS)</a>
	<p class="p_tags_icons">Logout</p>
	<div id="home-nav">
		<a href="<c:url value='/logout' />"><i
			class="fa fa-sign-out fa-2x fa-fw" aria-hidden="true"></i></a>

	</div>
	</nav>
	<div align="center" style="width: 80%; margin: 0 auto"
		ng-controller="userController">

		<h1 id="allHeadingCss">User List</h1>
		<div
			style="float: left; width: 100%; text-align: right; margin-bottom: 5px">
			<div style="float: left;">
				<a href="addUser" class="btn btn-success">Add User</a>
			</div>
			<div style="float: right; width: 30%">
				<div style="float: left; margin-right: 8px">
					<a href="createRequest" class="btn btn-info">Create Travel
						Request</a>
				</div>
				<div style="float: right;">
					<a href="travelRequestList" class="btn btn-danger">Travel
						Request List</a>
				</div>
			</div>
		</div>
		<div style="float: right; font-size: 14px;"></div>
		<div style="clear: both; margin: 2px 0; color: red;" id="div_msg">${msg}</div>
		<input type="hidden" value="${loggedInUserRole}" id="loggedInUserRole" />
		<input type="hidden" value="${loggedInUserId}" id="loggedInUserId" />

		<table id="usertable" class="table table-striped table-bordered">
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
					<th style="width: 8%">Action</th>

				</tr>
			</thead>

			<tbody>
				<%-- <c:forEach var="user" items="${userList}" varStatus="status"> --%>
				<%-- <tr ng-repeat="user in userList">

					<td>{{$index + 1}}</td>
					<td>{{user.name}}</td>
					<td>{{user.email}}</td>
					<td>{{user.address}}</td>
					<td>{{user.telephone}}</td>
					<td><a href="editUser?id={{user.id}}" title="Edit user"><i
							class="fa fa-edit" style="color: blue; font-size: 20px"></i></a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a href="deleteUser?id=${user.id}"
						onclick="return confirm('Are you sure you want to delete this user?')"
						title="Delete user"><i class="fa fa-trash-o"
							style="color: red; font-size: 20px"></i></a></td>
				</tr> --%>
				<%-- </c:forEach> --%>
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
		src="<c:url value="/resources/js/controllers/userController.js"/>"
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
