/**
功能说明------
所有事件的注册
一部分代码复制共通list.js
*/
$(document).ready(function(){

  //列表栏目数据
  initDefaultMenu();
});

/**
功能说明------
动态设置数据区域的高度
*/
function reSizeDataArea(){
	 var HlistArea = $('.listArea').height();
	 var Hqpsearch = $('.qp-search ').height();
	 var Hqppagestate = $('.qp-page-state').height();
	 var Hqpdata = HlistArea - Hqpsearch - Hqppagestate + "px";
	 $('.qp-data').css("height",Hqpdata);
}

/**
功能说明------
*初始化菜单数据
*/
function initDefaultMenu(){
	createTable(${data});
}

/**
功能说明------
*表格数据渲染
*/	
function createTable(data){
    //需要销毁表格首先，否则数据不能加载
    $('#smsc').bootstrapTable('destroy'); 
	$('#smsc').bootstrapTable({
	    columns: [
			    {
			        field: 'dataId',
			        title: '数据id'
			    },
			    
			    {
			        field: 'cnName',
			        title: '字段中文名'
			    },
			    {
			        field: 'enName',
			        title: '字段英文名'
			    },	
			    {
			        field: 'type',
			        title: '字段类型'
			    }					    		    	    	    	    
	    ],
	    data:data
	});	
}

/**
功能说明------
ajax请求
*/
function ajaxErrorDelear(xhr,err,e){
     //暂时简单的返回错误
     alert("网络错误！！！");
}

/**
功能说明------
简单的为空，未定义的数据转化成0返回
*/
function nullOrEmptyToZero(code){
    if(code == "" || 
       code == "undefined" || 
       code == undefined || 
       code == null){
        return 0;
    }
    return code;
}

/**
功能说明------
刷新当前页面
*/


/**
功能说明------
刷新当前页面
*/
function currentPageRefresh(){
       window.location.reload();
}

/**
功能说明------
刷新父页面
*/
function parentPageRefresh(){
       parent.location.reload();
}