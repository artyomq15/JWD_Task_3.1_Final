function popup_message(){
    var delayShow = 500;
    var delayHide = 2500;
    setTimeout(function(){
        document.getElementById('popup_message').style.opacity=1;
    }, delayShow);
    setTimeout(function(){
        document.getElementById('popup_message').style.opacity=0;
    }, delayHide);
}
popup_message();