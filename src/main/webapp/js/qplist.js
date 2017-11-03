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