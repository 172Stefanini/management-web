$(document).ajaxStart(function(){
  $("#loadingDiv").css("display","block");
});
$(document).ajaxComplete(function(){
  $("#loadingDiv").css("display","none");
});