<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加消费记录</title>
</head>
<body>
	<form action="api/tradeOrder/addTradeOrder" method="post">
		<label>支付通道</label><select name="paytype">
			<option>--请选择支付通道--</option>
			<option value="1">钱袋宝</option>
			<option value="2">中汇</option>
			<option value="3">韩鑫</option>
		</select><br /> 
		<label>终端号S/N</label><input type="text" id="eqno" name="eqno" style="height: 20px; width: 150px"><br /> 
		<label>请选择银行</label><select
			style="height: 20px; width: 150px" name="bankName">
			<option>--请选择银行--</option>
			<option value="1">中国工商银行</option>
			<option value="2">交通银行</option>
			<option value="3">广发银行</option>
			<option value="4">中国农业银行</option>
			<option value="5">招商银行</option>
			<option value="6">平安银行</option>
			<option value="7">中国邮政储蓄银行</option>
			<option value="8">中国建设银行</option>
		</select><br /> 
		<label>银行卡号</label><input type="text" id="cardno" name="cardno"><br />
		<label>银行卡类型</label><input type="radio" name="cardtype" value="1">借记卡
							   <input type="radio" name="cardtype" value="2">贷记卡<br/>
		<label>消费金额</label><input type="text" id="money" name="money"><br /> 
		<input type="submit" value="提交">
	</form>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<thead>
			<tr>
				<th>支付通道</th>
				<th>终端号(S/N)</th>
				<th>结算银行名称</th>
				<th>结算银行卡号</th>
				<th>结算银行卡类型</th>
				<th>消费金额</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table>
</body>
</html>