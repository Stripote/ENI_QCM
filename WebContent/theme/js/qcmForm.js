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
		$(".fsReponse").last().find("input").first().val("")
		$(".fsReponse").last().find("input[type='checkbox']").val(index+1);
		
		var btnDelete = '<span title="Retirer la question" class="delRep glyphycon glyphicon glyphicon-remove"></span>';
		$(".fsReponse").last().find("span#idReponse").append(btnDelete);
	});
	
	$("body").on("click", ".delRep", function(){
		$(this).closest(".fsReponse").remove();
	});
});