<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>qp-dev-center</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta charset="utf-8"/>
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--css -->
	<link href="${CSS_URL}/reset.css" rel="stylesheet" type="text/css" >
	<link href="${CSS_URL}/qplist.css" rel="stylesheet" type="text/css" >
	<link href="${ASSERT_URL}/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="${ASSERT_URL}/bootstrap/css/bootstrap-theme.css" rel="stylesheet" type="text/css">
	<link href="${ASSERT_URL}/bootstraptable/bootstrap-table.css"  rel="stylesheet" type="text/css">
	<!--javascript-->
	<script src="${JAVASCRIPT_URL}/jquery-3.2.1.js"></script>
	<script src="${ASSERT_URL}/bootstrap/js/bootstrap.js"></script>
	<script src="${JAVASCRIPT_URL}/qplist.js"></script>
	<script src="${ASSERT_URL}/bootstraptable/bootstrap-table.js"></script>
	<script src="${ASSERT_URL}/bootstraptable/bootstrap-table-zh-CN.js"></script>
  </head>
  <body>
  <!-- 菜单栏目左侧树-->
  <div id="treeArea" class="treeArea">
       <iframe id = "treeData" src="treeData.do"></iframe>
  </div>
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
			<table data-toggle="table" data-url="menuData.do">
				<thead>
					<tr>	
					    <th data-checkbox="yes">yes</th>
						<th data-title="操作">操作</th>
						<th data-field="id" data-align="center">栏目id</th>
						<th data-field="level">级别</th>
						<th data-field="name">名称</th>
						<th data-field="hasSubTreeobj">是否包含下级</th>
						<th data-field="pid">父id</th>
						<th data-field="subMenuMun">下级个数</th>	
						<th data-field="urlType">url类型</th>	
					    <th data-field="url">url</th>											
					</tr>
				</thead>
			</table>
	  </div>  
	  <!--part3 状态区以及分页区 -->
	  <div class="qp-page-state" id ="qp-page-state">
	  </div>  
  </div>
  </body>
</html>
