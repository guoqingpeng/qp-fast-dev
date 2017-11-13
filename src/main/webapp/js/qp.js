// JavaScript Document
//每一个菜单的id都是唯一的
//使用一个字符串来维护所有已经打开的标签的url,每个包含参数url都是唯一的
var openedurls = "";
var jioncode = "guoqingpeng";
var homepageurl = "g1.do";
var marknum = 10;

/**
功能说明------
系统首页初始化
菜单初始化
系统首页事件绑定执行
*/
$(document).ready(function(){

  //加载菜单
  initMenu();
  //初始化所有事件
  initBindSystemEvents();
  
});

/**
功能说明------
初始化化自定义的菜单
*/
function initMenu(){
	var menuArea = $('#qpleft');
	var mainMenu = $("<ul class='mainMenu'></ul>");
	var menuContent = $('#menuData').html();
	var menuJson = $.parseJSON(menuContent);
	
	//固定栏目菜单添加-----暂时未做
	
	var level = 1;
	//递归生成所有菜单
	for(var menu in menuJson){
	    var menuIetm = menuJson[menu];
	    mainMenu = makeSubMenu(mainMenu,menuIetm,level);
	}
	//加载菜单
	menuArea.append(mainMenu);
	
	//菜单加载完毕之后,菜单层级css样式刷新
	reFreshMenuCss();
}

/**
功能说明------
针对一个菜单，递归生成所有的子菜单
*/
function makeSubMenu(mainMenu,menu,level){
	   var li = $("<li class='subMenu'></li>");
	   var menuType = menu.urlType;//1.代表栏目不能跳转,0代表普通菜单可以跳转
	   var pid = menu.pid;
	   var subMenuNum = menu.subMenuNum;
	    //栏目菜单
	    if(subMenuNum == 0){      
	            //没有子元素时
				 li.addClass("menuItem")
				   .attr("id",menu.id)
				   .attr("pid",menu.pid)
				   .attr("url",menu.url)
				   .attr("level",level)
				   .html("<div class='aTop'><a class='menuName'>"+menu.name+"</a></div>"); 
			      mainMenu.append(li);   
	     }else{
	            //有子元素时，递归调用
	            li.addClass("hasSubMenu");
				li.addClass("menuItem")
				   .attr("id",menu.id)
				   .attr("pid",menu.pid)
				   .attr("url",menu.url)
				   .attr("level",level);
				   
				var div = $("<div class='aTop'><a class='menuName'>"+menu.name+"</a></div>");
				var span = $("<span class='menuNum'></span>");
				span.append("+");
				div.append(span);
				li.append(div);
				var subMainMenu = $("<ul class='subMainMenu'></ul>");
				var subMenus = menu.subTreeobjList;
				level++;
				for(var menu in subMenus){
				    var menuIetm = subMenus[menu];
				    subMainMenu = makeSubMenu(subMainMenu,menuIetm,level);
				}
				li.append(subMainMenu);
				mainMenu.append(li);			
	     }
	   return mainMenu;
}

/**
功能说明------
菜单层级css样式刷新
*/
function reFreshMenuCss(){
	var marginToDiv = $(".menuName").parent().width() * 0.3;
	$(".menuName").each(function(){
	    var level = $(this).parent().parent().attr("level");
		var toLeft = level * 8 + marginToDiv + "px";
		$(this).css("margin-left",toLeft);
	});
}

/**
功能说明------
动态设置页签的宽度
*/
function setMarkitWidth(){
	var widthcanuse = $("#marks").width();//标签可用长
	var perwidth =  widthcanuse/marknum - 10 + "px";
	$(".markit").css("width",perwidth);
}


/*
功能说明------
点击菜单时,将菜单上的url设置到数据展示去的iframe上
*/
function  jumptourl(ele){
    var a  = $(ele).find("a");//这里取到的实际是一个a标签，因为设计是菜单的是<div><a>名称</a></div>
	var markName = $(a).html();//取a标签的值才是菜单的名称
    var url = $(ele).attr("url");
	var id = $(ele).attr("id");
	var pid = $(ele).attr("pid");
	if(url == "" || url == null){
	   return;
	}
	//页签如果没有打开过
	if(isMenuOpen(id) == -1){
		//判断是否够长度
		if(checkAvriableMarkWidth() != 1){
			$(".ishow").first().removeClass("ishow").addClass("ihide").hide();	
		}
		creatMark(id,url,markName);
		showMark();
	}else{
		shuffleMarks(id);
		clickMenuAndMark(id);
	}
}

/**
功能说明------
检测页签所占宽度是否达到上限
*/
function checkAvriableMarkWidth(){
	var widthcanuse = $("#marks").width();//标签可用长度
	var markitnum = $(".ishow").length +1//计算如果再打开一个标签是否会超出总可用长度
	var markitwidth = $(".markit").width();//每一个页签的长度
	var markittotalwidth = markitnum * markitwidth;//预计下一个的总长度
	var totalmarginleft = 10 * markitnum;//总偏移量
	var totalusewidth = markittotalwidth + totalmarginleft;//实际使用总长度
	if(totalusewidth > widthcanuse){
		return -1;
	}else{
		return 1;
	}
}

/**
功能说明------
创建一个标签
*/
function creatMark(id,url,markName){
	//设置其它页签没选中
	$(".markit").removeClass("markChoosen").addClass("markNotChoosen");
	var mark =$("<div class = 'markit markChoosen ishow' openid="+id+" url='"+url+"'>"+
					"<span class= 'markname' >"+markName+"</span>"+
					"<div class='closemark'><span class='glyphicon glyphicon-remove'></span></div>"+
			   "</div>");
	$("#marks").append(mark);
	addUrltoOpenedurls(id);
	$("#dataframe").attr("src",url);
	setMarkitWidth();
	setMarkLoctionCenter(mark);
	var alertsg = $("<div class='alert alert-warning' role='alert'>打开页签成功"+markName+"</div>");
	$("#msg").append(alertsg);
	$(alertsg).show(500);
}

/**
功能说明------
根据页面大小动态设置页签区域居中
*/
function setMarkLoctionCenter(mark){
	 var h1 = $("#marks").height();
	 var h2 = $(".markit").height();
	 var centerposition = (h1-h2)/2+"px";		 
	 $(".markit").css("margin-top",centerposition);
}

/**
功能说明------
显示翻页
*/
function showMark(){
	 var h1 = $("#marks").height();
	 var h2 = $(".leftcontrol").height();
	 var centerposition=(h1-h2)/2 +"px";
	 var leftflafg = $(".ishow").first().prev();
	 var rightflafg = $(".ishow").last().next();
	 if(leftflafg.length == 1){
		 $(".rightcontrol").show();
	 }else{
		 $(".rightcontrol").hide();
	 }
	 if(rightflafg.length == 1){
		 $(".leftcontrol").show();
	 }else{
		 $(".leftcontrol").hide();
	 }
	 $(".leftcontrol").css("top",centerposition);
	 $(".rightcontrol").css("top",centerposition);
}

/**
功能说明------
关闭翻页
*/
function hideMark(){
	 $(".leftcontrol").hide(2000);
	 $(".rightcontrol").hide(2000);
}

/**
功能说明------
左移一格
*/
function moveLeft(){
	 //找到第一个ishow元素，并将其隐藏
	 $(".ishow").first().removeClass("ishow").addClass("ihide").hide();
	 //找到最后一个ishow元素之后的第一个隐藏元素
	 $(".ishow").last().next().removeClass("ihide").addClass("ishow").show();
	 showMark();
}

/**
功能说明------
右移一格
*/
function moveRight(){
	 //找到最后一个ishow元素，并将其隐藏
	 $(".ishow").last().removeClass("ishow").addClass("ihide").hide();
	 //找到最后一个ishow元素之后的第一个隐藏元素
	 $(".ishow").first().prev().removeClass("ihide").addClass("ishow").show(50);
	 showMark();
}

/**
功能说明------
点击菜单时切换选中的标签
跳转到选中的页签上
*/
function clickMenuAndMark(id){
	$(".markit").removeClass("markChoosen").addClass("markNotChoosen");
	$(".markit[openid='"+id+"']").addClass("markChoosen").removeClass("markNotChoosen");
	//找到选中的url
	var url = $(".markit[openid='"+id+"']").attr("url");
	$("#dataframe").attr("src",url);
}

/*
*功能说明
检查某个菜单是够已经点过
点过了，则将当前的iframe的设置为菜单上的url
当前的也签选中
*/
function isMenuOpen(id){
	var ids = openedurls.split(jioncode);
	for(var i=0;i<ids.length;i++){
	    if(id == ids[i]){
			return 1;
		}	
	}
	return -1;
}
	
/**
功能说明
将刚刚打开的地址加入到全局维护所有以打开标签之中
*/
function addUrltoOpenedurls(id){
	if(isMenuOpen(id) == -1){
		openedurls = openedurls.concat(jioncode+id);
	}
}

/**
功能说明
将刚关闭开的地址从全局维护的所有打开的地址之中去除
*/
function removeUrlfromOpenedurls(id){
	if(isMenuOpen(id) != -1){
		openedurls = openedurls.replace(jioncode+id,"");
	}	
}

/**
功能说明
切换到选中的标签页上面
*/
function markChoosen(ele){
	//所有元素未选中
	$(".markit").removeClass("markChoosen").addClass("markNotChoosen");
	//当前元素选中
	$(ele).parent().addClass("markChoosen").removeClass("markNotChoosen");
	//设置当前框架的页面
	$("#dataframe").attr("src",$(ele).parent().attr("url"));
}

/**
功能说明------
删除标签页
*/
function destoryMark(ele){
   //关闭标签前获取前面的一个元素
   openurlWhenCloseMark(ele);
   var id = $(ele).parent().attr("openid");
   $(ele).parent().remove();
   removeUrlfromOpenedurls(id);
   var leftflafg = $(".ishow").first().prev();
   var rightflafg = $(".ishow").last().next(); 
   if(leftflafg.length == 1){
	 leftflafg.removeClass("ihide").addClass("ishow").show();
   }else if(rightflafg.length == 1){
	  rightflafg.removeClass("ihide").addClass("ishow").show();
   }
   showMark();
}

/**
功能说明------
如果关闭页签时，默认打开前一个，
没有前一个没有时打开后一个
所有没有时打开默认页
*/
function openurlWhenCloseMark(ele){
	//如果关闭的这个页签是正打开着
	if($(ele).parent().hasClass("markChoosen")){
		var preitem = $(ele).parent().prev().children().first();
		var nextitem = $(ele).parent().next().children().first();
		if(preitem.length == 1){
			markChoosen(preitem);
		}else if(nextitem.length == 1){
			markChoosen(nextitem);
		}else{
			$("#dataframe").attr("src",homepageurl);
		}
	}
}

/**
功能说明------
当标签隐藏时，点击对应的菜单栏，标签页排序重新洗牌
*/
function shuffleMarks(id){
	var startIndex = 0;
	var endIndex = 0;
	var mark = $(".markit[openid='"+id+"']");
	//点击的菜单对应的是隐藏住的页签时;
	if(mark.hasClass("ihide")){
		var marks = $(".markit").length;
		var choosendMarkIndex = $(".markit").index(mark);
		if( choosendMarkIndex <= marks/2){
			 startIndex = choosendMarkIndex;
			 endIndex = choosendMarkIndex + (marknum - 1);
		}else{
			 startIndex = choosendMarkIndex -(marknum - 1);
			 endIndex = choosendMarkIndex;
		}
		$(".markit:gt("+startIndex+"):lt("+endIndex+")").addClass("ishow").removeClass("ihide").show();
		$(".markit:eq("+startIndex+")").addClass("ishow").removeClass("ihide").show();
		$(".markit:eq("+endIndex+")").addClass("ishow").removeClass("ihide").show();
		$(".markit:gt("+endIndex+")").addClass("ihide").removeClass("ishow").hide();
		$(".markit:lt("+startIndex+")").addClass("ihide").removeClass("ishow").hide();
	}
	showMark();
}

/**
功能说明------
菜单区域宽度变化
*/
function menuSizeChange(){
	if(!$('#logo').hasClass("menuZoomout")){		
		 $(".qpright").css({
			width: "100%",
		  },1000); 
		 $(".qpleft").css({
			width: "0%",
		  }, 300);		 
		 $('#logo').addClass("menuZoomout");
		 $('.molo1').hide();
		 $('.molo2').show();
    }else{
		 $(".qpright").css({
			width: "85%",
		  }); 
		 $(".qpleft").css({
			width: "15%",
		  }, 300);		 
		 $('#logo').removeClass("menuZoomout");
		 $('.molo2').hide();
		 $('.molo1').show();	
	}
}

/**
功能说明------
所有事件的注册
*/
function initBindSystemEvents(){
	
  //注册关闭按钮页签按钮事件
  $(document).on('click','.closemark',function(){
	   destoryMark(this);							 
  });
  
  //注册页签选中事件
  $(document).on('click','.markname',function(){
	   markChoosen(this);						 
  });
  
   //页签左翻页
  $(document).on('click','.leftcontrol',function(){
	   moveLeft();					 
  });
  
   //页签右翻页
  $(document).on('click','.rightcontrol',function(){
	   moveRight();			 
  });
  
  //菜单区宽窄变化
  $(document).on('click','.molo1,.molo2',function(){
	   menuSizeChange();  
  });
  
  //绑定点击展开的功能
  $(document).on('click','.hasSubMenu',function(event){
	   var subMainMenu = $(this).find("ul").first();
	   if(subMainMenu.hasClass("menuFold")){
		   subMainMenu.hide();
		   subMainMenu.removeClass("menuFold");
		   $(this).children().find(".menuNum").first().html("+");
	   }else{
		   subMainMenu.addClass("menuFold");
		   subMainMenu.show();
		   $(this).children().find(".menuNum").first().html("-");
	   }
	   //阻止父级再次调用 很重要的
	  event.stopPropagation();
	  //刷新点击的展开收起状态
	  
  });
  
  //url跳转这个事件一定要绑hasSubMenu定在之前
  $(document).on('click','.menuItem',function(event){		
	   jumptourl($(this));	
	   //阻止父级再次调用 很重要的
	   event.stopPropagation();
  }); 
  
  //点击home图标
  $(document).on('click','.home',function(){
	   $(".markChoosen").removeClass("markChoosen").addClass("markNotChoosen");
	   $("#dataframe").attr("src","g1.do");
  });
}