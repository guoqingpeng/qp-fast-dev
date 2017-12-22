<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<title>qp-dev-center</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta charset="utf-8" />
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<!--css -->
		<link href="${CSS_URL}/reset.css" rel="stylesheet" type="text/css">
		<link href="${CSS_URL}/qptablelist.css" rel="stylesheet" type="text/css">
		<link href="${ASSERT_URL}/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
		<link href="${ASSERT_URL}/bootstrap/css/bootstrap-theme.css" rel="stylesheet" type="text/css">
		<link href="${ASSERT_URL}/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">
		<link href="${ASSERT_URL}/bootstraptable/bootstrap-table.css" rel="stylesheet" type="text/css">
		
		<!--javascript-->
		<script src="${JAVASCRIPT_URL}/jquery-3.2.1.js"></script>
		<script src="${ASSERT_URL}/bootstrap/js/bootstrap.js"></script>
		<script src="${ASSERT_URL}/ztree/js/jquery.ztree.all.js"></script>
		<script src="${ASSERT_URL}/bootstraptable/bootstrap-table.js"></script>
		<script src="${ASSERT_URL}/bootstraptable/bootstrap-table-zh-CN.js"></script>
	    <script src="${JAVASCRIPT_URL}/qpfieldlist.js"></script>
	</head>
	<body>
		<!--part2 数据区域 -->
		<div class="qp-data" id="qp-data">
			<table id="smsc" tableId="${tableId}">
			</table>
		</div>
		
		<div>
	         <input id="saveField" class="btn btn-default" type="submit" value="保存">
		</div>
		<div id="cachedDictionary">${cachedDictionary}</div>
	</body>
</html>
