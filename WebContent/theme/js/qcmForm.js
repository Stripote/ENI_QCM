$(document).ready( function(){
	$("#addSection").on("click", function(){
		var index = $(".fsSection").length;
		$("#firstSection").clone().appendTo("#sections");
		$(".fsSection").last().find("span#idSection").text(index+1);
	});
	
	$("#addReponse").on("click", function(){
		var index = $(".fsReponse").length;
		$("#firstreponse").clone().appendTo("#reponses");
		$(".fsReponse").last().find("span#idReponse").text(index+1);
	});
});