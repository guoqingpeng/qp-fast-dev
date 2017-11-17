/**
功能说明------
所有事件的注册
一部分代码复制共通list.js
*/
$(document).ready(function(){

  //展开或者隐藏搜索框,动态设置数据区域的高度
  $('.search-left').click(function(){
	  $('.searchForm').slideToggle(function(){
		   reSizeDataArea();
	  });
  });
  
  reSizeDataArea();
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
	 $.ajax({
	  	  type: 'get',
		  url: 'menuData.do',
		  dataType: 'json',
		  success:function(data){
		          createTable(data);
		  },
		  error:ajaxErrorDelear
	});			
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
			        field: 'operation',
			        title: '操作',
			        formatter:function(value, row, index){
			                  return '<a class="mod" >修改</a> ' + '<a class="delete"  onClick="qpDelete('+row.id+')">删除</a>';
			        }
			    },
			    {
			        field: 'id',
			        title: '数据id'
			    },
			    {
			        field: 'name',
			        title: '名称'
			    },
			    {
			        field: 'pid',
			        title: '父id'
			    },
			    {
			        field: 'position',
			        title: '位置'
			    },
			    {
			        field: 'url',
			        title: '链接'
			    },
			    {
			        field: 'level',
			        title: '层级'
			    }	    	    	    	    
	    ],
	    data:data
	});	
}


/**
功能说明------
*删除数据
*/
function qpDelete(id){

     if(qpDeleteDataFromDB(id)){
         deleteRowFromPage(id);
         currentPageRefresh();
         return true;
     }
     return false;
}

/**
功能说明------
*根据数据id删除表格中的一行数据
*/
function qpDeleteDataFromDB(id){
	var isDelete = false;
	$.ajax({
		  type: 'get',
		  url: 'menuDelete.do?id='+id,
		  async:false,
		  success:function(data){
			      if(data=="ok"){
			         isDelete = true;
			      }else{
			         alert(data);
			      }
		  },
		  error:ajaxErrorDelear
	});
	return isDelete;
}

/**
功能说明------
*根据数据id删除表格中的一行数据
*/
function deleteRowFromPage(id){
    $('#smsc').bootstrapTable('remove',
				    {
				           field:'id',
				           values:[id]
				    }
    );
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