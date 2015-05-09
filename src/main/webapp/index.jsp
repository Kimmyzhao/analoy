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

</head>
<body>
	<div
		style="margin: 0px; padding: 0px; position: relative; width: 100%; height: 30%; text-align: center; background: #216BDA; color: red;">添加终端</div>
	<div style="width: 100%; height: 70%;">
		<form action="api/qiandai/addEqno" method="post">
			<input id="eqno" type="text" name="eqno" placeholder="请输入终端号(S/N):">
			<input type="submit" value="添加">
		</form>
	</div>
</body>
</html>
