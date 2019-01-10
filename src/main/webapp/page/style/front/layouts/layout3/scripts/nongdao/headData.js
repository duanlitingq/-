$(function () {
    $.post("/page/front/menu/getList", {"parentId": 0}, function (result) {
        var menuStr = "<div class='container'><div class='hor-menu'><ul class='nav navbar-nav'>";
        console.log("========================分割线===============================");
        for (var i = 0; i < result.length; i++) {
            menuStr += "<li class='menu-dropdown classic-menu-dropdown'  style='position: relative'>" +
                " <a href='"+result[i].pageUrl+"' onmouseenter='getMenuList2(\""+result[i].id+"\",this,\"1\")' onclick='getMenuList2(\""+result[i].id+"\",this,\"2\")' class='nav-link '>" + result[i].name +
                "<span class='arrow'></span></a>" +
                "</li>";
        }
        menuStr += "</ul></div> </div>";
        $(".page-header-menu").html(menuStr);
    })
})

function getMenuList2(parentId,obj,type) {
    obj = $(obj).parent();
    if(/Android|webOS|iPhone|iPad|iPad Pro|BlackBerry/i.test(navigator.userAgent) && type == "1")return;
    if($(obj).find("ul").length>0){ //如果已经进来二级菜单就不重复提交
        $(obj).addClass("active").siblings().removeClass("active");
        if($(obj).hasClass("opened")){

            $(obj).removeClass("opened");
        }else {
            $(obj).addClass("opened").siblings().removeClass("opened");
        }
        return;
    }
    $(obj).addClass("opened").siblings().removeClass("opened");
    $(obj).addClass("active").siblings().removeClass("active");
    $(obj).find(".li2").remove();//先清空li下所有的子菜单,然后再追加

    $.post("/page/front/menu/getList", {"parentId": parentId}, function (result) {

        if( result.length>1){
            var menuStr = '<ul class="dropdown-menu pull-left li2" style="min-width:120px;left: 0;">';
            for (var i = 0; i < result.length; i++) {
                menuStr+="<li class='lvshizi'  style='position: relative'>" +
                    "<a href='"+result[i].pageUrl+"' onmouseenter='getMenuLists3(\""+result[i].id+"\",this,\"1\")' onclick='getMenuLists3(\""+result[i].id+"\",this,\"2\")'>"+result[i].name+"" +
                    "<span class='arrow' style='display: none'></span></a></li>"
            }
            menuStr += "</ul>";
            $(obj).append(menuStr);
        }
    })

}
//三级菜单
function getMenuLists3(parentId,obj,type) {
    obj = $(obj).parent();
    if(/Android|webOS|iPhone|iPad|iPad Pro|BlackBerry/i.test(navigator.userAgent) && type == "1")return;
    if($(obj).find("ul").length>0){ //如果已经进来三级菜单就不重复提交
        if($(obj).hasClass("opened")){
            $(obj).removeClass("opened");
        }else {
            $(obj).addClass("opened").siblings().removeClass("opened");
        }
        return;
    }
    $(obj).addClass("opened").siblings().removeClass("opened");
    // $(obj).addClass("active").siblings().removeClass("active");
    $(obj).find(".li3").remove();
    $.post("/page/front/menu/getList", {"parentId": parentId}, function (result) {
        console.log(result);
        if( result.length>0){
            // $(obj).siblings().find("a").append('<span class="arrow"></span>');
            // $(obj).find("a").append('<span class="arrow"></span>');
            $(".arrow").show();
           var menuStr = "<ul class='dropdown-menu ul3' style='left: 119px;top:0;min-width:120px;z-index: 999;display: inline'>";
            for (var i = 0; i < result.length; i++) {
                menuStr+="<li class='lvshizi li3'><a href='"+result[i].pageUrl+"' class='nav-link nav-toggle '>"+result[i].name+"</a></li>"
            }
            menuStr += "</ul>";
            $(obj).append(menuStr);
            $(obj).siblings("li").find(".ul3").remove();//先清空li下所有的子菜单,然后再追加
        }
    })


}
function myBrowser(){
    //获取不同浏览器
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    var isOpera = userAgent.indexOf("Opera") > -1;
    if (isOpera) {
        return "Opera"
    }; //判断是否Opera浏览器
    if (userAgent.indexOf("Firefox") > -1) {
        return "FF";
    } //判断是否Firefox浏览器
    if (userAgent.indexOf("MicroMessenger") > -1){
        return "MicroMessenger";//微信浏览器
    }
    if (userAgent.indexOf("Safari") > -1) {
        return "Safari";//苹果自带浏览器
    } //判断是否Safari浏览器
    if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
        return "IE";
    }; //判断是否IE浏览器
}

