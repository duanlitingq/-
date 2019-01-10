    
//CSS+jquery(JQ)table表格隔行变色_隔行换色特效
	
$(document).ready(function(){ //这个就是传说的ready    
$(".stripe tr").mouseover(function(){    
   //如果鼠标移到class为stripe的表格的tr上时，执行函数    
  $(this).addClass("over");}).mouseout(function(){    
		//给这行添加class值为over，并且当鼠标一出该行时执行函数    
		$(this).removeClass("over");}) //移除该行的class    
$(".stripe tr:even").addClass("alt");    
//给class为stripe的表格的偶数行添加class值为alt 
//www.divcss5.com 整理特效 
}); 


$(document).ready(function(){});       
function hiden(){         
$("#divObj").hide();//hide()函数,实现隐藏,括号里还可以带一个时间参数(毫秒)例如hide(2000)以2000毫秒的速度隐藏,还可以带slow,fast         
}       
function slideToggle(){         
$("#divObj").slideToggle(2000);//窗帘效果的切换,点一下收,点一下开,参数可以无,参数说明同上         
}       
function show(){         
$("#divObj").show();//显示,参数说明同上         
}       
function toggle(){         
$("#divObj").toggle();//显示隐藏切换,参数可以无,参数说明同上         
         
}       
function slide(){         
$("#divObj").slideDown();//窗帘效果展开         
}       
     

//全选
function selectAll(obj){
	for(var i = 0;i<obj.elements.length;i++)
	if(obj.elements[i].type == "checkbox")
	obj.elements[i].checked = true;
}
//反选
function selectOther(obj){
	for(var i = 0;i<obj.elements.length;i++)
	if(obj.elements[i].type == "checkbox" ){
		if(!obj.elements[i].checked)
			obj.elements[i].checked = true;
		else
			obj.elements[i].checked = false;

	}
}
//取消全部
function clearAll(obj){
	for(var i = 0;i<obj.elements.length;i++)
	if(obj.elements[i].type == "checkbox")
	obj.elements[i].checked = false;
}

/**
 * 判断是否为空
 * @param obj
 * @returns {boolean}
 */
function isNull(obj){
	var result = true;
	var type = typeof(obj);
	/*undefined or null return false*/
	if(type == "undefined"
			|| obj == null){
		result = true;
	}
	/*type is string */
	else if (type == "string"){
		obj = $.trim(obj);
		if( obj==""
				|| obj == "undefined"){
			result = true;
		}else {
			result = false;
		}
	}
	/*other object */
	else{
		result = false;
	}
	return result;
}