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
	    <script src="${JAVASCRIPT_URL}/qptablelist.js"></script>
	</head>
	<body>
		<!-- 右侧列表区域 -->
		<div class="listArea">
		
			<!--part1 检索区域 -->
			<div class="qp-search" id="qp-search">
			
				<div class="search-left contaniner-horizontal-left">
					<span class="searchCondition">搜索条件</span>
					<span class="qp-down glyphicon glyphicon-chevron-up"></span>
				</div>
				
				<div class="search-right">
				        <button type="button" class="btn btn-default" data-toggle="modal" data-target=".bs-example-modal-lg">
				             <span id="menuAdd" title="添加" class="qp-button glyphicon glyphicon-plus"></span>
				        </button>
				</div>
				<div class="searchForm">
				<form>
				    <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
					<input type="button" value="搜索"/>
				</form>
				</div>
				
			</div>
			
			<!--part2 数据区域 -->
			<div class="qp-data" id="qp-data">
				<table id="smsc">
				</table>
			</div>
			
			<!--part3 状态区以及分页区 -->
			<div class="qp-page-state" id="qp-page-state">
			</div>
			<!--页面中的弹窗页面-->
			<!-- Large modal -->
			<div id = "qpDialog" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
			  <div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					 <iframe id ="qpPage" src="toTabelAddPage.do"></iframe>
				</div>
			  </div>
			</div>	
	</body>
</html>
