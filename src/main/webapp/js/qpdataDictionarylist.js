/**
功能说明------
所有事件的注册
*/
$(document).ready(function(){

  //展开或者隐藏搜索框,动态设置数据区域的高度
  $('.search-left').click(function(){
	  $('.searchForm').slideToggle(function(){
		   reSizeDataArea();
	  });
  });
  
  reSizeDataArea();
  //初始化左侧树和列表栏目数据
  initDefaultTree();
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

//--------------ZTree参数配置-----------------------------------------start
 var zTreeObj;
 var setting = {
    view: {
		dblClickExpand: false,
		showLine: false,
		selectedMulti: false,
		expandSpeed:"",
		showIcon: false
	},
	edit: {
		drag: {
			autoExpandTrigger: true,
			prev: true,
			inner: true,
			next: true
		},	
		enable: true,
		showRemoveBtn: true,
		showRenameBtn: false
	},	
	//回调事件在这个地方注册				
	callback:{
	    onClick:qpClick,
	    beforeDrag:qpBeforeDrag,
	    beforeDrop:qpBeforeDrop,
	    onDrop:qpOnDrop,
	    beforeRemove:qpOnRemove
	},
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "pid",
			rootPId: ""
		},
        keep: {
            parent: true,
            leaf: false
        }		
	}
 };
var zNodes = [];
//--------------ZTree参数配置-----------------------------------------end

/**
功能说明------
*初始化左侧树结构数据
*/
function initDefaultTree(){
	 $.ajax({
		  type: 'get',
		  url: 'dataDictionaryData.do',
		  dataType: 'json',
		  success:function(data){
		      zNodes = data;
			  //加载树形结构数据
			  zTreeObj = $.fn.zTree.init($("#qptree"), setting, zNodes);
			  //节点默认全部收缩
			  zTreeObj.expandAll(false);	
		  },
		  error:ajaxErrorDelear
		});
}		

/**
功能说明------
*初始化菜单数据
*/
function initDefaultMenu(){
	 $.ajax({
	  	  type: 'get',
		  url: 'dataDictionaryData.do',
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
			        title: '值'
			    },
			    {
			        field: 'pid',
			        title: '父id'
			    },
			    {
			        field: 'position',
			        title: '排序'
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
		  url: 'dataDictionaryDelete.do?id='+id,
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
*给菜单绑定事件
*/		
function qpClick(event, treeId, treeNode, clickFlag) {
      var id = treeNode.id;
      var dataurl = "dataDictionaryFromPid.do?pid="+id;
		$.ajax({
			  type: 'get',
			  url: dataurl,
			  dataType: 'json',
			  success:function(data){
				      createTable(data);
			  },
			  error:ajaxErrorDelear
		});		      
}

/**
功能说明------
*拖动前判断元素是否可以拖动
 需要检测当只有一个根节点时是不允许拖动的
*/	
function qpBeforeDrag(treeId, treeNodes){
     //父级元素不允许拖动
     var pid =treeNodes[0].pid;
     if(pid == 0 || pid == ""){
         return false;
     }
     return true;
}

/**
功能说明------
返回true决定元素可以放下，否则不能放下
并且有且仅在返回true时节点的ondrag事件才会执行
*元素放下前判断元素是否可以放下
*/	
function qpBeforeDrop(treeId, treeNodes, targetNode, moveType){
     //将元素插入到数据库，成功返回true失败返回false，
     //这个时候元素的位置才可以真正的改变
      var id = treeNodes[0].id;
      var pid = treeNodes[0].pid;
      var dataurl;
      /**判断一下移动到的目的节点的类型
      //prev移到它上面去
      //inner移到里面去
      //next移到后面去
      */
      
      //排序只允许在内部排序不允许拖出去
      var targetPid = targetNode.pid;
      if(pid != targetPid){
         return false;
      }
      
      switch(moveType){
      
		 case "prev":
		       dataurl =qpDragPrev(id,targetNode);
		 break;     
		 
 		 case "inner":
		      //dataurl = qpDragInner(id,targetNode);
		      return false;
		 break;    
		 
		 case "next":
		      dataurl = qpDragNext(id,targetNode);  
		 break;
		 
      }
      
      return changePidAndSort(dataurl);
}

/**
功能说明------
*prev类型的
*设置pid为目标元素的父元素id,
目标元素以及目标元素之后的【位置+1】
设置当前元素的位置为目标元素的位置
*/	
function qpDragPrev(id,targetNode){
     var pid = nullOrEmptyToZero(targetNode.pid);
     var targetPosition = targetNode.position;
     return "changDataDictionaryParent.do?id="+id+"&pid="+pid+"&position="+targetPosition;
}

/**
功能说明------
*inner类型的
*设置pid为目标元素的id
位置设置为最后一个元素
当前元素排序为1
*/	
function qpDragInner(id,targetNode){	   
     var pid = targetNode.id;
     var children = targetNode.children;
     var targetPosition = 1;
     var lastChild;
     //如果目标节点有子元素，则将拖拽节点的位置放在目标节点的最后一个
     //否则拖拽节点的位置为第一个
     if(children != undefined){
         lastChild = children[children.length-1];
         targetPosition = lastChild.position + 1;
     }
     return "changDataDictionaryParent.do?id="+id+"&pid="+pid+"&position="+targetPosition;
}

/**
功能说明------
*prev类型的
*设置pid为目标元素的父元素id,
修改目标元素之前的和之后的位置【位置加2】
设置当前元素的位置为目标元素的【位置+1】
*/
function qpDragNext(id,targetNode){
     var pid = nullOrEmptyToZero(targetNode.pid);
     var targetPosition = targetNode.position + 1;
     return "changDataDictionaryParent.do?id="+id+"&pid="+pid+"&position="+targetPosition;
}

/**
功能说明------
设置排序
设置拖拽元素的父级
*/
function changePidAndSort(dataurl){
	  //这个地方必须要加一个返回值来接受本次处理的结果,反映到树节点
	  var isSuccess = false;
	  $.ajax({
			  type: 'get',
			  url: dataurl,
			  async:false,
			  success:function(data){
			         if(data == "ok"){
			             initDefaultMenu(); 
			             isSuccess = true;
			         }else{
			             alert("系统错误");
			         }
			  },
			  error:ajaxErrorDelear
	   });
	   
	   return isSuccess;//决定元素是否可以放在新位置
}

/**
功能说明------
*元素放下后执行的操作
*/	
function qpOnDrop(event, treeId, treeNodes, targetNode, moveType, isCopy){

  //暂时没有任何操作
}

/**
功能说明------
*元素删除的方法
*/	
function qpOnRemove(treeId, treeNode){
   //暂时没有任何操作
    var id = treeNode.id;
    if(qpDelete(id)){
       return true;
    }  
    return false;
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