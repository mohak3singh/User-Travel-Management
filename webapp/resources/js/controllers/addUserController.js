angular.module("app", []).controller("addUserController",
				function($scope, $http) {
	
					$scope.userDTO = {
						name : "",
						email : "",
						gender : "0",
						userRole : "0",
						address : "",
						telephone : "",
						username : "",
						password : "",
						unitId : 0,
						dateOfBirth : ""
					};
					
					$scope.unitDTO = {
							unitId : 0,
							unitName : "Please select"
					};
					
					
					$scope.saveUser = function() {
						/*var action = angular.element(document.querySelector("#action")).val();
				        if (action != null && action != "" && action == "add") {
				        	$scope.userDTO.userRole = "OR";
				        }*/
				        
						$http({
							url : "/SpringJDBCDemo/saveUser",
							method : 'POST',
							headers : {
								'Content-Type' : 'application/json'
							},
							data : angular.toJson($scope.userDTO)
						}).then(function(response) {
											$("#div_msg").text("");
											if (response.data.status != null
													&& response.data.status == "0") {
												$("#div_msg").text("Username already exist");
											} else {
												window.location.href = "/SpringJDBCDemo/showUser?status=" + response.data.status;
												window.close();
											}
										},
										function(errResponse) {
											console.log("error while saving user"+ errResponse);
									    });
					}

					$scope.validate = function() {

						var name = document.getElementById("name").value;
						var email = document.getElementById("email").value;
						var address = document.getElementById("address").value;
						var telephone = document.getElementById("telephone").value;
						var username = document.getElementById("usrname").value;
						var password = document.getElementById("pwd").value;
						var gender = document.getElementById("gender");
						var userrole = document.getElementById("userRole");
						var unitname = document.getElementById("unitName");
						var dateofbirth = document.getElementById("dateOfBirth");

//						var namecheck = /^[a-zA-Z ]+$/;
//						var emailcheck = /^[0-9A-Za-z]+[.+-_]{0,1}[0-9a-zA-Z]+[@][a-zA-Z0-9]+[.+-_][a-zA-Z]+[.]{0,1}[a-zA-Z]{2,5}/;
						var addresscheck = /^[A-Za-z0-9. -]{3,40}$/;
						var telephonecheck = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/;
						var passwordcheck = /^[a-zA-Z0-9]{5,16}$/;
						var usernamecheck = /^\w[a-zA-Z@#0-9.]*$/;

						if (unitname.value == "0") {
							alert("Please select your unit");
							unitname.focus();
							return false;
						}
						
						if(name == ""){
							alert("Enter your name");
							name.focus();
							return false;
						}
						if(email == ""){
							alert("Enter your Email");
							email.focus();
							return false;
						}
//						if (namecheck.test(name)) {
//							document.getElementById('username').innerHTML = " ";
//						} else {
//							document.getElementById('username').innerHTML = "*** Name is invalid : only characters are allowed";
//							return false;
//							name.focus();
//						}
//						if (emailcheck.test(email)) {
//							document.getElementById('erroremail').innerHTML = " ";
//						} else {
//							document.getElementById('erroremail').innerHTML = "*** Email format is incorrect";
//							return false;
//							email.focus();
//						}
						if (gender.value == "0") {
							alert("Please select your gender");
							gender.focus();
							return false;
						}
						if (userrole.value == "0" && $scope.userDTO.userRole == "AD") {
							alert("Please select your Role");
							gender.focus();
							return false;
						}
						if (addresscheck.test(address)) {
							document.getElementById('erroraddress').innerHTML = " ";
						} else {
							document.getElementById('erroraddress').innerHTML = "*** Address format is incorrect";
							return false;
						}
						if (telephonecheck.test(telephone)) {
							document.getElementById('errortelephone').innerHTML = " ";
						} else {
							document.getElementById('errortelephone').innerHTML = "***  Number is not correct";
							return false;
						}
						if (usernamecheck.test(username)) {
							document.getElementById('errorusername').innerHTML = " ";
						} else {
							document.getElementById('errorusername').innerHTML = "***  Username is not correct";
							return false;
						}
						if (passwordcheck.test(password)) {
							document.getElementById('errorpassword').innerHTML = " ";
						} else {
							document.getElementById('errorpassword').innerHTML = "*** Invalid format: Min 5 characters";
							return false;
						}
						if (dateofbirth.value == "0") {
							alert("Please select your Date of birth");
							dateofbirth.focus();
							return false;
						}
						$scope.saveUser();
					}

					$scope.getUserById = function() {

						var userId = angular.element(document.querySelector("#userId")).val();
						$http({
							url : "/SpringJDBCDemo/getUserById/" + userId,
							method : 'GET'
						}).then(
								function(response) {
									$scope.userDTO = angular.copy(response.data);
								}, function(errResponse) {
									console.log("error while edit user");
								});
					}
					
					$scope.getUnitList = function() {
						$http({
							url : "/SpringJDBCDemo/getUnitList",
							method : 'GET'
						}).then(
								function(response) {
									$scope.unitDTO = angular.copy(response.data);
								}, function(errResponse) {
									console.log("error while fetching unit");
						});
					}
					
					angular.element(document).ready(
									function() {
										$scope.loggedInUserRole = angular.element(document.querySelector("#loggedInUserRole")).val();
										if($scope.loggedInUserRole != ""){
											$("nav#navbar").css("display","block");
											$("footer#footer").css("display","block");
											$("input#back_btn_userform").css("display","block");
											$("input#close_btn_userform").css("display","none");
										}else{
											$("nav#navbar").css("display","none");
											$("footer#footer").css("display","none");
											$("input#back_btn_userform").css("display","none");
											$("input#close_btn_userform").css("display","block");
										}
										var action = angular.element(document.querySelector("#action")).val();
										if (action != null && action != "" && action == "edit") {
											$scope.getUserById();
										}
										$scope.getUnitList();
									});
					
				
					
				});