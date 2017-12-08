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
	<meta charset="UTF-8"/>
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
	<form id="menuAddForm" class="form-inline" action="dataDictionaryAdd.do" method="post">

	  <div class="form-group">
	    <label for="exampleInputEmail2">父级id</label>
	    <input type="text" name = "pid" class="form-control" placeholder="pid">
	  </div>
	  <div class="form-group">
	    <label for="exampleInputName2">名称</label>
	    <input type="text" name = "name" class="form-control" placeholder="name">
	  </div>
	  <button type="submit" class="btn btn-default">保存</button>
	</form>
  </body>
</html>
