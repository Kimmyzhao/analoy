var terminalModule = angular.module('terminalModule', []);

var terminalController = function($scope, $http, $location) {

	var rows = 10;
	$scope.init = function() {
		$scope.list();
	};

	$scope.list = function() {
		$http.post("api/equiment/list").success(function(data) {
			if (data.code == 1) {
				$scope.list = data.result.list;
			}
		}).error(function(data) {
		});
	};

	$scope.init();
};

terminalModule.controller('terminalController', terminalController);