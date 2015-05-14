<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加消费记录</title>
<style>
.content {
	margin-top: 30px;
}

.content tbody tr td {
	text-align: center;
}

table tr td {
	border: solid 1px #CCC;
}

table tr {
	line-height: 30px;
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
	<form action="api/tradeOrder/addTradeOrder" method="post">
		<table style="width: 100%;">
			<tr class="rowline">
				<td><label>支付通道</label></td>
				<td><select name="paytype" style="width: 50%;">
						<option>--请选择支付通道--</option>
						<option value="1">钱袋宝</option>
						<option value="2">中汇</option>
						<option value="3">韩鑫</option>
				</select></td>

				<td><label>终端号S/N</label></td>
				<td><input type="text" id="eqno" name="eqno"
					style="height: 20px; width: 50%"><br /></td>
			</tr>
			<tr class="rowline">
				<td><label>请选择银行</label></td>
				<td><select style="height: 20px; width: 50%" name="bankName">
						<option>--请选择银行--</option>
						<option value="1">中国工商银行</option>
						<option value="2">交通银行</option>
						<option value="3">广发银行</option>
						<option value="4">中国农业银行</option>
						<option value="5">招商银行</option>
						<option value="6">平安银行</option>
						<option value="7">中国邮政储蓄银行</option>
						<option value="8">中国建设银行</option>
				</select></td>
				<td><label>银行卡号</label></td>
				<td><input type="text" id="cardno" name="cardno"
					style="width: 50%;"></td>
			</tr>
			<tr class="rowline">
				<td><label>银行卡类型</label></td>
				<td><input type="radio" name="cardtype" value="1">借记卡 <input
					type="radio" name="cardtype" value="2">贷记卡</td>
				<td><label>交易类型</label></td>
				<td><input type="radio" name="tradetype" value="1">消费 <input
					type="radio" name="tradetype" value="2">充值</td>
			</tr>
			<tr class="rowline">
				<td><label>消费金额</label></td>
				<td colspan="3"><input type="text" id="money" name="money"
					style="width: 221px"></td>
			</tr>
			<tr class="rowline">
				<td colspan="4" style="text-align: center; width: 50%;">
					<button type="submit" style="width: 20%; height: 30px;">提&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交</button>
				</td>
			</tr>
		</table>

	</form>

	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		class="content">
		<thead>
			<tr style="background: #CCC;">
				<th>支付通道</th>
				<th>终端号(S/N)</th>
				<th>结算银行名称</th>
				<th>结算银行卡号</th>
				<th>结算银行卡类型</th>
				<th>交易类型</th>
				<th>消费金额</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tradeorder}" var="one">
			<tr class="rowline">
				<td>
					<c:if test="${one.payType == 1}" >钱袋宝</c:if>
					<c:if test="${one.payType == 2}" >中汇</c:if>
					<c:if test="${one.payType == 3}" >韩鑫</c:if>
				</td>
				<td>${one.eqno }</td>
				<td>${one.settleBankName }</td>
				<td>${one.settleCardNo }</td>
				<td>
					<c:if test="${one.cardType == 1 }">借记卡</c:if>
					<c:if test="${one.cardType == 2 }">贷记卡</c:if>
				</td>
				<td>
					<c:if test="${one.tradetype == 1 }">消费</c:if>
					<c:if test="${one.tradetype == 2 }">充值</c:if>
				</td>
				<td>￥${one.money}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>