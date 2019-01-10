$(function(){

    $.ajax({
        url: webSite + webName +"/page/front/advertisement/getPage",
        type:"GET",
        dataType:"json",
        success:function (res) {
            var imgsArr = res.list[7].imgsArr;
            $.each(imgsArr,function (imgs,v) {
                var bannerImg =imgsArr[0];
                $(".img-responsive").attr("src",webSite+webName+bannerImg);
            });
            var textArr = res.list[7];
            $.each(textArr,function (text,v) {
                var title =textArr.name;
                var con = textArr.info;
                $(".brand-text>h2").text(title);
                $(".brand-text>p").text(con)
            })

        },
        error:function (error) {
            console.log("error")
        }
    });




//    品牌类别
    $.ajax({
        url:webSite + webName + "/page/front/brandtype/getPage/",
        type:"GET",
        dataType:"json",
        data:{},
        async:false,
        success:function (res) {
            var brandList = res.list;
            for (var i = 0; i < brandList.length; i++) {
                if(i < 3){
                    var brandId = brandList[i].id;
                    var brandName = brandList[i].name;
                    var brandEname = brandList[i].englishName;
                    $(".style-public-title >h2").eq(i).text(brandName);
                    $(".style-public-title >p").eq(i).text(brandEname);
                    getIdInfo(brandId,i);
                }
            }
        }
    });

    //根据id获取数据展示
    function getIdInfo(id,index){
        $.ajax({
            url: webSite + webName + "/page/front/brand/getPage" ,
            type:"GET",
            dataType:"json",
            data:{typeId:id},
            async:false,
            success:function (res) {
                var arr = res.list;
                if(index == 0){
                    var firstInfo = arr[0].info;
                    $("#first").text(firstInfo);

                }else if(index == 1){
                    var arrLength=arr.slice(0,4);
                    $.each(arrLength,function (k,v) {
                        var html=null;
                        if (k%2==0){
                            var brand=$("#brand").html();
                            html=template(brand,{data:v});
                        }else {
                            var brand=$("#brand-min").html();
                            html=template(brand,{data:v})
                        }
                        $(".brand-con").append(html);
                    });
                }else{
                    var arrPic=arr.slice(0,1);
                    $.each(arrPic,function (k,v) {
                        var img=v.imgs;
                        console.log(v,"========")
                        var html=null;
                        var brand=$("#hope").html();
                        html=template(brand,{data:v});
                        $(".brand-hope").append(html);
                    });
                }
            },
            error:function (error) {
                console.log(error,"================")
            }
        })
    }
});