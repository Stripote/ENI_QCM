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
	
	if( $("#reponseDonnee" ).length > 0 ){
		console.log("une réponse existe déja => "+$("#reponseDonnee" ).val() );
		$(".reponse").each( function(){
			
		});
	}
	
	//footer always on bottom
	if( $("body").height() > 1000 ){
		$(".bottom_menu").css("position", "relative");
	}
	$("body").bind("DOMSubtreeModified", function(){
		if( $("body").height() > 1000 ){
			$(".bottom_menu").css("position", "relative");
		}
	});
});