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
		  url: 'tableData.do',
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
			                  console.log(row.dataId+"----"+row.enName)
			                  var id = row.dataId;
			                  var tableName = row.enName;
			                  return '<a class="modify" id='+id+' tableName='+ tableName+' onClick="qpUpdate(this)">修改</a> ' + 
			                         '<a class="delete" id='+id+' tableName='+ tableName+' onClick="qpDelete(this)">删除</a>';
			        }
			    },
			    {
			        field: 'dataId',
			        title: '数据id'
			    },
			    {
			        field: 'cnName',
			        title: '对象中文名称'
			    },
			    {
			        field: 'enName',
			        title: '数据库表名'
			    },
			    {
			        field: 'isParent',
			        title: '是否支持父子级'
			    },
			    {
			        field: 'description',
			        title: '描述'
			    }	    	    	    	    
	    ],
	    detailView: true,
        detailFormatter: function (index, row) {
            //这个地方直接返回一个iframe表格里面的数据是该对象下的所有字段
            var url = "fields.do?tableId="+row.dataId;
            return '<iframe id ="qpPage" src="'+url+'"></iframe>';
        },
	    data:data
	});	
}

/**
功能说明------
*更新数据
*/
function qpUpdate(ele){
     var id = $(ele).attr("id");
     var tableName = $(ele).attr("tableName");
     var url = "fields.do?tableId="+id;
     $("#qpDialog").find("iframe").attr("src",url);
     $("#qpDialog").modal('show');
}

/**
功能说明------
*删除数据
*/
function qpDelete(ele){
     var id = $(ele).attr("id")
     var tableName = $(ele).attr("tableName");
     if(qpDeleteDataFromDB(id,tableName)){
         deleteRowFromPage(id);
         currentPageRefresh();
         return true;
     }
     return false;
}

/**
功能说明------
*根据数据id删除表格中的一行数据
*删掉数据库
*/
function qpDeleteDataFromDB(id,tableName){
	var isDelete = false;
	$.ajax({
		  type: 'get',
		  url: 'tableDelete.do?id='+id+'&tableName='+tableName,
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
				           field:'dataId',
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