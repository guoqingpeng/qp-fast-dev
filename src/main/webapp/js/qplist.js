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
var zNodes = [];
//--------------ZTree参数配置-----------------------------------------end

/**
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
		  }
		});
}		

/**
*初始化菜单数据
*/
function initDefaultMenu(){
	 $.ajax({
	  	  type: 'get',
		  url: 'menuData.do',
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

/**
*给菜单绑定事件
*/		
function clickme(event, treeId, treeNode, clickFlag) {
      var id = treeNode.id;
      var dataurl = "menuFromPid.do?pid="+id;
		$.ajax({
			  type: 'get',
			  url: dataurl,
			  dataType: 'json',
			  success:function(data){
			        //需要销毁表格首先，否则数据不能加载
			        $('#smsc').bootstrapTable('destroy'); 
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