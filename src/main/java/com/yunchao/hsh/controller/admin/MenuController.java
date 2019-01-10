package com.yunchao.hsh.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.MenuResp;
import com.yunchao.hsh.model.Menu;
import com.yunchao.hsh.service.IMenuService;
import com.yunchao.hsh.utils.Constant;
import com.yunchao.hsh.utils.ObjectUtils;
import com.yunchao.hsh.utils.ParamUtils;
import com.yunchao.hsh.utils.superdir.sub.Result;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/5/15.
 */
@Controller
@RequestMapping("/page/admin/menu")
public class MenuController {
    Logger logger = Logger.getLogger("MenuController.class") ;

    @Autowired
    private IMenuService menuService ;

    //分页，搜索获取菜单列表
    @RequestMapping("getPage")
    @ResponseBody
    public PageInfo<MenuResp> getPage(HttpServletRequest request,Integer status,Integer level){
        try {
            int pageNum = ParamUtils.getIntParameter(request,"pageNum",1);
            int pageSize = ParamUtils.getIntParameter(request,"pageSize",10) ;
            Map<String,Object> params = new HashMap<String,Object>();
            String name = ParamUtils.getParameter(request,"name",""); //菜单名
            if(status != null){
                params.put("status",status);
            }
            if(level != null){
                params.put("level",level);
            }
            params.put("name",name);
            PageHelper.orderBy("level asc,sort desc");
            PageInfo<MenuResp> pageInfo = menuService.getPage(params,pageNum,pageSize) ;
            return pageInfo ;
        }catch (Exception e){
            logger.error("菜单分页列表异常！" + e.getMessage());
        }
        return null ;
    }

    //获取菜单列表
    @RequestMapping("getList")
    @ResponseBody
    public List<MenuResp> getList(Long parentId){
        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("parentId",parentId);
            params.put("status",1);
            List<MenuResp> list = menuService.getList(params) ;
            return list ;
        }catch (Exception e){
            logger.error("菜单列表异常 " + e.getMessage());
        }
        return null ;
    }

    //增加菜单
    @RequestMapping("doAdd")
    @ResponseBody
    public Result doAdd(Menu menu){
        Result result = new Result() ;
        try {
            if(StringUtils.isBlank(menu.getPageUrl())){
                menu.setPageUrl("#"); //页面链接为空 设置锚点
            }
            if(ObjectUtils.isEmpty(menu.getSort())){
                menu.setSort(0);
            }
            menuService.save(menu);
            result.setS("") ;
        }catch (Exception e){
            logger.error("菜单列表异常 " + e.getMessage());
            result.setF("") ;
        }
        return result ;
    }

    //根据id获取菜单数据
    @RequestMapping("getById")
    @ResponseBody
    public MenuResp getById(Long id){
        try {
            return menuService.getById(id);
        }catch (Exception e){
            logger.error("增加菜单异常  " + e.getMessage());
        }
        return null ;
    }

    //修改菜单
    @RequestMapping("doUpdate")
    @ResponseBody
    public Result doUpdate(Menu menu) {
        Result result = new Result() ;
        try {
            if(StringUtils.isBlank(menu.getPageUrl())){
                menu.setPageUrl("#"); //页面链接为空 设置锚点
            }
            if(ObjectUtils.isEmpty(menu.getSort())){
                menu.setSort(0);
            }
            menuService.update(menu);
            result.setS("") ;
        } catch (Exception e) {
            logger.error("修改菜单异常  " + e.getMessage());
            result.setF("") ;
        }
        return result;
    }

    //增加菜单
    @RequestMapping("doAddSubMenu")
    @ResponseBody
    public Result doAddSubMenu(Menu menu){
        Result result = new Result() ;
        try {
            Menu parentMenu = menuService.getById(menu.getParentId()) ; //获取父菜单信息
            Integer parentLeven = parentMenu.getLevel() ; //父菜单等级
            if(parentLeven>=Constant.THREE_LEVEL){
                result.setF("暂不支持添加4级菜单") ;
                return  result;
            }
            menu.setLevel(parentLeven + 1);
            if(StringUtils.isBlank(menu.getPageUrl())){
                menu.setPageUrl("#"); //页面链接为空 设置锚点
            }
            menuService.save(menu);
            result.setS("") ;
        }catch (Exception e){
            logger.error("菜单列表异常 " + e.getMessage());
            result.setF("") ;
        }
        return result ;
    }


    @RequestMapping("updateState")
    @ResponseBody
    public Result updateState(Long id,Integer status){
        Result result = new Result();
        if(ObjectUtils.isEmpty(id) || ObjectUtils.isEmpty(status)){
            logger.info("参数为空！ id = " + id +" status = " + status);
            return result.setF("") ;
        }
        try {
            Menu menu = menuService.getById(id) ;
            if(ObjectUtils.isEmpty(menu)){
                return result.setF("未查询到菜单数据！");
            }
            menu.setStatus(status);
            menuService.update(menu);
            return result.setS("") ;
        }catch (Exception e){
            logger.error("更新菜单状态异常 == " + e.getMessage());
            result.setF("") ;
        }
        return result ;
    }

    @RequestMapping("batchUpdateState")
    @ResponseBody
    public Result batchUpdateState(String ids ,Integer status){
        Result result = new Result();
        try {
            String[] str = ids.split(",") ;
            if(ObjectUtils.isEmpty(status)){
                logger.info("状态为空 ！");
                return result.setF("");
            }
            if(ObjectUtils.isEmpty(str)){
                logger.info("菜单ID为空  ！");
                return result.setF("");
            }
            List<Menu> list = new ArrayList<Menu>();
            for (int i = 0 ; i < str.length ; i ++){
                Menu menu = menuService.getById(Long.parseLong(str[i]));
                if(ObjectUtils.isNotEmpty(menu)){
                    list.add(menu) ;
                }else {
                    logger.info("未查询到菜单数据 终止操作！");
                    return result.setF("未查询到菜单数据 终止操作！");
                }
            }
            menuService.batchUpdateState(list,status);
            return result.setS("") ;
        }catch (Exception e){
            logger.error("批量修改状态异常 == " + e.getMessage());
            result.setF("") ;
        }
        return result ;
    }

    @RequestMapping("getLeftMenu")
    @ResponseBody
    public List<Menu> getLeftMenu(Long userId){

        List<Menu> list = menuService.getLeftMenu(userId);

        return list;
    }
}
