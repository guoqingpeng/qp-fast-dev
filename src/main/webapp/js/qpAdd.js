// JavaScript Document
$(document).ready(function(){
   var options={
         success:function(data){
            
             alert(data);
              parentPageRefresh();
         },
         error:function(data){
             alert("网络发生故障")
         }
   };
   $('#menuAddForm').ajaxForm(options);
});

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