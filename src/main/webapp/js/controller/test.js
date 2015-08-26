var test = angular.module('test', []);

var testController = function($scope, $http, $location) {
	$scope.add = function() {
		var fee = $scope.fee;
		var eqno = $scope.eqno;
		var topCharge = $scope.topCharge;
		var status = $scope.status;
		if (eqno == '' || eqno == undefined || eqno == null) {
			$.messager.alert('错误', '终端号不能为空');
			return;
		}

		if (fee == '' || fee == null || fee == undefined) {
			$.messager.alert('错误', '终端费率不能为空');
			return;
		}
		if (status == '' || status == null || status == undefined) {
			$.messager.alert('错误', '终端开通状态不能为空');
			return;
		}
		$http.post("api/test/addFee", {
			eqno : eqno,
			fee : fee,
			topCharge : topCharge,
			status : status
		}).success(function(data) {
			$.messager.alert('提示', data);

		}).error(function(data) {
		});
	}
}

test.controller('testController', testController);