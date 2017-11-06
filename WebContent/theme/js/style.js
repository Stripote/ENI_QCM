$(document).ready( function(){
	$(".reponse").on("click", function(){
		if( $(this).prop("checked") ){
			$(this).parent().css("background-color", "#1b90c6");
			$(this).parent().css("color", "white");
		}
		else{
			$(this).parent().css("background-color", "transparent");
			$(this).parent().css("color", "black");
		}
	});
});