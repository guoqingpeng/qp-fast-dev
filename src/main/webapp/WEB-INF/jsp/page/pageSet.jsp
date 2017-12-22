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
	<link href="${ASSERT_URL}/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="${ASSERT_URL}/bootstrap/css/bootstrap-theme.css" rel="stylesheet" type="text/css">
	<!--javascript-->
	<script src="${JAVASCRIPT_URL}/jquery-3.2.1.js"></script>
	<script src="${ASSERT_URL}/bootstrap/js/bootstrap.js"></script>
  </head>
  <body>
	<form class="form-inline">
	
	  <div class="form-group">
	    <label for="exampleInputName2">页面名称</label>
	    <input type="text" class="form-control" id="exampleInputName2" placeholder="名称">
	  </div>	
	  <div class="form-group">
	    <label for="exampleInputName2">页面类型</label>
	    <select  class="form-control"/>
	        <option value=1>列表</option>
	        <option value=2>添加</option>
	        <option value=3>修改</option>
	        <option value=4>查看</option>
	        <!-- <option value=5>批量添加</option>
	        <option value=6>批量修改</option>
	         --><option value=7>弹窗</option>
	        <option value=8>下拉树</option>
	    </select>
	  </div>
	  
	  <div class="form-group">
	    <label for="exampleInputName2">是否关联</label>
	    <select  class="form-control"/>
	        <option value=1>否</option>
	        <option value=2>是</option>
	    </select>
	  </div>
	  	  
	  <div class="form-group">
	    <label for="exampleInputName2">关联菜单</label>
	    <input type="text" class="form-control" id="exampleInputName2" placeholder="关联菜单">
	  </div>
	</form>
  </body>
</html>
