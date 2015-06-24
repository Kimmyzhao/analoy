var addRecordModule = angular.module('addRecordModule', []);

var recordController = function($scope, $http, $location) {

	$scope.addRecord = function() {
		var paytype = $scope.paytype;
		var eqno = $scope.eqno;
		var bankName = $scope.bankName;
		var cardno = $scope.cardno;
		var cardtype = $scope.cardtype;
		var tradetype = $scope.tradetype;
		var money = $scope.money;
		var fee = $scope.fee;
		if (paytype == undefined) {
			$.messager.alert('提示', '请选择支付通道');
			return;
		} else if (eqno == undefined) {
			$.messager.alert('提示', '终端号不能为空');
			return;
		} else if (bankName == undefined) {
			$.messager.alert('提示', '请选择银行');
			return;
		} else if (cardno == undefined) {
			$.messager.alert('提示', '银行卡号不能为空');
			return;
		} else if (cardtype == undefined) {
			$.messager.alert('提示', '请选择银行卡类型');
			return;
		} else if (tradetype == undefined) {
			$.messager.alert('提示', '请选择交易类型');
			return;
		} else if (money == undefined) {
			$.messager.alert('提示', '消费金额不能为空');
			return;
		} else if (fee == undefined) {
			$.messager.alert('提示', '手续费不能为空');
			return;
		}

		if (fee > 1 || fee < 0) {
			$.messager.alert('提示', '手续费只能在0-1之间');
			return;
		}
		$http.post("api/tradeOrder/addTradeOrder", {
			paytype : paytype,
			eqno : eqno,
			bankName : bankName,
			cardno : cardno,
			cardtype : cardtype,
			tradetype : tradetype,
			money : money,
			feeRate : fee
		}).success(function(data) {
			if (data.code == 1) {
				$.messager.alert('提示', '添加成功!!!');
			} else {
				$.messager.alert('提示', data.message);
			}
		}).error(function(data) {
		});
	};
};

addRecordModule.controller('recordController', recordController);