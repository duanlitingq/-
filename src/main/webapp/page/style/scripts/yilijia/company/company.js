$(function(){
    $.ajax({
        url: webSite + webName +"/page/front/advertisement/getPage",
        type:"GET",
        dataType:"json",
        success:function (res) {
            var imgsArr = res.list[9].imgsArr;
            $.each(imgsArr,function (imgs,v) {
                var bannerImg =imgsArr[0];
                $(".img-responsive").attr("src",webSite+webName+bannerImg);
            });

        },
        error:function (error) {
            console.log("error")
        }
    });
});