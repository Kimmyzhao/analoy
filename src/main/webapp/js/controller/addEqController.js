var addEuipmentApp = angular.module('addEuipmentApp', []);

var addEqController = function($scope, $http, $location) {
	// alert($scope.type);
	var type = $scope.type;
	var eqno = $scope.eqno;
	var mobile = $scope.mobile;
	var licenseCode = $scope.licenseCode;
	$scope.changeType = function() {
		// alert($scope.type);
	};

	$scope.add = function() {
		if (type == undefined) {
			$.messager.alert('错误', '类型不能为空');
			return;
		} else {
			if (eqno === '') {
				$.messager.alert('错误', '终端号不能为空');
				return;
			}
			if (type === 2) {
				if (mobile === '') {
					$.messager.alert('错误', '手机号不能为空');
					return;
				} else if (licenseCode === '') {
					$.messager.alert('错误', '激活码不能为空');
					return;
				}
			}

			$http.post("api/equiment/addEqno", {
				type : type,
				eqno : eqno,
				mobile : mobile,
				licenseCode : licenseCode
			}).success().error();
		}
	};
};
addEuipmentApp.controller('agentapp', agentapp);