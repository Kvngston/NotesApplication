
$(document).ready(function(){

    $("#thover").click(function(){
        $(this).fadeOut();
        $("#tpopup").fadeOut();
    });


    $("#tclose").click(function(){
        $("#thover").fadeOut();
        $("#tpopup").fadeOut();
        $(this).fadeOut();
    });

});