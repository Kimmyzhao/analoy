<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加消费记录</title>
</head>
<body>
	<form action="">
		<label>支付通道</label><select>
			<option>--请选择支付通道--</option>
			<option value="0">钱袋宝</option>
			<option value="1">中汇</option>
			<option value="2">韩鑫</option>
		</select><br/>
		<label>终端号S/N</label><input type="text" id="eqno" name="eqno"
			style="height: 20px; width: 150px"><br /> <label>请选择银行</label><select
			style="height: 20px; width: 150px">
			<option>--请选择银行--</option>
			<option value="0">中国工商银行</option>
			<option value="1">交通银行</option>
			<option value="2">广发银行</option>
			<option value="3">中国农业银行</option>
			<option value="4">招商银行</option>
			<option value="5">平安银行</option>
			<option value="6">中国邮政储蓄银行</option>
			<option value="7">中国建设银行</option>
		</select><br /> <label>银行卡号</label><input type="text" id="cardno" name="cardno"><br />
		<label>消费金额</label><input type="text" id="money" name="money"><br />
		<input type="submit" value="提交">
	</form>
</body>
</html>