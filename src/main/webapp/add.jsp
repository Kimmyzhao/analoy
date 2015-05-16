<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Basic Layout - jQuery EasyUI Demo</title>
<link rel="stylesheet" type="text/css" href="style/easyui.css">

<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body style="width: 100%">
	<div class="easyui-layout" style="width: 100%; height: 350px;">
		<div data-options="region:'north'" style="height: 50px"></div>
		<div data-options="region:'south',split:true" style="height: 50px;"></div>
		<div data-options="region:'east',split:true" title="East"
			style="width: 100px;"></div>
		<div data-options="region:'west',split:true" title="West"
			style="width: 100px;"></div>
		<div
			data-options="region:'center',title:'Main Title',iconCls:'icon-ok'">
			<table class="easyui-datagrid"
				data-options="url:'datagrid_data1.json',method:'get',border:false,singleSelect:true,fit:true,fitColumns:true">
				<thead>
					<tr>
						<th data-options="field:'itemid'" width="80">Item ID</th>
						<th data-options="field:'productid'" width="100">Product ID</th>
						<th data-options="field:'listprice',align:'right'" width="80">List
							Price</th>
						<th data-options="field:'unitcost',align:'right'" width="80">Unit
							Cost</th>
						<th data-options="field:'attr1'" width="150">Attribute</th>
						<th data-options="field:'status',align:'center'" width="60">Status</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

</body>
</html>