// index1页面中左侧导航栏功能js
$(function(){
    $(".leftnav h2").click(function(){
        $("#a_leader_txt").text($(this).text());
        $("#a_leader_txt1").text("");
        $(this).next().slideToggle(200);
        $(this).siblings().next("ul").hide(200);
        $(".leftnav h2").removeClass("on");
        $(".leftnav ul li a").removeClass("on");
        $(this).addClass("on");
    })
    $(".leftnav ul li a").click(function(){
        $("#a_leader_txt1").text($(this).text());
        $(".leftnav ul li a").removeClass("on");
        $(this).addClass("on");
    });
});
// 上传图片js
function changepic(obj) {
    var f=$(obj).val();
    if(f == null || f ==undefined || f == ''){
        alert("文件不正确！");
        return
    }
//        alert(f) ;
    if(!/\.(?:png|jpg|bmp|gif|PNG|JPG|BMP|GIF)$/.test(f))
    {
        alert("类型必须是图片(.png|jpg|bmp|gif|PNG|JPG|BMP|GIF)");
        $(obj).val('');
        return
    }
    // var data = new FormData();
    // $.each($(obj)[0].files,function(i,file){
    //     data.append('file', file);
    // });
    //console.log(obj.files[0]);//这里可以获取上传文件的name
    var newsrc=getObjectURL(obj.files[0]);
   // document.getElementById('show').src=newsrc;
    var attr = $(obj).parent(".input-file").prev("img").attr("src");
    if(isEmpty(attr)){
    addLi();//动态添加li
    }
    $(obj).parent(".input-file").hide();//隐藏多余的图片上传按钮
    $(obj).parent(".input-file").prev("img").attr("src",newsrc);//图片回显
}

function addLi() {
    var li = '<li style="position: relative">' +
        '<label>' +
        '<img src="" width="200">' +
    '<a class="button bg-blue input-file" href="javascript:void(0);">+ 图片上传' +
        '<input id="file" name="file" data-validate="required:" type="file" onchange="changepic(this)"/></a>' +
        '</label>' +
        '</li>';
    $(".addImgs").append(li);
}
//建立一個可存取到該file的url
function getObjectURL(file) {
    var url = null ;
    // 下面函数执行的效果是一样的，只是需要针对不同的浏览器执行不同的 js 函数而已
    if (window.createObjectURL!=undefined) { // basic
        url = window.createObjectURL(file) ;
    } else if (window.URL!=undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file) ;
    } else if (window.webkitURL!=undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file) ;
    }
    return url ;
}

// 复选框全选
function checkAll() {
    var all=document.getElementById('checkall');//获取到点击全选的那个复选框的id
    var one=document.getElementsByName('checkname[]');//获取到复选框的名称
    //因为获得的是数组，所以要循环 为每一个checked赋值
    for(var i=0;i<one.length;i++){
        one[i].checked=all.checked;
    }
}

//复选框批量显示
function changeishome(){
    var Checkbox=false;
    $("input[name='checkname[]']").each(function(){
        if (this.checked==true) {
            Checkbox=true;
        }
    });
    if (Checkbox){
        $("#listform").submit();
    }
    else{
        alert("请选择要操作的内容!");
        return false;
    }

}
//值是否为空
function isEmpty(value) {
    if(value==undefined){
        return true;
    }
    value = value.replace(/\s+/g,"");
    if(value==null||value==""||value==undefined){
    return true;
    }else{
        return false;
    }
}
// 展示
function open1(){
}
// 隐藏
function hide1(){

}
//输入数字校验
function isIntNum(val){
    //var regPos = / ^\d+$/; // 非负整数
    var regNeg = /^[0-9]*$/; // 负整数
    //if(regPos.test(val) || regNeg.test(val)){
    if(regNeg.test(val)){
        return false;
    }else{
        return true;
    }
}
//手机号校验
function isMobile(mobile){
    var regx = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|17[0-9]|18[0|1|2|3|5|6|7|8|9]|19[0-9])\d{8}$/;
    if(regx.test(mobile)){
        return false;
    }else{
        return true;
    }
}
//价格校验
function isPrice(price) {
    var regx = /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/;
    if(regx.test(price)){
        return false;
    }else{
        return true;
    }
}