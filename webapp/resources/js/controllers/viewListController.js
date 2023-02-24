angular.module("app", []).controller(
		"viewListController",
		function($scope, $http) {

			$scope.userList = null;

			var table;

			$scope.getData = function() {
				table = $("table#viewtable").DataTable(
						{
							"processing" : true,
							"serverSide" : true,
							"iDisplayStart" : 0,
							"iDisplayLength" : 20,
							"bPaginate" : true,
							"aLengthMenu" : [ [ 10, 20, 30, -1 ],
									[ 10, 20, 30, "All" ] ],

							"ajax" : {
								"url" : "/SpringJDBCDemo/getViewList",

								"type" : "GET"
							},


							"columns" : [ {
								"data" : "srNo"
							}, {
								"data" : "unitName"
							}, {
								"data" : "name"
							}, {
								"data" : "email"
							}, {
								"data" : "telephone"
							}, {
								"data" : "dateOfBirth"
							}, {
								"data" : "userRole"
							}, {
								"data" : "address"
							} ]
						});
			};

			$scope.getData();

		});