<%@ page  contentType="text/html;charset=UTF-8"  language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<script type="text/javascript">
	$(document).ready(function(){
	        var menu;
			$.ajax({
				  type: 'get',
				  url: 'menuData.do',
				  dataType: 'json',
				  success:function(data){
						$('#smsc').bootstrapTable({
						    columns: [{
						        field: 'id',
						        title: '数据id'
						    }, {
						        field: 'name',
						        title: '名称'
						    }],
						    data:data
						});
				  }
				});
	});
	</script>
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
			<table id ="smsc">
			</table>
	  </div>  
	  <!--part3 状态区以及分页区 -->
	  <div class="qp-page-state" id ="qp-page-state">
	  </div> 
  </body>
</html>
