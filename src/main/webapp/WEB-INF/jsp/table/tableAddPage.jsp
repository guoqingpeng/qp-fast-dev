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
	<script src="${ASSERT_URL}/jqueryForm/jquery.form.js"></script>
	<script src="${JAVASCRIPT_URL}/qpAdd.js"></script>
  </head>
  <body>
	<form id="tableAddForm" class="form-inline" action="tableAdd.do" method="post">
	       	  	
	  <div class="form-group">
	    <label for="exampleInputName2">对象中文名</label>
	    <input type="text" name = "cnName" class="form-control" placeholder="对象中文名">
	  </div>
	  
	  <div class="form-group">
	    <label for="exampleInputEmail2">对象英文名</label>
	    <input type="text" name = "enName" class="form-control" placeholder="对象英文名">
	  </div>
	  
	  <div class="form-group">
	    <label for="exampleInputEmail2">对象类型</label>
	    <input type="text" name = "type" class="form-control" placeholder="对象类型">
	  </div>

	  <div class="form-group">
	    <label for="exampleInputEmail2">是否支持父子级</label>
	    <input type="text" name = "isParent" class="form-control" placeholder="是否支持父子级">
	  </div>	  
	  
	  <div class="form-group">
	    <label for="exampleInputEmail2">描述</label>
	    <input type="text" name = "description" class="form-control" placeholder="描述">
	  </div>	  
	  
	  <button type="submit" class="btn btn-default">保存</button>
	</form>
  </body>
</html>
