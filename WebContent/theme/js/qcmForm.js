$(document).ready( function(){
	$("#addSection").on("click", function(){
		var index = $(".fsSection").length;
		$("#firstSection").clone().appendTo("#sections");
		$(".fsSection").last().find("span#idSection").text(index+1);
	});
});