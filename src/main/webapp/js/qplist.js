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
		showIcon: false
	},
	edit: {
		enable: true,
		showRemoveBtn: false,
		showRenameBtn: false
	},	
	//回调事件在这个地方注册				
	callback:{
	    onClick:qpClick,
	    beforeDrag:qpBeforeDrag,
	    beforeDrop:qpBeforeDrop,
	    onDrop:qpOnDrop
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
var zNodes = [];
//--------------ZTree参数配置-----------------------------------------end

/**
功能说明------
*初始化左侧树结构数据
*/
function initDefaultTree(){
	 $.ajax({
		  type: 'get',
		  url: 'treeData.do',
		  dataType: 'json',
		  success:function(data){
		      zNodes = data;
			  //加载树形结构数据
			  zTreeObj = $.fn.zTree.init($("#qptree"), setting, zNodes);
			  //节点默认全部展开
			  zTreeObj.expandAll(true);	
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
			        field: 'urlType',
			        title: '链接类型'
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
*给菜单绑定事件
*/		
function qpClick(event, treeId, treeNode, clickFlag) {
      var id = treeNode.id;
      var dataurl = "menuFromPid.do?pid="+id;
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
*/	
function qpBeforeDrag(treeId, treeNodes){
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
      var pid ;
      try{
         pid= targetNode.id;
      }catch(err){
         pid =0;
      }
      
      var dataurl = "changTreeParent.do?id="+id+"&pid="+pid;
      
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
	   
	   return isSuccess;//
}

/**
功能说明------
*元素放下后执行的操作
*/	
function qpOnDrop(event, treeId, treeNodes, targetNode, moveType, isCopy){
    //alert(targetNode.id)
    //暂时没有什么用处好像
    // console.log(targetNode.name+'放下后。。。。');
}

/**
功能说明------
ajax请求
*/
function ajaxErrorDelear(xhr,err,e){
     alert("网络错误！！！");
}
