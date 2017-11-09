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
	<link href="${ASSERT_URL}/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">
	<link href="${ASSERT_URL}/bootstraptable/bootstrap-table.css"  rel="stylesheet" type="text/css">
	<!--javascript-->
	<script src="${JAVASCRIPT_URL}/jquery-3.2.1.js"></script>
	<script src="${ASSERT_URL}/bootstrap/js/bootstrap.js"></script>
	<script src="${ASSERT_URL}/ztree/js/jquery.ztree.all.js"></script>
	<script src="${ASSERT_URL}/bootstraptable/bootstrap-table.js"></script>
	<script src="${ASSERT_URL}/bootstraptable/bootstrap-table-zh-CN.js"></script>
	<script type="text/javascript">
	
	    var zTreeObj;
	    
	    var setting = {
				    view: {
						dblClickExpand: false,
						showLine: false,
						selectedMulti: false,
						showIcon: false
					},
					
					edit: {
						enable: true,
						showRemoveBtn: false,
						showRenameBtn: false
					},	
					
					//回调事件在这个地方注册				
					callback:{
					    onClick:clickme
					},
					
					data: {
						simpleData: {
							enable:true,
							idKey: "id",
							pIdKey: "pid",
							rootPId: ""
						}
					}
	    };
	    var zNodes = ${treeData};
		
		$(document).ready(function(){
		  //加载树形结构数据
		  zTreeObj = $.fn.zTree.init($("#qptree"), setting, zNodes);
		  //节点默认全部展开
		  zTreeObj.expandAll(true);
		});
		
		function clickme(event, treeId, treeNode, clickFlag) {
		      var id = treeNode.id;
		      var dataurl = "menuFromPid.do?pid="+id;
				$.ajax({
					  type: 'get',
					  url: dataurl,
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
		}
	</script>
  </head>
  <body>
     <div>
        <ul id="qptree" class="ztree"></ul>
     </div>
  </body>
</html>
