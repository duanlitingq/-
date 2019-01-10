/**
 * 追加自定义验证方法，来源网络
 *
 * version : 1.0
 * create : 公安知识服务平台 www.up72.com
 */

//================身份证号码验证开始==================================================
/**
 * 添加身份证号码验证
 */
jQuery.validator.addMethod("idcard", function (value, element) {
    return this.optional(element) || isIdCardNo(value);
}, "请正确输入身份证号码");

/**
 * 身份证号码验证
 *
 */
function isIdCardNo(obj) {
    var aCity = {11: "北京", 12: "天津", 13: "河北", 14: "山西", 15: "内蒙古", 21: "辽宁", 22: "吉林", 23: "黑龙江", 31: "上海", 32: "江苏", 33: "浙江", 34: "安徽", 35: "福建", 36: "江西", 37: "山东", 41: "河南", 42: "湖北", 43: "湖南", 44: "广东", 45: "广西", 46: "海南", 50: "重庆", 51: "四川", 52: "贵州", 53: "云南", 54: "西藏", 61: "陕西", 62: "甘肃", 63: "青海", 64: "宁夏", 65: "新疆", 71: "台湾", 81: "香港", 82: "澳门", 91: "国外"};
    var iSum = 0;
    var strIDno = obj;
    var idCardLength = strIDno.length;
    if (!/^\d{17}(\d|x)$/i.test(strIDno) && !/^\d{15}$/i.test(strIDno)) {
        return false; // 非法身份证号
    }

    if (aCity[parseInt(strIDno.substr(0, 2))] == null) {
        return false;// 非法地区
    }

    // 15位身份证转换为18位
    if (idCardLength == 15) {
        sBirthday = "19" + strIDno.substr(6, 2) + "-" + Number(strIDno.substr(8, 2)) + "-" + Number(strIDno.substr(10, 2));
        var d = new Date(sBirthday.replace(/-/g, "/"))
        var dd = d.getFullYear().toString() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
        if (sBirthday != dd) {
            return false; // 非法生日
        }
        strIDno = strIDno.substring(0, 6) + "19" + strIDno.substring(6, 15);
        strIDno = strIDno + GetVerifyBit(strIDno);
    }

    // 判断是否大于2078年，小于1900年
    var year = strIDno.substring(6, 10);
    if (year < 1900 || year > 2078) {
        return false;// 非法生日
    }

    // 18位身份证处理

    // 在后面的运算中x相当于数字10,所以转换成a
    strIDno = strIDno.replace(/x$/i, "a");

    sBirthday = strIDno.substr(6, 4) + "-" + Number(strIDno.substr(10, 2)) + "-" + Number(strIDno.substr(12, 2));
    var d = new Date(sBirthday.replace(/-/g, "/"))
    if (sBirthday != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate())) {
        return false; // 非法生日
    }
    // 身份证编码规范验证
    for (var i = 17; i >= 0; i--) {
        iSum += (Math.pow(2, i) % 11) * parseInt(strIDno.charAt(17 - i), 11);
    }
    if (iSum % 11 != 1) {
        return false;// 非法身份证号
    }

    // 判断是否屏蔽身份证
    var words = new Array();
    words = new Array("11111119111111111", "12121219121212121");

    for (var k = 0; k < words.length; k++) {
        if (strIDno.indexOf(words[k]) != -1) {
            return false;
        }
    }

    return true;
}
//15位转18位中,计算校验位即最后一位
function GetVerifyBit(id){
    var result;
    var nNum=eval(id.charAt(0)*7+id.charAt(1)*9+id.charAt(2)*10+id.charAt(3)*5+id.charAt(4)*8+id.charAt(5)*4+id.charAt(6)*2+id.charAt(7)*1+id.charAt(8)*6+id.charAt(9)*3+id.charAt(10)*7+id.charAt(11)*9+id.charAt(12)*10+id.charAt(13)*5+id.charAt(14)*8+id.charAt(15)*4+id.charAt(16)*2);
    nNum=nNum%11;
    switch (nNum) {
        case 0 :
            result="1";
            break;
        case 1 :
            result="0";
            break;
        case 2 :
            result="X";
            break;
        case 3 :
            result="9";
            break;
        case 4 :
            result="8";
            break;
        case 5 :
            result="7";
            break;
        case 6 :
            result="6";
            break;
        case 7 :
            result="5";
            break;
        case 8 :
            result="4";
            break;
        case 9 :
            result="3";
            break;
        case 10 :
            result="2";
            break;
    }
    //document.write(result);
    return result;
}
// ================身份证号码验证结束==================================================
/**
 * 字母数字
 */
jQuery.validator.addMethod("alnum", function (value, element) {
    return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
}, "只能包括英文字母和数字");

/**
 * 正数
 */
jQuery.validator.addMethod("znumber", function (value, element) {
    return this.optional(element) || /^[0-9]+(\.\d+)?$/.test(value);
}, "只能是数字，并且大于0");

/**
 * 位数最少6位，最多18位
 */
jQuery.validator.addMethod("val_length", function (value, element) {
    var length = value.length;
    return this.optional(element) || (length >= 6 && length <= 18);
}, "长度应该大于6并小于18");
/**
 * 手机号码验证
 */
jQuery.validator.addMethod("mobile", function (value, element) {
    var length = value.length;
    return this.optional(element) || (length == 11 && /^(1\d{10})$/.test(value));
}, "请正确填写手机号码");

/**
 * 邮政编码验证
 */
jQuery.validator.addMethod("zipcode", function (value, element) {
    var tel = /^[0-9]{6}$/;
    return this.optional(element) || (tel.test(value));
}, "请正确填写邮政编码");

/**
 * 汉字
 */
jQuery.validator.addMethod("chchar", function (value, element) {
    var tel = /^[\u4e00-\u9fa5]|[^[a-zA-Z0-9]+$/;
    return this.optional(element) || (tel.test(value));
}, "请输入汉字");

/**
 * 用户名校验，字母，数字，下划线，中文
 */
jQuery.validator.addMethod("cname", function (value, element) {
    var tel = /^[^A-Za-z0-9\u4E00-\u9FA5]|[^\w\u4E00-\u9FA5.-]|([_.-])\1/;
    return !(this.optional(element) || (tel.test(value)));
}, "仅可使用汉字、数字、字母和下划线");

/**
 * 用户名校验，字母，数字，下划线
 */
jQuery.validator.addMethod("zname", function (value, element) {
    var tel = /^\w+$/;
    return this.optional(element) || (tel.test(value));
}, "用户名仅可使用数字、字母和下划线");

/**
 * 字节最小长度校验
 */
jQuery.validator.addMethod("byteMin", function (value, element, param) {
    var result = value.replace(/[^\x00-\xff]/g, "xx").length;
    return this.optional(element) || (result >= param);
}, "长度不能少于{0}个字节");

/**
 * 字节最大长度校验
 */
jQuery.validator.addMethod("byteMax", function (value, element, param) {
    var result = value.replace(/[^\x00-\xff]/g, "xx").length;
    return this.optional(element) || (result <= param);
}, "长度不能超过{0}个字节");

jQuery.validator.addMethod("ip", function (value, element) {
    return ($.trim(value) == 'localhost') || (this.optional(element) || (/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)));
}, "请输入合法的IP信息");

/**
 * URL验证
 */
jQuery.validator.addMethod("isUrl", function (value, element, param) {
    var tel = "^((https|http|ftp|rtsp|mms)?://)"
        + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@
        + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
        + "|" // 允许IP和DOMAIN（域名）
        + "([0-9a-z_!~*'()-]+\.)*" // 域名- www.
        + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名
        + "[a-z]{2,6})" // first level domain- .com or .museum
        + "(:[0-9]{1,4})?" // 端口- :80
        + "((/?)|" // a slash isn't required if there is no file name
        + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
    return !(this.optional(element) || (tel.test(value)));
}, "请填写正确的URL地址");

/**
 * URI验证
 */

jQuery.validator.addMethod("uri", function (value, element) {
    var tel = "([0-9a-zA-Z_!~*'()-]+\/?)*"
        + "([0-9a-zA-Z][0-9a-zA-Z])?"
        + "[0-9a-zA-Z]\/?((//?)"
    // + "|(/[0-9a-zA-Z_!~*'().;?:@&=+$,%#-]+)+/?)$";
    return !(this.optional(element) || (tel.match(value)));
}, "请填写正确的地址");


/**
 * 去除空格
 */
jQuery.validator.addMethod("strTrim", function (value, element) {
    var result = value.replace(/(^\s*)|(\s*$)/, "");
    return this.optional(element) || result;
}, "去除字符串多余空格");


/**
 * 表达式输入验证，只能输入+,-,*,/,(,).和数字
 */
jQuery.validator.addMethod("expr", function (value, element) {
    var tel = /^([+-\\*\\/\\(\\)]|\w)+$/;
    var result = tel.test(value);
    return this.optional(element) || result;
}, "请填写正确的算术表达式");


//验证第二个数必须比第一个数大
jQuery.validator.addMethod("compareAmount",
    function (value, element, param) {
        var parameterValue = $(param).val();
        if ($.trim(parameterValue) == "" || $.trim(value) == "") {
            return true;
        }
        try {
            var startPrice = parseFloat(parameterValue);
            var endPrice = parseFloat(value);
            return endPrice >= startPrice;
        } catch (e) {
            return false;
        }
    },
    "结束商品价格必须大于起始商品价格"
);

//验证元素为空
jQuery.validator.addMethod("isEmptyelement",
    function (value, element, param) {
        var parameterValue = $('#'+param).val();
        console.info(parameterValue);
        if(parameterValue==null||parameterValue=='-1'||parameterValue==''){
            return false;
        }
        return true;
    },
    "请先选择车场"
);



jQuery.validator.addMethod("validateNo",
    function (value, element, params) {
        var parameterValue = $('#'+params[0]).val();
        var result1=false;
        if(value==null||value==''){
            return this.optional(element)
        }
        var myurl=params[1];
        $.ajax({
            url: myurl,
            async:false,
            type: "get",
            dataType: "json",
            data: { parkId: parameterValue,
                carportNo:value},
            success: function(data) {
                if(data.data==='true'){
                    console.info(data);
                    console.info(0);
                    result1=true;
                }
            }
        });
        console.info(result1);
        return result1;
    },
    "车位编号在该车场已被使用"
);

jQuery.validator.addMethod("validatechpoint",
    function (value, element, params) {
        var parameterValue = $('#'+params[0]).val();
        var result1=false;
        if(value==null||value==''){
            return this.optional(element)
        }
        var myurl=params[1];
        $.ajax({
            url: myurl,
            async:false,
            type: "get",
            dataType: "json",
            data: { chpNo: parameterValue,
                    chpslot:value},
            success: function(data) {
                if(data.data=='true'){
                    result1=true;
                }
            }
        });
        console.info(result1);
        return result1;
    },
    "车位编号在该车场已被使用"
);

