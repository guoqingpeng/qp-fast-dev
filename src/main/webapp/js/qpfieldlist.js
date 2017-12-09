/**
功能说明------
所有事件的注册
一部分代码复制共通list.js
*/

var originFields=[];
var tableId;
var addindex = -1;
$(document).ready(function(){
  
  tableId = $("#smsc").attr("tableId");
  
  //页面自适应
  reSizeDataArea();
  
  //列表栏目数据
  initDefaultMenu(tableId);
  
  $('#saveField').click(function(){
      saveField(); 
  });
  
  monitorForValueChange();
  
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
			        title: '数据id',
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
			        'class': 'canSelect'
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
	    data:data,
	    height:500
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
	
   var selectBox = $("<select  style ='float:left;width:50%' class='form-control'/></select>");
	   selectBox.append("<option value=1>字符</option>")
				.append("<option value=2>富文本</option>")
				.append("<option value=3>EMAIL</option>")
				.append("<option value=4>URL</option>")
				.append("<option value=5>手机</option>")
				.append("<option value=6>座机</option>")
				.append("<option value=7>规则</option>")
				.append("<option value=8>地址</option>")
				.append("<option value=9>拼音</option>")
				.append("<option value=10>数据</option>")
				.append("<option value=11>金额</option>")
				.append("<option value=12>日期</option>")
				.append("<option value=13>时间</option>")
				.append("<option value=14>单选</option>")
				.append("<option value=15>单文档</option>")
				.append("<option value=16>内部对象</option>")
				.append("<option value=17>星期</option>")
				.append("<option value=18>用户名</option>")
				.append("<option value=19>密码</option>")
				.append("<option value=20>排序</option>")
				.append("<option value=21>微信号</option>")
				.append("<option value=22>微信昵称</option>")
				.append("<option value=23>微信公众标识号</option>")
				.append("<option value=24>经度</option>")
				.append("<option value=25>维度</option>")
				.append("<option value=26>颜色</option>")
				.append("<option value=27>坐席帐号</option>")
				.append("<option value=28>坐席密码</option>");
	$("td.canSelect").html(selectBox).append("<div style='float:left;width:50%' >"+
	                                              "<input type='text' name = 'rule' class='form-control' placeholder='*长度'>"+
	                                         "</div>");
	
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
    setEditAble();
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
    if(dataId > 0){//从已经存在的表删除
         field.dataId = dataId;
         field.enName = enName;
         fieldDeleteList.push(field);
    }
    deleteRowFromPage(dataId);
}

/**
功能说明------
添加一个字段,默认设置dataId为-1
*/
function qpAddField(){

   var field=new Object();
   field.dataId = (addindex--);
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
     if(rowObj.dataId < 0){
	   var field = new Object();
	   field.dataId = -1;
	   field.cnName = rowObj.cnName;
	   field.enName = rowObj.enName;
	   field.type = rowObj.type;
	   fieldAddList.push(field);
     }
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
		       currentPageRefresh();
		  },
		  error:ajaxErrorDelear
	});	
}
///这个地方来定义表格的添加和删除的数据的记录-------------------end

/**
功能说明------
当表格中的数据改变时，更新表格整体数据
*/
function monitorForValueChange(){

	  $(document).on('change','input,select',function(event){
	  
	     var index = $(this).parent().parent().attr("data-index");
	     
	     var row = new Object();
	     var fields = $(this).parent().parent().children();
	     row.dataId = parseInt($(fields[0]).html());//特别注意这个地方的数据的类型
	     
	     var td1 = $(fields[1]);
	     var input1 = td1.children()[0];
	     row.cnName = $(input1).val();
	     
	     var td2 = $(fields[2]);
	     var input2 = td2.children()[0];
	     row.enName = $(input2).val();
	     
	     var td3 = $(fields[3]);
	     var input3 = td3.children()[0];
	     var type = $(input3).val()        
	     row.type = type;
	     //如果变化的元素是select时，修改页面结构
	     var eventItem = $(this)[0].tagName;
	     if(eventItem == "SELECT"){
	          var div = $(this).next();
	          if(type ==7){
	              var input = "<input type='text' name = 'rule' class='form-control' placeholder='规则'>"
	              $(div).html(input);
	          }else if(type ==14){
				   var selectBox = $("<select  class='form-control'/></select>");
				   var optionsHtml = $("#cachedDictionary").html();
				   var options  = $.parseJSON(optionsHtml);
				   for(var opt in options){
				       var option = $("<option></option");
				       option.val(options[opt].id);
				       option.text(options[opt].name);
				       selectBox.append(option);
				   }
				   $(div).html(selectBox);
	          }else if(type ==16){
	               $(div).html("选择对象");              
	          }else if(type ==12){
	               $(div).html("选择日期格式");   
	               var selectBox = $("<select  class='form-control'/></select>");
	               selectBox.append("<option value=1>YYYY-MM-DD HH:MM:SS</option>")
	                        .append("<option value=2>YYYY-MM-DD</option>")
	               $(div).append(selectBox);  	                   
	          }else if(type ==13){
	               $(div).html("选择时间格式");
	               var selectBox = $("<select  class='form-control'/></select>");
	               selectBox.append("<option value=1>HH:MM:SS</option>")
	                        .append("<option value=3>HH:MM</option>");
	               $(div).append(selectBox);        
	          }else{
	               $(div).html("<input type='text' name = 'rule' class='form-control' placeholder='长度'>");
	          }
	     }
	     
	     $("#smsc").bootstrapTable('getOptions').data.splice(index, 1, row);
  });
  
}

/**
功能说明------
刷新当前页面
*/
function currentPageRefresh(){
       window.location.reload();
}