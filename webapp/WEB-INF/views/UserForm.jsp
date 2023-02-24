<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add/Edit User</title>
<%@ page isELIgnored="false"%>

<link href="<c:url value="/resources/css/bootstrap.min.css"/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet"
	type="text/css" />
<link
	href="<c:url value="/resources/font-awesome/css/font-awesome.css"/>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value="/resources/css/plugins/datatable/jquery.dataTables.css"/>"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/plugins/datetimepicker/jquery-ui.css"/>" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/dt/dt-1.10.24/datatables.min.css" />
<script type="text/javascript"
	src="https://cdn.datatables.net/v/dt/dt-1.10.24/datatables.min.js"></script>
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
		})

	});

	function goBack() {
		if (confirm('Click OK to go back')) {
			window.location.href = "/SpringJDBCDemo/showUser";
		}
	}
	function AvoidSpace(event) {
		var k = event ? event.which : window.event.keyCode;
		if (k == 32)
			return false;
	}

	function removeSpaces(string) {
		return string.split(' ').join('');
	}
	
	function validateName(){
	    var validatedName = "";
	    var restrictedCharactersArray = ["0","1","2","3","4","5","6","7","8","9","~","`","!","@","#","$","%","^","&","*","(",")","-","_",
	        "+","=","{","}","[","]",":",";","'","<",">",",",".","?","/","/\/","|"];
	    var customerName = document.getElementById("name").value;
	    var numberValidation = (/^[a-zA-Z_ ]+$/g).test(customerName);
	    if(!numberValidation){
	        validatedName = "";
	        var customerNameArray = customerName.split("");
	        for(var i=0;i<restrictedCharactersArray.length;i++){
	            var restrictedCharacter = restrictedCharactersArray[i];
	            if(customerNameArray.indexOf(restrictedCharacter) !== -1){
	                for(var j=0; j<customerNameArray.length; j++){
	                    var customerNameCharacter = customerNameArray[j];
	                    if(customerNameCharacter !== restrictedCharacter){
	                        validatedName = validatedName+customerNameCharacter;
	                    }
	                }
	            }
	        }
	        document.getElementById("name").value = validatedName;
	    }
	}
	
	function validateEmail(){
	    var validatedName = "";
	    var restrictedCharactersArray = ["~","`","!","#","$","%","^","&","*",",","(",")","-","_","+","=","{","}","[","]",":",";","'","<",">","?","/","/\/","|"];
	    var customerName = document.getElementById("email").value;
	    var numberValidation = (/^[0-9A-Za-z@.]+$/g).test(customerName);
	    if(!numberValidation){
	        validatedName = "";
	        var customerNameArray = customerName.split("");
	        for(var i=0;i<restrictedCharactersArray.length;i++){
	            var restrictedCharacter = restrictedCharactersArray[i];
	            if(customerNameArray.indexOf(restrictedCharacter) !== -1){
	                for(var j=0; j<customerNameArray.length; j++){
	                    var customerNameCharacter = customerNameArray[j];
	                    if(customerNameCharacter !== restrictedCharacter){
	                        validatedName = validatedName+customerNameCharacter;
	                    }
	                }
	            }
	        }
	        document.getElementById("email").value = validatedName;
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
		ng-controller="addUserController">
		<h1 id="allHeadingCss" action="add">Register User</h1>
		<!-- 	<h1 action="edit">Edit User</h1> -->

		<div style="clear: both; margin: 2px 0; color: red;" id="div_msg"></div>
		<table id="userDetail" class="table table-striped table-bordered">
			<input type="hidden" value="${userId}" name="id" id="userId" />
			<input type="hidden" value="${action}" id="action" />
			<input type="hidden" value="${loggedInUserRole}" id="loggedInUserRole" />
			<input type="hidden" value="${loggedInUserId}" id="loggedInUserId" />

			<tr>

				<td>Unit Name:</td>
				<td><select name="unitId" title=" Select your Unit"
					class="form-control" id="unitName" ng-model="userDTO.unitId">
						<option ng-repeat="unit in unitDTO" value="{{unit.unitId}}"
							ng-selected="unit.unitId==0">{{unit.unitName}}</option>
				</select></td>

				<td>Name:</td>
				<td><input type="text" name="name"
					placeholder="Enter your Name" class="form-control"
					autocomplete="off" ng-model="userDTO.name" id="name" onKeyUp="validateName();" /> <span id="name"
					class="text-danger font-weight-bold"></span></td>

			</tr>
			<tr>

				<td>Email:</td>
				<td><input type="email" name="email"
					placeholder="Enter your Email" class="form-control" id="email"
					autocomplete="off" ng-model="userDTO.email" onKeyUp="validateEmail();"/> <span
					id="erroremail" class="text-danger font-weight-bold"></span></td>

				<td>Gender:</td>
				<td><select name="gender" title="Select your gender"
					class="form-control" id="gender" name="gender"
					ng-model="userDTO.gender">
						<option value="0">Please select</option>
						<option value="M">Male</option>
						<option value="F">Female</option>
				</select> <span id="gender" class="text-danger font-weight-bold"></span></td>

			</tr>
			<tr>
				<td>Address:</td>
				<td><input name="address" placeholder="Enter your Address"
					type="text" class="form-control" id="address" autocomplete="off"
					ng-model="userDTO.address" /> <span id="erroraddress"
					class="text-danger font-weight-bold"></span></td>
				<td>Telephone:</td>
				<td><input name="telephone" placeholder="Enter your Number"
					type="text" class="form-control" id="telephone" autocomplete="off"
					maxlength="10" ng-model="userDTO.telephone" /> <span
					id="errortelephone" class="text-danger font-weight-bold"></span></td>
			</tr>
			<tr>
				<td>Username:</td>
				<td><input name="username" placeholder="Enter your Username"
					type="text" class="form-control" id="usrname" autocomplete="off"
					ng-model="userDTO.username" onkeypress="return AvoidSpace(event);"
					onblur="this.value=removeSpaces(this.value);" /> <span
					id="errorusername" class="text-danger font-weight-bold"></span></td>
				<td>Password:</td>
				<td><input name="password" placeholder="Enter your Password"
					type="password" class="form-control" id="pwd" autocomplete="off"
					ng-model="userDTO.password" /> <span id="errorpassword"
					class="text-danger font-weight-bold"></span></td>
			</tr>
			<tr>

				<td>Date of Birth:</td>
				<td><input name="dateOfBirth"
					placeholder="Enter your Date of Bith" type="text"
					class="form-control datepicker" id="dateOfBirth"
					ng-model="userDTO.dateOfBirth" style="background: #fff" /></td>
					
					<!-- ng-if="loggedInUserRole == 'AD' || loggedInUserRole == 'LA'" -->
				<td>User Role:</td>

				<td><select name="userRole"
					title="Select your Role" class="form-control" id="userRole"
					ng-model="userDTO.userRole">
						<option value="0">Please select</option>
						<!-- <option value="AD">Admin</option> -->
						<option value="LA">Local Admin</option>
						<option value="OR">OR</option>
				</select> <span id="userRole" class="text-danger font-weight-bold"></span></td>

				<!-- <td ng-if="loggedInUserRole == 'LA'"><select name="userRole"
					title="Select your Role" class="form-control" id="userRole"
					ng-model="userDTO.userRole">
						<option value="0">Please select</option>
						<option value="LA">Local Admin</option>
						<option value="OR">OR</option>
				</select> <span id="userRole" class="text-danger font-weight-bold"></span></td>

				<td ng-if="loggedInUserRole != 'AD' || loggedInUserRole != 'LA'"
					colspan="2"><input type="hidden" name="userRole"
					ng-model="userDTO.userRole" id="userRole" /></td> -->
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
				<td align="right" style="width: 20px;"><input type="button"
					ng-click="validate()" name=" submit" class="btn btn-success"
					value="Save"></td>
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
		src="<c:url value="/resources/js/controllers/addUserController.js"/>"
		type="text/javascript"></script>
</body>

</html>