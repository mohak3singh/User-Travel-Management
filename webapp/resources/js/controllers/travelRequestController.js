angular.module("app", []).controller(
				"travelRequestController",
				function($scope, $http) {

					angular.element(document).ready(
							function() {
								var action = angular.element(document.querySelector("#action")).val();
								/*var action = document.getElementById("action").value;*/
								if (action != null && action != "" && action == "create") { 
									$scope.getTravellerInfoById();
									$("tr#travelReqNo").css("display","none");
								}else if (action != null && action != "" && action == "list") {
									$scope.getTravelRequestList();
								}else if (action != null && action != "" && action == "edit") {
									$scope.getRequestDetailForEdit();
									$("tr#travelReqNo").css("display","table-row");
									$("td#travelReqNodt").attr("colspan",3);
								}else if (action != null && action != "" && action == "detail") {
									$scope.getRequestDetailForEdit();
									$("tr#travelReqNo").css("display","table-row");
									$("td#travelReqNodt").attr("colspan",3);
								}
								/*angular.element(document.querySelector("#unitName")).css("color","red")*/
							});
					
					$scope.travelRequestDTO = {
						travelId : "",
						travelReqNo : "",
						unitId : "",
						unitName : "",
						travellerId : "",
						travellerName : "",
						email : "",
						gender : "",
						telephone : "",
						address : "",
						dateOfBirth : "",
						travelModeName : "",
						travelFrom : "",
						travelTo : "",
						travelDate : "",
						reasonForTravel : "",
					};

					$scope.getTravellerInfoById = function() {

						var travellerId = angular.element(document.querySelector("#loggedInUserId")).val();
						/*var travellerId = document.getElementById("loggedInUserId").value;*/
						$http(
								{
									url : "/SpringJDBCDemo/getTravellerInfoById/"+ travellerId,
									method : 'GET'
								})
								.then(
										function(response) {
											$scope.travelRequestDTO = angular.copy(response.data);
										},
										function(errResponse) {
											console.log("error while creating request");
										});
					}
					
					$scope.getRequestDetailForEdit = function() {

						// javascript
					/*	var travellerId = document.getElementById("travellerId").value;
						var travelId = document.getElementById("travelId").value;
						var unitId = document.getElementById("unitId").value;*/
						
						// angular 
						var travellerId = angular.element(document.querySelector("#travellerId")).val();
						var travelId = angular.element(document.querySelector("#travelId")).val();
						var unitId = angular.element(document.querySelector("#unitId")).val();
						console.log("a ="+ travelId + " b =" + unitId + " c =" + travellerId);
						$http(
								{
									url : "/SpringJDBCDemo/getRequestDetailForEdit/"+ travellerId+ "/"+travelId +"/"+unitId,
									method : 'GET'
								})
								.then(
										function(response) {
											$scope.travelRequestDTO = angular.copy(response.data);
											
										},
										function(errResponse) {
											console.log("error while creating request");
										});
					}

					$scope.saveTravelRequest = function() {
						/*
						 * var action = document.getElementById("action").value;
						 * if (action != null && action != "" && action ==
						 * "create") { $scope.travelRequestDTO.userRole = "OR"; }
						 */

						$http({
							url : "/SpringJDBCDemo/saveTravelRequest",
							method : 'POST',
							headers : {
								'Content-Type' : 'application/json'
							},
							data : angular.toJson($scope.travelRequestDTO)
						})
								.then(
										function(response) {
											console.log("data- "+ JSON.stringify(response.data));
											$("#div_msg").text("");
											if (response.data.status != null && response.data.status == "0") {
												$("#div_msg").text("travel request details exist");
											} else {
												window.location.href = "/SpringJDBCDemo/showUser?status="+ response.data.status;
											}
										},
										function(errResponse) {
											console.log("error while saving travel request"+ errResponse);
										});
					}

					$scope.validateRequestData = function() {

						/*var travelFrom = document.getElementById("travelFrom").value;
						var travelTo = document.getElementById("travelTo").value;
						var travelMode = document.getElementById("travelMode");
						var travelDate = document.getElementById("travelDate");
						var reasonfortravel = document.getElementById("reasonForTravel").value;*/
						
						var travelFrom = angular.element(document.querySelector("#travelFrom")).val();
						var travelTo = angular.element(document.querySelector("#travelTo")).val();
						/*var travelMode = angular.element(document.querySelector("#travelMode"));
						var travelDate = angular.element(document.querySelector("#travelDate"));*/
						var travelMode = document.getElementById("travelMode");
						var travelDate = document.getElementById("travelDate");
						var reasonfortravel = angular.element(document.querySelector("#reasonForTravel")).val();

						var citynamecheck = /^[A-Za-z ]{3,20}/;
						var travelreason = /^[A-Za-z ]{3,60}/;

						if (travelMode.value == "0") {
							alert("Please select Mode of travel");
							travelMode.focus();
							return false;
						}
						if (citynamecheck.test(travelFrom)) {
							document.getElementById('travelName').innerHTML = " ";
						} else {
							document.getElementById('travelName').innerHTML = "*** Enter correct City Name";
							return false;
							travelFrom.focus();
						}
						if (citynamecheck.test(travelTo)) {
							document.getElementById('travelToName').innerHTML = " ";
						} else {
							document.getElementById('travelToName').innerHTML = "*** Enter correct City Name";
							return false;
							travelTo.focus();
						}
						if (travelDate.value == "0") {
							alert("Please select Date of Travel");
							travelDate.focus();
							return false;
						}
						if (travelreason.test(reasonfortravel)) {
							document.getElementById('travelReasonError').innerHTML = " ";
						} else {
							document.getElementById('travelReasonError').innerHTML = "*** Enter reason for travel";
							return false;
							reasonfortravel.focus();
						}
						$scope.saveTravelRequest();
					}
					

					angular.element(document).ready(
									function() {
										$scope.loggedInUserRole = angular.element(document.querySelector("#loggedInUserId")).val();
										if ($scope.loggedInUserId != "") {
											$("nav#navbar").css("display",
													"block");
											$("footer#footer").css("display",
													"block");
											$("input#back_btn_userform").css(
													"display", "block");
											$("input#close_btn_userform").css(
													"display", "none");
										} else {
											$("nav#navbar").css("display",
													"none");
											$("footer#footer").css("display",
													"none");
											$("input#back_btn_userform").css(
													"display", "none");
											$("input#close_btn_userform").css(
													"display", "block");
										}
									});

					var table;

					$scope.getTravelRequestList = function() {
						table = $("table#travelRequestTable").DataTable(
										{
											"processing": true,
											"serverSide": false,
											"lengthMenu": [[5, 10, 20, -1], [5, 10, 20, "All"]],
											'ordering': true,
								            'order': [[0, 'asc']],
								            'searching': true,
								            

											"ajax" : {
												"url" : "/SpringJDBCDemo/getTravelRequestList",
												"type" : "GET",
												"dataType" : "json",
												"dataSrc" : "",
												"data": function ( d ) {
													var params = $.extend({},d,{
														"loggedInUserRole" : $('#loggedInUserRole').val(),
														"loggedInUserId" : $('#loggedInUserId').val()
													});
														return params;
												  	}
											},
											
											"fnInitComplete" : function(oSettings, json) {
												$('div#travelRequestTable_info').css({"float" : "left"});
											},
											
											"columnDefs": [
											               {
											            	   "targets" : 2,
											            	   "data" : "active",
											            	   "render" : function(data, type, full, meta) {
											            		   var loggedInUserId = angular.element(document.querySelector("#loggedInUserId")).val();
											            		   var loggedInUserRole = angular.element(document.querySelector("#loggedInUserRole")).val();
																	if(loggedInUserId == full.travellerId || loggedInUserRole == 'AD'){
											            		   return '<a href="getRequestDetail?travelId='
																	+ full.travelId+ "&unitId="+ full.unitId+ "&travellerId="+ full.travellerId
																	+ '" title="' + full.reasonForTravel + '" onclick=\"angular.element(this).scope().viewDetails(\'' + full.travellerId + '\',\''+ full.travelId + '\',\''+ full.unitId + '\')\" >'+ full.travelReqNo + '</a>'
																	}
											            	   },

											               } ],

											"columns" : [ {
												"data" : "srNo", "width" : "4%",
											}, {
												"data" : "unitName"
											}, {
												"data" : "travelReqNo"
											}, {
												"data" : "travellerName",  "width" : "5%",
											}, {
												"data" : "email", "width" : "7%",
											}, {
												"data" : "telephone"
											}, {
												"data" : "dateOfBirth"
											}, {
												"data" : "travelModeName"
											}, {
												"data" : "travelFrom"
											}, {
												"data" : "travelTo"
											}, {
												"data" : "travelDate"
											},
											{
												"data" : "action","width" : "7%",
												"render" : function(
														data, type,
														full, meta) {
													var loggedInUserId = angular.element(document.querySelector("#loggedInUserId")).val();
													var loggedInUserRole = angular.element(document.querySelector("#loggedInUserRole")).val();
													//alert(loggedInUserId);
													if(loggedInUserId == full.travellerId || loggedInUserRole == 'AD'){
													var columnTxt = '<a href="editRequestList?travelId='
															+ full.travelId+ "&unitId="+ full.unitId+ "&travellerId="+ full.travellerId
															+ '" title="Edit user"><i class="fa fa-edit" style="color: blue; font-size: 20px"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;'
															+ '<a href="#" onclick=\"angular.element(this).scope().confirmDeleteRequestList('
															+ full.travellerId +","+ full.travelId+ "," + full.unitId
															+ ')\" title="Delete user"><i class="fa fa-trash-o" style="color: red; font-size: 20px"></i></a>';
													}
													
													return columnTxt;
												}
											}]
										});
					};
					
					$scope.viewDetails = function(travellerId, travelId, unitId) {
						var travellerId = angular.element(document.querySelector("#travellerId")).val();
						var travelId = angular.element(document.querySelector("#travelId")).val();
						var unitId = angular.element(document.querySelector("#unitId")).val();
						
						$http(
								{
									url : "/SpringJDBCDemo/getRequestDetail/"+ travellerId+ "/"+travelId +"/"+unitId,
									method : 'GET'
								})
								.then(
										function(response) {
											$scope.travelRequestDTO = angular.copy(response.data);
											console.log("data "+ JSON.stringify($scope.travelRequestDTO));
										},
										function(errResponse) {
											console.log("error while creating request");
										});
					}
					
					$scope.deleteRequestListUser = function(travellerId, travelId, unitId) {
						$http({
									url : "/SpringJDBCDemo/deleteRequestListUser",
									method : 'POST',
									headers : {
										'Content-Type' : 'application/x-www-form-urlencoded'
									},
									params : {
										travellerId : travellerId,
										travelId : travelId,
										unitId : unitId
									}
								})
								.then(function(response) {
											$("#div_list_msg").text("");
											table.ajax.url("/SpringJDBCDemo/getTravelRequestList").load();
											if(response.data != null && response.data == "0"){
												$("#div_list_msg").text("Travel requested detail deleted successfully");
											}else{
												$("#div_list_msg").text("Travel requested detail not Deleted : error occured");
											}
										},
										function(errResponse) {
											console.log("error while deleting Requested detail");
										});
					}
					
					$scope.confirmDeleteRequestList = function(travellerId, travelId, unitId) {
						if (confirm('Are you sure you want to delete this list?')) {
							$scope.deleteRequestListUser(travellerId, travelId, unitId);
						}
					};	
				});