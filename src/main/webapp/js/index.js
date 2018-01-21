$( "#add_topic" ).click(function() {
	$( "#add_topic_block_content" ).slideToggle("slow");
});

$( "#themes" ).click(function() {
	$( "#themes_toggle_element" ).slideToggle("slow");
	var up_img = document.getElementById("up_img");
	var down_img = document.getElementById("down_img");
	up_img.classList.toggle("hidden");
	down_img.classList.toggle("hidden");
});

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

