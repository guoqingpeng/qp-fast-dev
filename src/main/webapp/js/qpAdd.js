// JavaScript Document
$(document).ready(function(){
   var options={
         success:function(data){
             var ms = $.parseJSON(data);
             alert(ms.msg);
             parent.location.reload();
         },
         error:function(data){
             alert("网络发生鼓掌")
         }
   };
   $('#menuAddForm').ajaxForm(options);
});