<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加消费记录</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css"
	rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>
<body>
	<div
		style="margin: 0px; padding: 0px; position: relative; width: 100%; height: 40px; text-align: center; vertical-align; background: #216BDA; color: red;">添加消费记录</div>
	<form class="form-horizontal" role="form">
		<div class="form-group">
			<label for="eqno" class="col-sm-2 control-label">终端号S/N</label>
			<div class="col-sm-5">
				<input type="text" class="form-control" id="eqno"
					placeholder="请输入终端号">
			</div>
		</div>
		<div class="form-group">
			<label for="money" class="col-sm-2 control-label">刷卡金额</label>
			<div class="col-sm-5">
				<input type="text" class="form-control" id="money"
					placeholder="刷卡金额">
			</div>
		</div>
		<div class="form-group">
			<label for="money" class="col-sm-2 control-label">请选择银行</label>
			<div class="col-sm-5">
				
				<div class="dropdown">
					<button type="button" class="btn dropdown-toggle form-control"
						id="dropdownMenu1" data-toggle="dropdown">
						请选择银行 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu"
						aria-labelledby="dropdownMenu1">
						<li role="presentation"><a role="menuitem" tabindex="-1"
							href="#">中国工商银行</a></li>
						<li role="presentation"><a role="menuitem" tabindex="-1"
							href="#">交通银行</a></li>
						<li role="presentation"><a role="menuitem" tabindex="-1"
							href="#"> 广发银行 </a></li>
						<li role="presentation"><a role="menuitem" tabindex="-1"
							href="#">中国农业银行</a></li>
							<li role="presentation"><a role="menuitem" tabindex="-1"
							href="#">招商银行</a></li><li role="presentation"><a role="menuitem" tabindex="-1"
							href="#">平安银行</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">提交</button>
			</div>
		</div>
	</form>

</body>
</html>