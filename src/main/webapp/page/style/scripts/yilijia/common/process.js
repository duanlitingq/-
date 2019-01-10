$(function () {
    $(".style-grow-nice>span:nth-of-type(2)").click(function () {
        $(this).parent().next().children("div").show();
        $(this).parent().next().children("div").css("color","yellow")
        $(this).parent().parent().siblings('.style-grow').
        children('.style-grow-word').children("div").hide();
    })
});