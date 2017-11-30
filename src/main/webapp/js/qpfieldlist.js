/**
功能说明------
所有事件的注册
一部分代码复制共通list.js
*/

var originFields=[];
var tableId;
$(document).ready(function(){

  tableId = $("#smsc").attr("tableId");
  
  //页面自适应
  reSizeDataArea();
  
  //列表栏目数据
  initDefaultMenu(tableId);
  
  $('#saveField').click(function(){
      saveField();
  });
  
  $(document).on('change','input',function(event){
  
     var index = $(this).parent().parent().attr("data-index");
     var row = new Object();
     var fields = $(this).parent().parent().children();
     row.dataId = $(fields[0]).html();
     
     var td1 = $(fields[1]);
     var input1 = td1.children()[0];
     row.cnName = $(input1).val();
     
     var td2 = $(fields[2]);
     var input2 = td2.children()[0];
     row.enName = $(input2).val();
     
     var td3 = $(fields[3]);
     var input3 = td3.children()[0];                   
     row.type = $(input3).val();
     $("#smsc").bootstrapTable('getOptions').data.splice(index, 1, row);
     
  });  
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
function initDefaultMenu(tableId){
	 $.ajax({
	  	  type: 'get',
		  url: 'fieldData.do?tableId='+tableId,
		  dataType: 'json',
		  success:function(data){
		          createTable(data);
		          originFields = data;
		          setEditAble();
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
			        field: 'dataId',
			        title: '数据id'
			    },
			    
			    {
			        field: 'cnName',
			        title: '字段中文名',
			        'class': 'canEdit'
			    },
			    {
			        field: 'enName',
			        title: '字段英文名',
			        'class': 'canEdit'
			    },	
			    {  
			        field: 'type',
			        title: '字段类型',
			        'class': 'canEdit'
			    },
			    {  
			        field: 'tableName',
			        title: '表名',
			    },			    
			    {  
			        field: 'operation',
			        title: '[<a id="qpAddField" onClick="qpAddField()">添加</a>]',
			        formatter:function(value, row, index){
			                  var id = row.dataId;
			                  var enName = row.enName;
			                  return '<a class="clear" id='+id+' onClick="qpClearData(this)">清空数据</a> ' + 
			                         '<a class="delete" id='+id+' enName="'+enName+'"  onClick="qpDeleteField(this)">删除</a>';
			        }			        
			    }			        		    	    	    	    
	    ],
	    data:data
	});	
}

/**
功能说明------
将表格中的数据转化成可编辑状态的
*/
function setEditAble(){
	$("td.canEdit").each(function(){
	    var oldHtml = $(this).html();
	    var editAbleHtml = $("<input class='form-control' type='text'/>");
	    editAbleHtml.val(oldHtml);
	    $(this).html(editAbleHtml);
	});
}

/**
功能说明------
ajax请求错误统一处理
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



///这个地方来定义表格的添加和删除的数据的记录-------------------start
var fieldUpdateList = [];//根据状态判断的
var fieldDeleteList = [];//根据删除按钮添加的

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
*根据数据id删除表格中的一行数据
*/
function addRowToPage(row){
    $('#smsc').bootstrapTable('insertRow',{
              index:0,
              row:row
    });
    setEditAble();
}

/**
功能说明------
删除一个字段
*/
function qpDeleteField(ele){
    var field=new Object();
    //看看这个id是否为-1
    var dataId =parseInt($(ele).attr("id"));
    var enName = $(ele).attr("enName");
    if(dataId != -1){//从已经存在的表删除
         field.dataId = dataId;
         field.enName = enName;
         fieldDeleteList.push(field);
    }
    deleteRowFromPage(dataId);
    setEditAble();
}

/**
功能说明------
添加一个字段,默认设置dataId为-1
*/
function qpAddField(){
   var field=new Object();
   field.dataId = -1;
   field.cnName="";
   field.enName="";
   field.type=0;
   addRowToPage(field);
   
}

/**
功能说明------
将表格中的数据分为三类,并且已json字符串的格式保存
哪些是添加的 fieldAddList
哪些是修改了的 fieldUpdateList
哪些是删除了的字段 fieldDeleteList
*/
function createCommitFields(){
   var commitFields=new Object();
   commitFields.tableId = tableId;
   commitFields.fieldAddList = getNewAddFields();
   commitFields.fieldUpdateList = fieldUpdateList;
   commitFields.fieldDeleteList = fieldDeleteList;
   return JSON.stringify(commitFields);
}


/**
功能说明------
获取列表中新加的字段dataId=-1
*/
function getNewAddFields(){
  var fieldAddList = [];//dataId为-1的就是新加的
  var rows = $('#smsc').bootstrapTable('getData');
  for(row in rows){
     var rowObj = rows[row];
     if(rowObj.dataId == -1){
	   var field = new Object();
	   field.dataId = -1;
	   field.cnName = rowObj.cnName;
	   field.enName = rowObj.enName;
	   field.type = rowObj.type;
	   fieldAddList.push(field);
     }
     console.log(rowObj.dataId);
  }
  return fieldAddList;
}

/**
功能说明------
提交分类了的字段
*/
function saveField(){
	 var fields = createCommitFields();
	 $.ajax({
	  	  type: 'POST',
		  url: 'updateFields.do?fields='+fields,
		  contentType:'application/json',
		  data:fields,
		  success:function(data){
		       alert("成功");
		  },
		  error:ajaxErrorDelear
	});	
}
///这个地方来定义表格的添加和删除的数据的记录-------------------end