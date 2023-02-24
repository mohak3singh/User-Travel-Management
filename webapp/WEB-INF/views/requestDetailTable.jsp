<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Travel request</title>
<%@ page isELIgnored="false"%>

<link href="<c:url value="/resources/css/bootstrap.min.css"/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet"
	type="text/css" />
<link
	href="<c:url value="/resources/font-awesome/css/font-awesome.css"/>"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/plugins/datetimepicker/jquery-ui.css"/>" />
<link
	href="<c:url value="/resources/css/plugins/datatable/jquery.dataTables.css"/>"
	rel="stylesheet" type="text/css" />
	
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"
	type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"
	type="text/javascript"></script>
<script
	src="<c:url value="/resources/js/plugins/datetimepicker/jquery-ui.js"/>"
	type="text/javascript"></script>

<script type="text/javascript">
	$(function() {
		$(".datepicker").datepicker({
			dateFormat : 'dd-mm-yy',
			maxDate : new Date()
		}).attr('readonly', 'readonly');

	});

	function goBack() {
		if (confirm('Click OK to go back')) {
			window.location.href = "/SpringJDBCDemo/travelRequestList";
		}
	}
</script>
</head>
<body ng-app="app">
	<nav id="navbar"
		class="navbar navbar-inverse navbar-light bg-light justify-content-between"
		style="display:none"> <a class="navbar-brand">Travel Management System (TMS)</a>

	<p class="p_tags_icons_home">Home</p>
	<p class="p_tags_icons">Logout</p>
	<div id="userForm-nav">
		<a href="<c:url value='/showUser' />"><i
			class="fa fa-home fa-2x fa-fw" aria-hidden="true"></i></a> <a
			href="<c:url value='/logout' />"><i
			class="fa fa-sign-out fa-2x fa-fw" aria-hidden="true"></i></a>
	</div>
	</nav>

	<div align="center" style="width: 60%; margin: 0 auto"
		ng-controller="travelRequestController" id="div_msg">
		<h1 id="allHeadingCss" action="detail">Request Detail Table</h1>

		<div style="clear: both; margin: 2px 0; color: red;" id="div_msg"></div>
		<table id="travelDetail" class="table table-striped table-bordered">
			<input type="hidden" value="${loggedInUserId}" id="travellerId"
				name="travellerId" />
			<input type="hidden" value="${travellerId}" name="travellerId"
				id="travellerId" />
			<input type="hidden" value="${travelId}" name="travelId"
				id="travelId" />
			<input type="hidden" value="${unitId}" name="unitId" id="unitId" />
			<input type="hidden" value="${action}" id="action" />
			<input type="hidden" value="${loggedInUserId}" id="loggedInUserId" />

			<tr id="travelReqNo" style="display: none">
				<td>Travel Request No.</td>
				<td id="travelReqNodt" >{{travelRequestDTO.travelReqNo}}</td>
			</tr>

			<tr>
				<td>Unit Name:</td>
				<td id="unitName" name="unitName">{{travelRequestDTO.unitName}}</td>
				<!-- <td><input name="unitName" type="text" class="form-control"
					id="unitName" ng-model="travelRequestDTO.unitName" /></td> -->


				<td>Name:</td>
				<td id="name" name="name">{{travelRequestDTO.travellerName}}</td>
				<!-- <input type="text" name="name" class="form-control"
					id="name" ng-model="travelRequestDTO.travellerName" /> -->
			</tr>

			<tr>
				<td>Email:</td>
				<td id="email" name="email">{{travelRequestDTO.email}}</td>
				<!-- <input type="email" name="email" class="form-control"
					id="email" ng-model="travelRequestDTO.email" /> -->
					
				<td>Gender:</td>
				<td id="gender" name="gender">{{travelRequestDTO.gender}}</td>
				<!-- <input type="text" name="gender" class="form-control"
					id="gender" ng-model="travelRequestDTO.gender" /> -->
			</tr>

			<tr>
				<td>Telephone:</td>
				<td id="telephone" name="telephone">{{travelRequestDTO.telephone}}</td>
			<!-- 	<input name="telephone" type="text" class="form-control"
					id="telephone" maxlength="10" ng-model="travelRequestDTO.telephone" /> -->
				<td>Address:</td>
				<td id="address" name="address">{{travelRequestDTO.address}}</td>
			<!-- 	<input name="address" type="text" class="form-control"
					id="address" ng-model="travelRequestDTO.address" /> -->
			</tr>

			<tr>
				<td>Date of Birth:</td>
				<td id="dateOfBirth" name="dateOfBirth">{{travelRequestDTO.dateOfBirth}}</td>
				<!-- <input name="dateOfBirth" type="text"
					class="form-control datepicker" id="dateOfBirth"
					ng-model="travelRequestDTO.dateOfBirth" style="background: #fff" /></td> -->

				<td>Travel Mode:</td>
				<td id="travelMode" name="travelMode">
				{{travelRequestDTO.travelModeName}}</td>
			</tr>
			
			<tr>
				<td>Travel From:</td>
				<td id="travelFrom" name="travelFrom">{{travelRequestDTO.travelFrom}}</td>
				<!-- <input name="travelFrom" type="text" class="form-control"
					id="travelFrom" ng-model="travelRequestDTO.travelFrom" /> -->
				<td>Travel To:</td>
				<td id="travelTo" name="travelTo">{{travelRequestDTO.travelTo}}</td>
				<!-- <input name="travelTo" type="text" class="form-control"
					id="travelTo" ng-model="travelRequestDTO.travelTo" /> -->
			</tr>
			<tr>
				<td>Travel Date:</td>
				<td id="travelDate" name="travelDate">{{travelRequestDTO.travelDate}}</td>
				<!-- <input name="travelDate"
					placeholder="Select your Travel Date" type="text"
					class="form-control datepicker" id="travelDate"
					ng-model="travelRequestDTO.travelDate" style="background: #fff" /></td> -->

				<td>Reason for Travel:</td>
				<td id="reasonForTravel" name="reasonForTravel">{{travelRequestDTO.reasonForTravel}}</td>
			<!-- 	<input type="text" name="reasonForTravel"
					class="form-control" id="reasonForTravel"
					ng-model="travelRequestDTO.reasonForTravel" /> -->
			</tr>
		</table>
		<table style="width: 17%">
			<tr>
				<td align="left"><input type="button" value="close"
					onclick="window.close()" class="btn btn-danger"
					id="close_btn_userform" style="display: none"> <input
					type="button" value="Back" onclick="goBack()"
					class="btn btn-danger" id="back_btn_userform" style="display: none">
				</td>
			</tr>
		</table>
	</div>
	<footer id="footer" style="background-color: #485460; display:none">
	<div class="text-center p-3">MOHAK</div>
	</footer>
	</div>
	</div>
	<script src="<c:url value="/resources/js/angularjs/angular.min.js"/>"
		type="text/javascript"></script>
	<script
		src="<c:url value="/resources/js/controllers/travelRequestController.js"/>"
		type="text/javascript"></script>
</body>

</html>