'user strict';

var posQueryModule = angular.module("posQueryModule", []);
var posQueryController = function($scope, $http){
	$scope.init = function(){
		// alert("aaaa");
		$scope.req = {};
	}
	
	$scope.submit = function(){
		if($scope.req.eqno == null || $scope.req.eqno == "" || $scope.req.eqno == undefined){
			alert("终端号必须填写");
			return false;
		} else if($scope.req.now == null || $scope.req.now == "" || $scope.req.now == undefined){
			alert("查询时间不能为空");
			return false;
		}
		$http.post("api/qiandai/getAgentInfoByEqno", {
			eqno : $scope.req.eqno,
			now : $scope.req.now,
			remark : $scope.req.remark
		}).success(function(data){
			if(data.code == 1) {
				$scope.result = data.result;
				alert(data.result.msg);
			}
		}).error(function(data){
			
		});
	};
	$scope.init();
	
};
posQueryController.$inject = ['$scope','$http'];
posQueryModule.controller=("posQueryController", posQueryController);