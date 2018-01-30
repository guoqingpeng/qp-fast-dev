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
  
	<form id ="menuAddForm" class="form-horizontal" action="pageAdd.do" method="post">
	
	   <!-- hiden 区域开始 -->
	   <input type="hidden"  name="tableId" value="${tableId}">
	   <!-- hiden 区域结束 -->
	  <div class="form-group">
	    <label for="name" class="col-sm-2 control-label">页面名称</label>
	    <div class="col-sm-4">
	       <input type="text" class="form-control" id="name" placeholder="名称">
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label for="type" class="col-sm-2 control-label">页面类型</label>
	    <div class="col-sm-4">
		    <select  class="form-control" id="type">
		        <option value=1>列表</option>
		        <option value=2>添加</option>
		        <option value=3>修改</option>
		        <option value=4>查看</option>
		        <option value=7>弹窗</option>
		        <option value=8>下拉树</option>
		    </select>
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label for="assocation" class="col-sm-2 control-label">是否关联</label>
	    <div class="col-sm-4">
		    <select  class="form-control" id="assocation">
		        <option value=1>否</option>
		        <option value=2>是</option>
		    </select>
	    </div>
	  </div>
	  	  
	  <div class="form-group">
	    <label for="content" class="col-sm-2 control-label">关联菜单</label>
	    <div class="col-sm-4">
	       <input type="text" class="form-control" id="content" placeholder="关联菜单">
	    </div>
	  </div>
	  
	  <div class="form-group">
		  <div class="col-sm-offset-2 col-sm-4">
	         <button type="submit" class="btn btn-default">保存</button>
	      </div>
      </div>
      
	</form>
  </body>
</html>
