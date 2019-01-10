$(function(){
    $.ajax({
        url: webSite + webName +"/page/front/advertisement/getPage",
        type:"GET",
        dataType:"json",
        success:function (res) {
            var aboutImgs = res.list[8].imgs;
            var aboutbanner = aboutImgs.split(";")[0];
            $(".img-responsive").attr("src",webSite+webName+aboutbanner);

        }
    });
});