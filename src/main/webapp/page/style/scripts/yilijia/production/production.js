$(function(){
    //banner 图片部分
        $.ajax({
            url: webSite + webName +"/page/front/advertisement/getPage",
            type:"GET",
            dataType:"json",
            success:function (res) {
                var imgsArr = res.list[5].imgsArr;
                $.each(imgsArr,function (imgs,v) {
                    var bannerImg =imgsArr[0];
                    $(".img-responsive").attr("src",webSite+webName+bannerImg);
                });
            },
            error:function (error) {
                console.log("error")
            }
        });

//    产品展示
     $.ajax({
         url:webSite+webName+"/page/front/product/getPage",
         type:"GET",
         dataType:"json",
         data:{pageSize:12},
         success:function (res) {
             $.each(res.list,function (k,v) {
                 var html=null;
                 var production=$("#production").html();
                 html=template(production,{data:v});
                 $(".proImg").append(html);
             });
             console.log(res,"==============");
             //分页
             var allpage=res.lastPage;
             var size = res.pageSize;
             $('.M-box1').pagination({
                 pageCount:allpage,
                 mode:"fixed",
                 callback: function (api) {
                     var index=api.getCurrent();
                     console.log(index);
                     $(".M-box1 .active").css({"background":"#fff100"});
                     $.ajax({
                         url:webSite+webName+"/page/front/product/getPage",
                         type:'GET',
                         data:{
                             pageNum:index,pageSize:12
                         },
                         dataType:"json",
                         success:function (res) {
                             $(".production").remove();
                             $.each(res.list,function (k,v) {
                                 var html=null;
                                 var production=$("#production").html();
                                 html=template(production,{data:v});
                                 $(".proImg").append(html);
                             });
                         }
                     })
                 }
             });
         },
         error:function (error) {
             console.log("请求失败")
         }
     })

//    分页

});