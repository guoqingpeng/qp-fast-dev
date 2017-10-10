<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>菜单设置</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="菜单列表">
	<!--css -->
	<link href="${CSS_URL}/reset.css" rel="stylesheet" type="text/css" >
	<link href="${CSS_URL}/qp-list.css" rel="stylesheet" type="text/css" >
	<link href="${ASSERT_URL}/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="${ASSERT_URL}/bootstrap/css/bootstrap-theme.css" rel="stylesheet" type="text/css">
	<!--javascript-->
	<script src="${JAVASCRIPT_URL}/jquery-3.2.1.js"></script>
	<script src="${ASSERT_URL}/bootstrap/js/bootstrap.js"></script>
	<script src="${JAVASCRIPT_URL}/qp-list.js"></script>
  </head>
  <body>
  <div class="listArea">
  
	  <!--part1 检索区域 -->
	  <div class="qp-search" id ="qp-search">
	       <div class="searchArea">
	       		<div class="search-left contaniner-horizontal-left">
		       		<span class="searchCondition">搜索条件</span>
		       		<span class="qp-down glyphicon glyphicon-chevron-up"></span>
	       		</div>
	       		<div class="search-right contaniner-horizontal">
		       		<span class="qp-search-icon glyphicon glyphicon-search"></span>
	       		</div>				
	       </div>
	       <div class="searchForm"></div>
	  </div>
	  
	  <!--part2 数据区域 -->
	  <div class="qp-data" id ="qp-data">
	  </div>
	  
	  <!--part3 状态区以及分页区 -->
	  <div class="qp-page-state" id ="qp-page-state">
	  </div>
	  
  </div>
  </body>
</html>
