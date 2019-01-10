$(function () {
    //banner 图片部分
        $.ajax({
            url: webSite + webName +"/page/front/advertisement/getPage",
            type:"GET",
            dataType:"json",
            success:function (res) {
                var imgsArr = res.list[6].imgsArr;
                $.each(imgsArr,function (imgs,v) {
                    var bannerImg =imgsArr[0];
                    $(".img-responsive").attr("src",webSite+webName+bannerImg);
                });
                var textArr = res.list[6];
                $.each(textArr,function (text,v) {
                    var title =textArr.name;
                    $(".style-text>h1").text(title);
                })
            },
            error:function (error) {
                console.log("error")
            }
        });

    //企业类别
    $.ajax({
        url:webSite + webName + "/page/front/enterprisetype/getPage",
        type:"GET",
        dataType:"json",
        data:{},
        async:false,
        success:function (res) {
            var styleList = res.list;
            for(var i=0;i<styleList.length;i++){
                if(i<2){
                    var styleId = styleList[i].id;
                    var styleName = styleList[i].name;
                    var styleEname = styleList[i].englishName;
                    console.log(styleEname,"======styleEname=========");
                    $(".style-public-title>h2").eq(i).text(styleName);
                    $(".style-public-title>p").eq(i).text(styleEname);
                    getIdInfo(styleId,i)
                }
            }
        },
        error:function (error) {
            console.log(error)
        }
    });
//    根据id获取数据展示
   function getIdInfo(id,index) {
       console.log(id,"========");
       $.ajax({
           url:webSite+webName+"/page/front/enterprisemien/getPage",
           type:"GET",
           dataType:"json",
           data:{typeId:id},
           async:false,
           success:function (res) {
               var arr=res.list;
               var arrLength=arr.slice(0,3);
               var len=arr.length;
               if(index==0){
                   $.each(arrLength,function (k,v) {
                       var html=null;
                       if (k==0){
                           var styleOne=$("#styleOne").html();
                           html=template(styleOne,{data:v});
                       }else if(k==1) {
                           var styleTwo=$("#styleTwo").html();
                           html=template(styleTwo,{data:v})
                       }else{
                           var styleThree=$("#styleThree").html();
                           html=template(styleThree,{data:v})
                       }
                       $(".style-content").append(html);
                   });
               }else{
                   $.each(arrLength,function (data,v) {
                       var stylePic = v.imgs;
                       var arrLength=arr.slice(0,1);
                       $(".stylePic img").attr("src",webSite+webName+stylePic);
                   });
               }
           },
           error:function () {
                console.log("请求失败")
           }
       })
   }
});