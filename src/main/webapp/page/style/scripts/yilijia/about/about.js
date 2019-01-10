$(function(){
        $.ajax({
            url: webSite + webName +"/page/front/advertisement/getPage",
            type:"GET",
            dataType:"json",
            success:function (res) {
                var imgsArr = res.list[3].imgsArr;
                $.each(imgsArr,function (imgs,v) {
                        var bannerImg =imgsArr[0];
                        $(".img-responsive").attr("src",webSite+webName+bannerImg);
                });
                var textArr = res.list[3];
                $.each(textArr,function (text,v) {
                    var title =textArr.name;
                    var con = textArr.info;
                    $(".banner-text>h2").text(title);
                    $(".banner-text>p").text(con);
                })

            },
            error:function (error) {
                console.log("error")
            }
        });
    });