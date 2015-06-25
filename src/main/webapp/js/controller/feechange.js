var feeChangeModule = angular.module('feeChangeModule', []);

var feechangeController = function($scope, $http, $location) {

	$scope.changefee = function() {
		var creditfee = $scope.creditfee;// 信用卡手续费
		var debitfee = $scope.debitfee;// 借记卡手续费
		var credittopflag = $scope.credittopflag;// 信用卡封顶标识
		var debittopflag = $scope.debittopflag;// 借记卡封顶标识
		var agentno = $scope.agentno;
		if (creditfee == undefined) {
			$.messager.alert('提示', '信用卡手续费不能为空');
			return;
		} else if (debitfee == undefined) {
			$.messager.alert('提示', '借记卡手续费不能为空');
			return;
		} else if (credittopflag == undefined) {
			$.messager.alert('提示', '请选择信用卡封顶标识');
			return;
		} else if (debittopflag == undefined) {
			$.messager.alert('提示', '请选择借记卡封顶标识');
			return;
		} else if (agentno == undefined) {
			$.messager.alert('提示', '商户编号不能为空');
			return;
		}

		if (creditfee > 1 || creditfee < 0) {
			$.messager.alert('提示', '信用卡手续费只能在0-1之间');
			return;
		}

		if (debitfee > 1 || debitfee < 0) {
			$.messager.alert('提示', '借记卡手续费只能在0-1之间');
			return;
		}

		var credittopmoney = 0, debittopmoney = 0;
		if (credittopflag == 1) {
			credittopmoney = $scope.credittopmoney;
			if (credittopmoney == undefined) {
				$.messager.alert('提示', '请填写信用卡封顶金额');
				return;
			}
		}

		if (debittopflag == 1) {
			debittopmoney = $scope.debittopmoney;
			if (debittopmoney == undefined) {
				$.messager.alert('提示', '请填写借记卡封顶金额');
				return;
			}
		}

		$http.post("api/qiandai/feechange", {
			creditfee : creditfee,
			debitfee : debitfee,
			credittopflag : credittopflag,
			debittopflag : debittopflag,
			credittopmoney : credittopmoney,
			debittopmoney : debittopmoney,
			agentno : agentno
		}).success(function(data) {
			if (data.code == 1) {
				$.messager.alert('提示', data.message);
			} else {
				$.messager.alert('提示', data.message);
			}
		});

	};
};

feeChangeModule.controller('feechangeController', feechangeController);