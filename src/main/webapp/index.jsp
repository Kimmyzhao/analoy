<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html ng-app>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="style/style.css" rel="stylesheet" type="text/css" />
<link href="style/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/libs/angular.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/controllers/posQueryController.js"></script>
<script type="text/javascript">
	$(function() {
		$(".user_date").datepicker();
	});
	jQuery(function($) {
		$.datepicker.regional['zh-CN'] = {
			closeText : '关闭',
			prevText : '<上月', 
			nextText: '下月>',
			currentText : '今天',
			monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
			monthNamesShort : [ '一', '二', '三', '四', '五', '六', '七', '八', '九', '十', '十一', '十二' ],
			dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
			dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
			dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
			weekHeader : '周',
			dateFormat : 'yy-mm-dd',
			firstDay : 1,
			isRTL : false,
			showMonthAfterYear : true,
			yearSuffix : '年'
		};
		$.datepicker.setDefaults($.datepicker.regional['zh-CN']);
	});
</script>
</head>
<body>
	<div ng-controller="posQueryController" class="content clear">
		<h3>根据POS终端号查询POS开通状态</h3>
		<div>
			<label><font color="red">*</font>终端号(S/N):</label><input id="eqno"
				type="text" ng-model="req.eqno" name="eqno" />
			<div class="seenBox clear">
				<ul>
					<li>
						<div class="user_date">
							<label><font color="red">*</font>查询时间:</label> <input id="now"
								type="text" ng-model="req.now" name="now" readonly >
						</div>
					</li>
				</ul>
			</div>
			<label>备注:</label><input id="remark" type="text" ng-model="req.remark" name="remark"><br />
			<button type="submit" value="查询">查询</button>
		</div>
	</div>
</body>
</html>
