angular.module("app", []).controller("userController",function($scope, $http) {
	                
					$scope.userList = null;
					
					var table;
					$scope.getData = function() {
						table = $("#usertable").DataTable({	
									
											"processing": true,
											"serverSide": false,
											"lengthMenu": [[5, 10, 20, -1], [5, 10, 20, "All"]],
											'ordering': true,
								            'order': [[0, 'asc']],
								            'searching': true,
								            
											"ajax" : {
												"url" : "/SpringJDBCDemo/getList",
												/*"contentType" : "application/json",*/
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
												$('div#usertable_info').css({"float" : "left"});
											},
											
											"fnRowCallback" : function(nRow, data){
												if(data.userRole.toUpperCase() == "ADMIN"){
													$(nRow).find("td:eq(8) a#deleteBtn").css("display", "none");
													$(nRow).find("td:eq(8) a#editBtn").css("display", "none");
												}
											},
											
											"columns" : [
													{
														"data" : "srNo"
													},
													{
														"data" : "unitName"
													},
													{
														"data" : "name"
													},
													{
														"data" : "email"
													},
													{
														"data" : "telephone"
													},
													{
														"data" : "dateOfBirth"
													},
													{
														"data" : "userRole"
													},
													{
														"data" : "address"
													},
													{
														"data" : "action",
														"render" : function(
																data, type,
																full, meta) {console.log("data"+data);
															var columnTxt = '<a href="editUser?id='
																	+ full.id
																	+ '" title="Edit user" id="editBtn"><i class="fa fa-edit" style="color: blue; font-size: 20px"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;'
																	+ '<a href="#" onclick=\"angular.element(this).scope().confirmDelete('
																	+ full.id
																	+ ')\" title="Delete user" id="deleteBtn"><i class="fa fa-trash-o" style="color: red; font-size: 20px"></i></a>';
															return columnTxt;
														}
													}]
										}).draw();
					};
					
					$scope.getData();
					
					$('#usertable thead tr').each(function() {
						  if ($('.status', this).text() == 'assigned') {
						    $('.btn-assign', this).prop("disabled", true);
						  }
						});

					$scope.deleteUser = function(userId) {
						$http({
									url : "/SpringJDBCDemo/deleteUser",
									method : 'POST',
									headers : {
										'Content-Type' : 'application/x-www-form-urlencoded'
									},
									params : {
										id : userId
									}
								})
								.then(function(response) {
											$("#div_msg").text("");
											table.ajax.url("/SpringJDBCDemo/getList").load();
											if(response.data != null && response.data == "0"){
												$("#div_msg").text("User deleted successfully");
											}else{
												$("#div_msg").text("User not Deleted : error occured");
											}
										},
										function(errResponse) {
											console.log("error while deleting user");
										});
					}
					
					$scope.confirmDelete = function(id) {
						if (confirm('Are you sure you want to delete this user?')) {
							$scope.deleteUser(id);
						}
					};				
				});
