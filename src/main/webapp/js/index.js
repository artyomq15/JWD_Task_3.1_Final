function toggle (idName) {
	var element = document.getElementById(idName);
	element.classList.toggle("hidden");
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
	} else {
  		for(var i = 0, length = elements.length; i < length; i++) {
			elements[i].style.display = "none";
    		confirmation.style.border = "solid 1px green";    	
		}
  	}
}

