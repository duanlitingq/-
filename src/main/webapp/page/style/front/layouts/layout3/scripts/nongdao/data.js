/**
 * Created by admin on 2018/5/31.
 */
/*大国农道*/

function greenCross() {
    var articleId = jQuery.getURLParam("articleId");
    var num = 1;
    var rowBegin;
    var rowEnd;
    if (isEmpty(articleId)) {
        console.log("没有id");
    }
    $.get("/page/front/article/getById", {"id": articleId}, function (datas) {
        if (datas == "" || datas == "undefined" || datas == null) {
            $(".page-content-inner .row").html("此板块内容更新，请浏览其他板块！").css({"font-size": "20px", "text-align": "center"});
        }
        var imgs = "";
        var str=datas.info;
        str=str.replace(/<br>/g, '<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');//段落空格
        $("#actions").html(str);
        if (datas.arrImgs == "" || datas.arrImgs == null || datas.arrImgs == "undefined") {
            console.log("没有图片")
        }else{
            $("#title").html(datas.title + "<img style='height: 23px;margin: 0 5px;' src='" + datas.arrImgs[0] + "' onerror='imgError(this)'>");
            for (var j = 1; j < datas.arrImgs.length; j++) {
                rowBegin = "";
                rowEnd = "";
                if (num == 1) {
                    rowBegin = "<div class='row'>";
                }
                if (num == 3 || j ==  datas.arrImgs.length - 1) {
                    rowEnd = "</div>";
                }
                imgs +=rowBegin +
                    '<div class="col-md-4 col-sm-4">' +
                    '<div class="portlet light ">' +
                    '<img class="listImg" src="' + datas.arrImgs[j] + '" onerror="imgError(this)">' +
                    '</div>' +
                    '</div>'+ rowEnd;
                if (num < 3) {
                    num++;
                } else {
                    num = 1;
                }
            }
            $(".page-content-inner .imgs").after(imgs);
        }
    });

}
function ndlz() {
    var articleId = jQuery.getURLParam("articleId");
    var num = 1;
    var rowBegin;
    var rowEnd;
    if (isEmpty(articleId)) {
        console.log("没有id");
    }
    $.get("/page/front/article/getById", {"id": articleId}, function (datas) {
        if (datas == "" || datas == "undefined" || datas == null) {
            $(".page-content-inner .row").html("此板块内容更新，请浏览其他板块！").css({"font-size": "20px", "text-align": "center"});
        }
        $("#title").html(datas.title);
        var str=datas.info;
        str=str.replace(/<br>/g, '<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');//段落空格
        $("#actions").html(str);
        var imgs="";
        if (datas.arrImgs == "" || datas.arrImgs == null || datas.arrImgs == "undefined") {
            console.log("没有图片")
        } else {
            for (var j = 0; j < datas.arrImgs.length; j++) {
                rowBegin = "";
                rowEnd = "";
                if (num == 1) {
                    rowBegin = "<div class='row'>";
                }
                if (num == 3 || j ==  datas.arrImgs.length - 1) {
                    rowEnd = "</div>";
                }
                imgs +=rowBegin +
                    '<div class="col-md-4 col-sm-4">' +
                    '<div class="portlet light ">' +
                    '<img class="listImg" src="' + datas.arrImgs[j] + '" onerror="imgError(this)">' +
                    '</div>' +
                    '</div>'+ rowEnd;
                if (num < 3) {
                    num++;
                } else {
                    num = 1;
                }
            }

            $(".page-content-inner .imgs").after(imgs);
        }


    });

}


/*落地案例*/
function cases() {
    var articleId = jQuery.getURLParam("articleId");
    if (isEmpty(articleId)) {
        // articleId=6;//首页菜单ID
        console.log("id不存在")
    }
    $.get("/page/front/article/getById", {"id": articleId}, function (data) {
        var html = "";
        var imgs = "";
        if (data) {
            var str=data.info;
            str=str.replace(/<br>/g, '<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');
            // console.log(str);
            html = '' +
                '<div class="col-md-12 col-sm-12">' +
                '<div class="portlet light">' +
                '<div class="portlet-title">' +
                '<div class="caption caption-md">' +
                '<span class="caption-subject font-black bold uppercase regionName">' + data.title + '</span>' +
                '</div>' +
                '<div class="actions" id="content">' +
                '<div class="btn-group btn-group-devided" data-toggle="buttons">' +
                str +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>'

            for (var j = 0; j < data.arrImgs.length; j++) {
                imgs +=
                    '<div class="col-md-4 col-sm-4">' +
                    '<div class="portlet light ">' +
                    '<img class="listImg" src="' + data.arrImgs[j] + '" onerror="imgError(this)">' +
                    '</div>' +
                    '</div>'
            }


            $(".page-content-inner .row").append(html);
            $(".page-content-inner #imgs").append(imgs);
        }
    });
}

/*软件团队*/
function software() {
    var menuId = jQuery.getURLParam("menuId");
    if (isEmpty(menuId)) {
        // menuId=24;//首页菜单ID
    }
    $.get("/page/front/article/getPage", {"menuId": menuId}, function (data) {
        var html = "";
        var num = 1;
        var rowBegin;
        var rowEnd;
        if (data.list == "" || data.list == "undefined" || data.list == null) {
            $(".page-content-inner .row").html("此板块内容更新，请浏览其他板块！").css({"font-size": "20px", "text-align": "center"});
        }
        for (var i = 0; i < data.list.length; i++) {
            var datas = data.list[i];
            rowBegin = "";
            rowEnd = "";
            if (num == 1) {
                rowBegin = "<div class='row'>";
            }
            if (num == 2 || i == data.list.length - 1) {
                rowEnd = "</div>";
            }
            var str=datas.info;
            str=str.replace(/<br>/g, '<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');//段落空格
            html += rowBegin +
                '<div class="cols col-md-6 col-sm-6">' +
                '<div class="portlet light ">' +
                '<img class="listImg" src="' + datas.arrImgs[0] + '" onerror="imgError(this)">' +
                '</div>' +
                '<div class="light portlet-title portlet">' +
                '<div class="caption caption-md">' +
                '<span class="caption-subject font-black bold uppercase">' + datas.title + '</span>' +
                '</div>' +
                '<div class="actions">' +
                '<div class="btn-group btn-group-devided" data-toggle="buttons">' +
                str +
                '</div>' +
                '<a id="more" onclick="more(' + datas.id + ')" style="display: inline-block;text-align: right;width: 100%;">更多</a>' +
                '</div>' +
                '</div>' +
                '</div>' + rowEnd;
            if (num < 2) {
                num++;
            } else {
                num = 1;
            }
        }

        $(".page-content-inner .row").append(html);
        if(data.list.length<2){
            $(".cols").removeClass("col-md-6").addClass("col-md-12 col-sm-12");
            $(".cols").css("text-align","center");
            if(/Android|webOS|iPhone|iPad|iPad Pro|BlackBerry/i.test(navigator.userAgent)) {
                $(".cols").find(" img").css("width","100%");
            }else{
                $(".cols").find(" img").css("width","80%");
            }

            $("#more").remove();
            if(datas.info.length<5){
                $(".actions").remove();
                $(".uppercase").css("opacity","0");
            }
            if(datas.title.length<2){
                $(".uppercase").remove();
                $(".caption ").remove();
            }
            {

            }
        }
    });
}
//农道公益基金会新版
function nongdaogongyijijinhui() {
    var menuId = jQuery.getURLParam("menuId");
    if (isEmpty(menuId)) {
        // menuId=24;//首页菜单ID
    }
    $.get("/page/front/article/getPage", {"menuId": menuId}, function (data) {
        // console.log(data);
        var html = "";
        var num = 1;
        var rowBegin;
        var rowEnd;
        if (data.list == "" || data.list == "undefined" || data.list == null) {
            $(".page-content-inner .row").html("此板块内容更新，请浏览其他板块！").css({"font-size": "20px", "text-align": "center"});
        }
        var strs=data.list[0].info;
        strs=strs.replace(/<br>/g, '<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');//段落空格
        $("#actions").html(strs);
        $("#title").html(data.list[0].title + "<img style='height: 23px;margin: 0 5px;' src='" + data.list[0].arrImgs[0] + "' onerror='imgError(this)'>");
        var info;
        var moreHtml;
        for (var i = 1; i < data.list.length; i++) {
            var datas = data.list[i];
            rowBegin = "";
            rowEnd = "";
            if (num == 1) {
                rowBegin = "<div class='row'>";
            }
            if (num == 2 || i == data.list.length - 1) {
                rowEnd = "</div>";
            }
            info=datas.info;
            // alert(info.length);
            if(info.length>116){
                moreHtml='<a id="more"  onclick="more(' + datas.id + ')" style="display: inline-block;text-align: right;width: 100%;">更多</a>'
            }else {
                moreHtml=''
            }
            var str=datas.info;
            str=str.replace(/<br>/g, '<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');//段落空格
            html += rowBegin +
                '<div class="cols col-md-6 col-sm-12">' +
                '<div class="portlet light " >' +
                '<img class="listImg" src="' + datas.arrImgs[0] + '" onerror="imgError(this)">' +
                '</div>' +
                '<div class="light portlet-title portlet">' +
                '<div class="caption caption-md">' +
                '<span class="caption-subject font-black bold uppercase">' + datas.title + '</span>' +
                '</div>' +
                '<div class="actions">' +
                '<div class="btn-group btn-group-devided" data-toggle="buttons">' +
                str +
                '</div>' +
                moreHtml+
                '</div>' +
                '</div>' +
                '</div>' + rowEnd;

            if (num < 2) {
                num++;
            } else {
                num = 1;
            }
        }

        $(".page-content-inner .nongdaogongyi").append(html);
        if(data.list.length<2){
            $(".cols").removeClass("col-md-6").addClass("col-md-12 col-sm-12");
            $(".cols").css("text-align","center");
            if(/Android|webOS|iPhone|iPad|iPad Pro|BlackBerry/i.test(navigator.userAgent)) {
                $(".cols").find(" img").css("width","100%");
            }else{
                $(".cols").find(" img").css("width","80%");
            }

            $("#more").remove();
            if(str.length<5){
                $(".actions").remove();
                $(".uppercase").css("opacity","0");
            }
            if(datas.title.length<2){
                $(".uppercase").remove();
                $(".caption ").remove();
            }
            {

            }
        }
    });
}
/*硬件 and 工程统筹*/
function constructionUnit() {
    var menuId = jQuery.getURLParam("menuId");
    if (isEmpty(menuId)) {
        menuId = 24;//首页菜单ID
    }
    $.get("/page/front/article/getPage", {"menuId": menuId}, function (data) {
        var html = "";
        var num = 1;
        var rowBegin;
        var rowEnd;
        if (data.list == "" || data.list == "undefined" || data.list == null) {
            $(".page-content-inner .row").html("此板块内容更新，请浏览其他板块！").css({"font-size": "20px", "text-align": "center"});
        }
        for (var i = 0; i < data.list.length; i++) {
            var datas = data.list[i];
            var str=datas.info;
            str=str.replace(/<br>/g, '<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');//段落空格
            rowBegin = "";
            rowEnd = "";
            if (num == 1) {
                rowBegin = "<div class='row'>";
            }
            if (num == 3 || i == data.list.length - 1) {
                rowEnd = "</div>";
            }
            html += rowBegin +
                '<div class="col-md-4 col-sm-4">' +
                '<div class="portlet light ">' +
                '<img class="listImg" onclick="bigImgShow(this)" src="' + datas.arrImgs[0] + '" onerror="imgError(this)" >' +
                '</div>' +
                '<div class="light portlet-title portlet">' +
                '<div class="caption caption-md">' +
                '<span class="caption-subject font-black bold uppercase">' + datas.title + '</span>' +
                '</div>' +
                '<div class="actions">' +
                '<div class="btn-group btn-group-devided" data-toggle="buttons">' +
                str +
                '</div>' +
                '<a onclick="more(' + datas.id + ')" style="display: inline-block;text-align: right;width: 100%;">更多</a>' +
                '</div>' +
                '</div>' +
                '</div>' + rowEnd;
            if (num < 3) {
                num++;
            } else {
                num = 1;
            }
        }
        $(".page-content-inner .row").append(html);
    });
}
// var h=document.documentElement.clientHeight-200;
var h=document.documentElement.clientHeight;
function bigImgShow(self) {

    if(/Android|webOS|iPhone|iPad|iPad Pro|BlackBerry/i.test(navigator.userAgent)) {
        $(".page-content-inner").after('<div class="shade" onclick="$(this).hide()">' +
            '<img src="'+self.src+'" >' +
            '</div>'
        )
    }else{
        $(".page-content-inner").after('<div class="shade" style="line-height: '+h+'px" onclick="$(this).hide()">' +
            '<img src="'+self.src+'" >' +
            '</div>'
        )
    }



}
/*出版书籍*/
function publishedWorks() {
    var menuId = jQuery.getURLParam("menuId");
    if (isEmpty(menuId)) {
        menuId = 24;//首页菜单ID
    }

    $.get("/page/front/article/getPage", {"menuId": menuId}, function (data) {
        var html = "";
        var num = 1;
        var rowBegin;
        var rowEnd;
        if (data.list == "" || data.list == "undefined" || data.list == null) {
            $(".page-content-inner .row").html("此板块内容更新，请浏览其他板块！").css({"font-size": "20px", "text-align": "center"});
        }
        for (var i = 0; i < data.list.length; i++) {
            var datas = data.list[i];
            rowBegin = "";
            rowEnd = "";
            if (num == 1) {
                rowBegin = "<div class='row'>";
            }
            if (num == 3 || i == data.list.length - 1) {
                rowEnd = "</div>";
            }
            html += rowBegin +
                ' <div class="col-md-4 col-sm-4">' +
                '<div class="portlet light ">' +
                '<img class="listImg" src=' + datas.arrImgs[0] + ' onerror="imgError(this)">' +
                '<img class="QRcode" src=' + datas.arrImgs[1] + ' onerror="imgError(this)">' +
                '</div>' +
                '<div class="portlet light ">' +
                '<div class="portlet-title">' +
                '<div class="caption caption-md">' +
                '<span class="caption-subject font-black bold uppercase">《' + datas.title + '》</span>' +
                '<p class="caption-subject">出版时间：' + datas.pubDataStr +
                '</div>' +
                '<div class="actions">' +
                '<div class="btn-group btn-group-devided" data-toggle="buttons">' +
                datas.info +
                '</div>' +
                '<a onclick="bookMore(' + datas.id + ')" style="display: inline-block;text-align: right;width: 100%;">更多</a>' +
                '</div>' +
                '</div>' +
                '</div>' +
                ' </div>' + rowEnd;
            //
            if (num < 3) {
                num++;
            } else {
                num = 1;
            }
        }
        $(".page-content-inner").append(html);
    });
}
/*出版书籍 详情*/
function publishedWorksDetails() {
    var articleId = jQuery.getURLParam("articleId");
    if (isEmpty(articleId)) {
        articleId = 6;//首页菜单ID
    }

    $.get("/page/front/article/getById", {"id": articleId}, function (data) {
        var str=data.info;
        str=str.replace(/<br>/g, '<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');//段落空格
        $(".listImgBox .listImg").attr("src", data.arrImgs[0]);
        $(".listImgBox .QRcode").attr("src", data.arrImgs[1]);
        $(".uppercase").html(data.title);
        $(".pubDataStr").html("出版时间:" + data.pubDataStr);
        $(".actions div").html(str);
    });
}

/*特邀专栏   */
function specialColumn() {
    $.get("/page/front/invitedExpert/getPage", {}, function (data) {
        var html = "";
        var datas = "";
        var bookLinks = "";
        var bookLink = "";
        if (data.list == "" || data.list == "undefined" || data.list == null) {
            $(".page-content-inner .row").html("此板块内容更新，请浏览其他板块！").css({"font-size": "20px", "text-align": "center"});
        }
        for (var i = 0; i < data.list.length; i++) {
            datas = data.list[i];
            console.log(datas)
            var links = "";
            bookLinks = datas.expertArticleRespList;
            if (bookLinks == "") {
                console.log("暂无添加");
                links = "暂无添加";
            } else {
                if (bookLinks.length > 4) {
                    bookLinks.length = 4;
                }
                for (var j = 0; j < bookLinks.length; j++) {
                    bookLink = bookLinks[j];
                    links += '<a style="text-decoration: underline;margin-left: 5%" href="/page/front/theory/specialColumnDetails.html?specialColumnId=' + bookLink.id + '">' + bookLink.title + '</a>';

                }
                if (bookLinks.length >= 4) {
                    links += "<a href='/page/front/details/bookDetails.html?expertsId=" + datas.id + "' style='display: inline-block;text-align: right;'>更多文章……</a>"
                }
            }
            html += '' +
                '<div class="col-md-6 col-sm-6">' +
                '<div class="portlet light">' +
                '<div class="caption caption-md portlet light">' +
                '<span class="caption-subject font-black bold uppercase">' + datas.name + '</span>' +
                '</div>' +
                '<div class="row">' +
                '<div class="col-md-8 col-sm-8">' +
                '<div class="actions portlet-title" style="overflow: hidden;height: 180px">' +
                '<img class="listImg" src="' + datas.arrImgs[0] + '" onerror="imgError(this)">' +
                '</div>' +
                '<div class="portlet-title">' +
                '<div class="actions">' +
                '<div class="btn-group btn-group-devided" data-toggle="buttons">' +
                datas.info +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<div class="col-md-4 col-sm-4 links">' +
                '<p style="margin: 0 0 10px;">发表过的文章:</p>' +
                links +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>'

        }
        $(".page-content-inner .content").append(html);
    });
}
/*特邀专家详情 */
function bookDetails() {
    var expertsId = jQuery.getURLParam("expertsId");
    $.get("/page/front/invitedExpert/getById", {"id": expertsId}, function (data) {
        var html = "";
        var datas = "";
        var links = "";
        if (data) {
            for (var i = 0; i < data.expertArticleRespList.length; i++) {
                datas = data.expertArticleRespList[i];
                links += '<a href="' + datas.link + '">' + datas.title + '</a>';
                //console.log(data)
            }
        } else {
            $(".page-content-inner").html("没有");
            return;
        }
        var str=data.info;
        str=str.replace(/<br>/g, '<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');//段落空格
        html = ' <div class="row">' +
            '<div class="col-md-6 col-sm-6 " >' +
            '<div class="actions portlet-title" style="text-align: center;">' +
            '<img class="listImg" src="' + data.arrImgs[0] + '" onerror="imgError(this)">' +
            '</div>' +
            '</div>' +
            '<div class="col-md-6 col-sm-6 " >' +
            '<div class="portlet-title">' +
            '<div class="actions">' +
            '<div class="btn-group btn-group-devided" data-toggle="buttons">' +
            str +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>' +
            ' </div>' +
            '<div class="row">' +
            '<div class="col-md-12 col-sm-12 links">' +
            '<h4 style="margin: 10px 0 10px">发表过的文章:</h4>' +
            links +
            '</div>' +
            '</div>'

        $(".page-content-inner").html(html);
    });
}
/*文章详情*/
function specialColumnDetails() {
    var specialColumnId = jQuery.getURLParam("specialColumnId");
    $.get("/page/front/expertArticle/getById", {"id": specialColumnId}, function (data) {
        console.log(data);
        var str=data.info;
        str=str.replace(/<br>/g,'<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');//段落空格
        $(".articleTitle").html(data.title);
        $(".actions div").append(str);
    });
}


/*内容详情*/
function details() {
    var articleId = jQuery.getURLParam("articleId");
    if (isEmpty(articleId)) {
        console.log("id不存在");
    }
    var url = "";
    var arrImgs = "";
    $.get("/page/front/article/getById", {"id": articleId}, function (data) {
        for (var i = 0; i < data.arrImgs.length; i++) {
            url = data.arrImgs[i];
            arrImgs += '' +
                '<img class="listImg"  src="' + url + '" onerror="imgError(this)">'
        }
        if(data.arrImgs.length>1){
            $(".imgs").css("text-align","left");
        }
        $(".imgs").append(arrImgs);


        console.log(data.info.length)
        // if(data.info.length>260){
        //     // data.info.length = data.info.substring(0, 300) + "更多";
        //     $(".actions div").after('<a onclick="more(' + data.id + ')" style="display: inline-block;text-align: right;width: 100%;" class="more">更多</a>')
        // }
        var str=data.info;
        str=str.replace(/<br>/g, '<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');//段落空格
        $(".actions div").html(str);

    });
}
//联系我们 备用
// function contactUS() {
//     var menuId = jQuery.getURLParam("menuId");
//     if (isEmpty(menuId)) {
//         console.log("没有id");
//     }
//     $.get("/page/front/article/getPage", {"menuId": menuId}, function (data) {
//         if (data.list.length > 0) {
//
//         }
//     });
//
// }

function more(ids) {
    window.location.href = "/page/front/details/details.html?articleId=" + ids;
}
function bookMore(ids) {
    window.location.href = "/page/front/theory/publishedWorksDetails.html?articleId=" + ids;
}
function imgError(self) {
    self.src = '../../style/image/error.jpg'
}

