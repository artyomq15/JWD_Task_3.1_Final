$( "#add_topic" ).click(function() {
	$( "#add_topic_block_content" ).slideToggle("slow");
});

$( "#themes" ).click(function() {
	if($(window).width() < 960) {
		$("#themes_toggle_element").slideToggle("slow");
		$("#up_img").toggle("fast");
		$("#down_img").toggle("fast");
	}
});

$( document ).ready(showBlocksIfBigDisplay());

function showBlocksIfBigDisplay(){
	if($(window).width() >= 960){
		$(".themes_block_items").show();
		$("#up_img").hide();
		$("#down_img").hide();
	}
}


function validatePassword(){
	var password = document.getElementById("password"),
		confirmation = document.getElementById("confirmation"),
		elements = document.getElementsByClassName("error_validation-confirmation");
	if(password.value != confirmation.value) {
  		for(var i = 0, length = elements.length; i < length; i++) {
    		elements[i].style.display = "block";
    		confirmation.style.border = "solid 1px red";
    	}
		confirmation.setCustomValidity("Passwords Don't Match");
	} else {
  		for(var i = 0, length = elements.length; i < length; i++) {
			elements[i].style.display = "none";
    		confirmation.style.border = "solid 1px green";    	
		}
		confirmation.setCustomValidity('');
  	}
}



