/*轮播的初始化加载*/
$(function(){
    var mySwiper = new Swiper ('.swiper-container', {
        direction: 'horizontal',
        speed:3000,
        effect : 'fade',
        loop: true,
        autoplay: 3000,
        disableOnInteraction:false,
        // 如果需要分页器
        pagination:  '.swiper-pagination'


    })

    /*如果只有一个slide就销毁swiper*/
    if(mySwiper.slides.length<1){
        mySwiper.destroy();
    }
})

jQuery.extend({
    /**
     * 获取地址栏中的参数
     * Returns get parameters.
     *
     * If the desired param does not exist, null will be returned
     *
     * @example value = $.getURLParam("paramName");
     */
    getURLParam: function(strParamName)
    {
        var strReturn = "";
        var strHref = window.location.href;/*.toUpperCase()*/

        var bFound = false;

        var cmpstring = strParamName + "=";/*.toUpperCase()*/
        var cmplen = cmpstring.length;

        if (strHref.indexOf("?") > -1)
        {
            var strQueryString = strHref.substr(strHref.indexOf("?") + 1);
            var aQueryString = strQueryString.split("&");
            for (var iParam = 0; iParam < aQueryString.length; iParam++)
            {
                if (aQueryString[iParam].substr(0, cmplen) == cmpstring)
                {
                    var aParam = aQueryString[iParam].split("=");
                    strReturn = aParam[1];
                    bFound = true;
                    break;
                }

            }
        }
        if (bFound == false) return null;
        return strReturn;
    },
    getStringParam: function(strParamName, strParamString)
    {
        var strReturn = "";
        var strHref = strParamString;/*.toUpperCase()*/
        var bFound = false;

        var cmpstring = strParamName + "=";/*.toUpperCase()*/
        var cmplen = cmpstring.length;

        var strQueryString = strHref;
        var aQueryString = strQueryString.split("&");
        for (var iParam = 0; iParam < aQueryString.length; iParam++)
        {
            if (aQueryString[iParam].substr(0, cmplen) == cmpstring)
            {
                var aParam = aQueryString[iParam].split("=");
                strReturn = aParam[1];
                bFound = true;
                break;
            }

        }

        if (bFound == false) return null;
        return strReturn;
    }
});