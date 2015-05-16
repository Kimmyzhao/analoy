<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>添加终端</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="style/style.css" rel="stylesheet" type="text/css" />
<link href="style/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>

<style type="text/css">
table tr td {
	border: solid 1px #CCC;
}

table tr {
	line-height: 30px;
}

td {
	text-align: center;
}

td input {
	height: 20px;
}

.rowline {
	background: #FFF;
}
</style>
</head>
<body>
	<div
		style="margin: 0px; padding: 0px; position: relative; width: 100%; height: 50%; text-align: center; background: #216BDA; color: red;">添加终端</div>
	<div style="width: 100%; height: 50%;">
		<form action="api/equiment/addEqno" method="post">
			<table style="width: 100%;">
				<tr class="rowline">
					<td style="text-align: center;"><label>请选择类型:</label></td>
					<td><select style="height: 20px; width: 50%" id="type"
						name="type">
							<option value="">---请选择---</option>
							<option value="1">钱袋宝</option>
							<option value="2">中汇</option>
							<option value="3">韩鑫</option>
					</select></td>
				</tr>
				<tr class="rowline">
					<td><label>请输入终端号:</label></td>
					<td><input style="height: 20px; width: 50%" id="eqno"
						type="text" name="eqno" placeholder="请输入终端号(S/N):"></td>
				</tr>
				<tr class="rowline">
					<td><label>请输入手机号:</label></td>
					<td><input style="height: 20px; width: 50%" id="mobile"
						type="text" name="mobile" placeholder="请输入手机号:"></td>
				</tr>
				<tr class="rowline">
					<td><label>请输入激活码:</label></td>
					<td><input style="height: 20px; width: 50%" id="licenseCode"
						type="text" name="licenseCode" placeholder="请输入激活码:"></td>
				</tr>
				<tr class="rowline">
					<td colspan="2" style="text-align: center; width: 50%;"><input
						style="width: 20%; height: 30px;" type="submit" value="添加"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
