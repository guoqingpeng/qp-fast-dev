<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>qp-dev-center1</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--css -->
	<link href="${CSS_URL}/reset.css" rel="stylesheet" type="text/css" >
	<link href="${CSS_URL}/homepage.css" rel="stylesheet" type="text/css" >
	<link href="${ASSERT_URL}/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="${ASSERT_URL}/bootstrap/css/bootstrap-theme.css" rel="stylesheet" type="text/css">
	<!--javascript-->
	<script src="${JAVASCRIPT_URL}/jquery-3.2.1.js"></script>
	<script src="${JAVASCRIPT_URL}/homepage.js"></script>
	<script src="${ASSERT_URL}/bootstrap/js/bootstrap.js"></script>
  </head>
  <body>
  <div id="qpcontainer">
  
      <div class="uppart" id="uppart" >
	        <!--logo区-->
	        <div class="logo" id="logo">
					<div class="molo1"><span class="glyphicon glyphicon-indent-right"></span></div>
					<div class="molo2"><span class="glyphicon glyphicon-indent-left"></span></div>
					<div class="homepage glyphicon glyphicon-home"></div>
			</div>
	  		<!--页签区-->
			<div class="markarea" id="markarea">
				<div class="leftcontrol glyphicon glyphicon-triangle-left"></div>
			    <div class="marks" id="marks"></div>
				<div class="rightcontrol glyphicon glyphicon-triangle-right"></div>
			</div>
			<!--用户信息区-->
			<div  class="contaniner-horizontal" id="userinfo"></div>
	  </div>
	  
	  <div class="downpart" id="downpart">
	  
		  <!--系统的菜单区 开始-->
		  <div class="qpleft" id="qpleft">
		  <!-- 延迟加载菜单
		  1.首先加载顶级菜单，获取是不是有url,有上下级
		  2.点击每个菜单时判断是已经点过了，点过了
		  3.是就跳转，不是就ajax请求，获取子菜单，然后展开,一次循环
		  -->
			 <ul class="mainMenu">
				<li class="subMenu simpleMenu">
					<a class = "menuItem" href="#" id="1" url="menulist.do" onClick="jumptourl(this)">菜单设置</a>
				</li>
				<li class="subMenu simpleMenu">
					<a class = "menuItem" href="#" id="2" url="g1.do" onClick="jumptourl(this)">2</a>
				</li>
				<li class="subMenu simpleMenu">
					<a class = "menuItem" href="#" id="3" url="g3.do" onClick="jumptourl(this)">3</a>
				</li>
				<li class="subMenu simpleMenu">
					<a class = "menuItem" href="#" id="4" url="g2.do" onClick="jumptourl(this)">4</a>
				</li>
				<li class="subMenu simpleMenu">
					<a class = "menuItem" href="#" id="5" url="menulist.do" onClick="jumptourl(this)">5</a>
				</li>
				<li class="subMenu simpleMenu">
					<a class = "menuItem" href="#" id="6" url="menulist.do" onClick="jumptourl(this)">6</a>
				</li>
				<li class="subMenu simpleMenu">
					<a class = "menuItem" href="#" id="7" url="menulist.do" onClick="jumptourl(this)">7</a>
				</li>
				<li class="subMenu simpleMenu">
					<a class = "menuItem" href="#" id="8" url="menulist.do" onClick="jumptourl(this)">8</a>
				</li>
				<li class="subMenu simpleMenu">
					<a class = "menuItem" href="#" id="9" url="menulist.do" onClick="jumptourl(this)">9</a>
				</li>
				<li class="subMenu simpleMenu">
					<a class = "menuItem" href="#" id="10" url="menulist.do" onClick="jumptourl(this)">10</a>
				</li>
				<li class="subMenu simpleMenu">
					<a class = "menuItem" href="#" id="11" url="menulist.do" onClick="jumptourl(this)">11</a>
				</li>
				<li class="subMenu simpleMenu">
					<a class = "menuItem" href="#" id="12" url="menulist.do" onClick="jumptourl(this)">12</a>
				</li>
				<li class="subMenu simpleMenu">
					<a class = "menuItem" href="#" id="13" url="menulist.do" onClick="jumptourl(this)">13</a>
				</li>
				<li class="subMenu simpleMenu">
					<a class = "menuItem" href="#" id="14" url="menulist.do" onClick="jumptourl(this)">14</a>
				</li>
				<li class="subMenu simpleMenu">
					<a class = "menuItem" href="#" id="15" url="menulist.do" onClick="jumptourl(this)">15</a>
				</li>
				<li class="subMenu simpleMenu">
					<a class = "menuItem" href="#" id="16" url="menulist.do" onClick="jumptourl(this)">16</a>
				</li>
				<li class="subMenu simpleMenu">
					<a class = "menuItem" href="#" id="17" url="menulist.do" onClick="jumptourl(this)">17</a>
				</li>
				<li class="subMenu simpleMenu">
					<a class = "menuItem" href="#" id="18" url="menulist.do" onClick="jumptourl(this)">18</a>
				</li>
				<li class="subMenu simpleMenu">
					<a class = "menuItem" href="#" id="19" url="menulist.do" onClick="jumptourl(this)">19</a>
				</li>
				<li class="subMenu simpleMenu">
					<a class = "menuItem" href="#" id="20" url="menulist.do" onClick="jumptourl(this)">20</a>
				</li>
			 </ul>
		  </div>
		  <!--系统的菜单区结束-->
		  
		  <!--系统的数据展示区 开始-->
		  <div class="qpright" id="qpright">
			<!--数据列表展示区-->
			<div class="qp-right-main" id="qp-right-main">
			 <iframe id="dataframe" src="g1.do"></iframe>
			</div>
		  </div>
		   <!--系统的数据展示区 结束-->
	  </div>
  </div>
  <div id="dialog"></div>
  <div id="msg">${CSS_URL}</div>
  </body>
</html>
