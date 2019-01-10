$(function() {
    $(".page-header-menu").html(
        '<div class="container">' +
        '   <div class="hor-menu">' +
        '       <ul class="nav navbar-nav">' +
        '           <li class="menu-dropdown classic-menu-dropdown publicBenefit home">' +
        '                <a href="/page/front/index/index.html"> 首页 ' +
        '                <span class="arrow"></span></a>' +
        '            </li>' +
        '<li class="menu-dropdown classic-menu-dropdown dgnd ">' +
        '      <a href="javascript:;"> 大国农道' +
        '<span class="arrow"></span>' +
        '      </a>' +
        '      <ul class="dropdown-menu pull-left" style="min-width:120px">' +
        '           <li class=" ">' +
        '                <a href="/page/front/dgnd/greenCross.html?type=lianzhong" class="nav-link nav-toggle ">' +
        '                     农道联众' +
        // '                     <span class="arrow"></span>'+
        '                </a>' +
        '           </li>' +
        '           <li class=" lvshizi">' +
        '                <a href="/page/front/dgnd/greenCross.html?type=lvshizi" class="nav-link nav-toggle ">' +
        '                     绿十字' +
        // '                     <span class="arrow"></span>'+
        '                </a>' +
        '           </li>' +

        '        </ul>' +
        '</li>' +
        '<li class="menu-dropdown classic-menu-dropdown systeMcountryConstruction ">' +
        '      <a href="javascript:;"> 系统乡建' +
        '<span class="arrow"></span>' +
        '      </a>' +
        '      <ul class="dropdown-menu pull-left" style="min-width:120px">' +
        '           <li class=" ">' +
        '                <a href="/page/front/ruralConstruction/software.html" class="nav-link nav-toggle ">' +
        '                     乡建内容' +
        // '                     <span class="arrow"></span>'+
        '                </a>' +
        '           </li>' +
        '           <li class=" ">' +
        '                <a href="/page/front/ruralConstruction/software.html" class="nav-link nav-toggle ">' +
        '                     合作流程' +
        // '                     <span class="arrow"></span>'+
        '                </a>' +
        '           </li>' +
        '           <li class=" ">' +
        '                <a href="/page/front/ruralConstruction/hardware.html" class="nav-link nav-toggle ">' +
        '                     合作机构' +
        // '                     <span class="arrow"></span>'+
        '                </a>' +
        '           </li>' +
        '        </ul>' +
        '</li>' +
        '<li class="menu-dropdown classic-menu-dropdown case ">' +
        '<a href="javascript:;"> 落地案例' +
        '<span class="arrow"></span>' +
        '</a>' +
        '<ul class="dropdown-menu pull-left" style="min-width:120px">' +
        '<li class="dropdown-submenu ">' +
        '<a href="javascript:;" class="nav-link nav-toggle ">' +
        '四川省' +
        '<span class="arrow"></span>' +
        '</a>' +
        '<ul class="dropdown-menu">' +
        '<li class=" ">' +
        '<a href="/page/front/case/case.html?type=daiweicun" class="nav-link ">' +
        '戴维村 </a>' +
        '</li>' +
        '<li class=" ">' +
        '<a href="/page/front/case/case.html?type=xueshancun" class="nav-link ">' +
        '雪山村</a>' +
        '</li>' +
        '</ul>' +
        '</li>' +
        '<li class="dropdown-submenu ">' +
        '<a href="javascript:;" class="nav-link nav-toggle ">' +
        '湖北省' +
        '<span class="arrow"></span>' +
        '</a>' +
        '<ul class="dropdown-menu">' +
        '<li class=" ">' +
        '<a href="/page/front/case/case.html?type=wangtaicun"  class="nav-link ">' +
        '王台村 </a>' +
        '</li>' +
        '<li class=" ">' +
        '<a href="/page/front/case/case.html?type=taoyuancun" class="nav-link ">' +
        '桃源村 </a>' +
        '</li>' +
        '<li class=" ">' +
        '<a href="/page/front/case/case.html?type=yanhecun" class="nav-link ">' +
        '堰河村 </a>' +
        '</li>' +
        '<li class=" ">' +
        '<a href="/page/front/case/case.html?type=yingtao" class="nav-link ">' +
        '樱桃沟村 </a>' +
        '</li>' +
        '</ul>' +
        '</li>' +
        '<li class="dropdown-submenu ">' +
        '<a href="javascript:;" class="nav-link nav-toggle ">' +
        ' 河南省' +
        '<span class="arrow"></span>' +
        '</a>' +
        '<ul class="dropdown-menu">' +
        '<li class=" ">' +
        '<a href="/page/front/case/case.html?type=haotangcun" class="nav-link ">' +
        '郝堂村 </a>' +
        '</li>' +
        '</ul>' +
        '</li>' +
        '</ul>' +
        '</li>' +
        '<li class="menu-dropdown classic-menu-dropdown engineering ">' +
        '      <a href="javascript:;"> 项目统筹' +
        '<span class="arrow"></span>' +
        '      </a>' +
        '      <ul class="dropdown-menu pull-left" style="min-width:120px">' +
        '           <li class=" lvshizi">' +
        '                <a href="/page/front/constructionUnit/constructionUnit.html" class="nav-link nav-toggle ">' +
        '                     工程管理' +
        // '                     <span class="arrow"></span>'+
        '                </a>' +
        '           </li>' +
        '           <li class=" ">' +
        '                <a href="/page/front/constructionUnit/constructionUnit.html" class="nav-link nav-toggle ">' +
        '                     施工单位' +
        // '                     <span class="arrow"></span>'+
        '                </a>' +
        '           </li>' +
        '           <li class=" ">' +
        '                <a href="/page/front/constructionUnit/constructionUnit.html" class="nav-link nav-toggle ">' +
        '                     工程物料' +
        // '                     <span class="arrow"></span>'+
        '                </a>' +
        '           </li>' +
        '        </ul>' +
        '</li>' +
        '<li class="menu-dropdown classic-menu-dropdown thought ">' +
        '      <a href="javascript:;"> 理论思想' +
        '<span class="arrow"></span>' +
        '      </a>' +
        '      <ul class="dropdown-menu pull-left" style="min-width:120px">' +
        '           <li class=" lvshizi">' +
        '                <a href="/page/front/theory/specialColumn.html" class="nav-link nav-toggle ">' +
        '                     特邀专栏' +
        // '                     <span class="arrow"></span>'+
        '                </a>' +
        '           </li>' +
        '           <li class=" ">' +
        '                <a href="/page/front/theory/publishedWorks.html" class="nav-link nav-toggle ">' +
        '                     出版著作' +
        // '                     <span class="arrow"></span>'+
        '                </a>' +
        '           </li>' +
        '        </ul>' +
        '</li>' +
        '<li class="menu-dropdown classic-menu-dropdown specialist">' +
        '<a>' +
        '专家团队' +
        '<span class="arrow"></span>'+
        '</a>' +
        '      <ul class="dropdown-menu pull-left" style="min-width:120px">' +
        '           <li class=" lvshizi">' +
        '                <a href="/page/front/ruralConstruction/hardware.html" class="nav-link nav-toggle ">' +
        '                     硬件团队' +
        // '                     <span class="arrow"></span>'+
        '                </a>' +
        '           </li>' +
        '           <li class=" ">' +
        '                <a href="/page/front/ruralConstruction/software.html" class="nav-link nav-toggle ">' +
        '                     软件团队' +
        // '                     <span class="arrow"></span>'+
        '                </a>' +
        '           </li>' +
        '           <li class=" ">' +
        '                <a href="/page/front/ruralConstruction/software.html" class="nav-link nav-toggle ">' +
        '                     运营团队' +
        // '                     <span class="arrow"></span>'+
        '                </a>' +
        '           </li>' +
        '        </ul>' +
        '</li>' +
        '<li class="menu-dropdown classic-menu-dropdown publicBenefit ">' +
        '<a href="/page/front/publicBenefit/publicBenefit.html">' +
        ' 农道公益基金会' +
        // '<span class="arrow"></span>'+
        '</a>' +

        '</li>' +
        '<li class="menu-dropdown classic-menu-dropdown contactUS">' +
        '<a href="/page/front/contactUS/contactUS.html">' +
        ' 联系我们' +
        // '<span class="arrow"></span>'+
        '</a>' +
        '</li>' +
        '</ul>' +
        '</div>' +
        '</div>'
    )

})