function checkPasswordMatch() {
    var pass1 = document.getElementById("password");
    var pass2 = document.getElementById("password2")
    var password = pass1.value
    var confirmPassword = pass2.value;

    if (password != confirmPassword) {
        document.getElementById("password2").classList.add("error");
        return false;
    }
    else
        document.getElementById("password2").classList.remove("error");
    return true;

}
function checkPasswordCompMatch() {
    var pass1 = document.getElementById("passwordComp");
    var pass2 = document.getElementById("passwordComp2")
    var password = pass1.value
    var confirmPassword = pass2.value;

    if (password != confirmPassword) {
        document.getElementById("passwordComp2").classList.add("error");
        return false;
    }
    else
        document.getElementById("passwordComp2").classList.remove("error");
    return true;

}
$('.message a').click(function(){
    $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});
