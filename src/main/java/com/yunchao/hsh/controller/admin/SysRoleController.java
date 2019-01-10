package com.yunchao.hsh.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.SysRoleResp;
import com.yunchao.hsh.dto.resp.SysUserResp;
import com.yunchao.hsh.model.SysRole;
import com.yunchao.hsh.model.SysRoleMenu;
import com.yunchao.hsh.model.SysUser;
import com.yunchao.hsh.service.ISysRoleService;
import com.yunchao.hsh.service.ISysUserService;
import com.yunchao.hsh.utils.ParamUtils;
import com.yunchao.hsh.utils.superdir.sub.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangqi on 2018/4/25
 */
@Controller
@RequestMapping("/page/admin/sysRole/")
public class SysRoleController {

    Logger logger = Logger.getLogger("SysRoleController");
    //@Autowired(required = false)//要求依赖对象允许null值
    @Autowired(required = false)
    private ISysRoleService sysRoleService;
    @Autowired(required = false)
    private ISysUserService sysUserService;

    //查询所有
    //localhost:8080/page/admin/sysRole/getList
    @RequestMapping("getList")
    @ResponseBody
    public List<SysRoleResp> getList() {
        List<SysRoleResp> list = sysRoleService.getList();
        return list;
    }

    //localhost:8080/page/admin/sysRole/getPage?getPage=1&pageSize=3
    //分页处理 模板
    @RequestMapping("getPage")
    @ResponseBody
    public PageInfo<SysRoleResp> getPage(HttpServletRequest request) {
        try {
            int pageNum = ParamUtils.getIntParameter(request, "pageNum", 1);
            int pageSize = ParamUtils.getIntParameter(request, "pageSize", Integer.MAX_VALUE);
            Map<String, Object> params = new HashMap<String, Object>();
            String name = ParamUtils.getParameter(request, "name", "");//角色名
            params.put("name", name);
            PageHelper.orderBy("id desc");
            PageInfo<SysRoleResp> pageInfo = sysRoleService.getPage(params, pageNum, pageSize);
            return pageInfo;
        } catch (Exception e) {
            logger.error("广告分页列表异常！" + e.getMessage());
        }
        return null;
    }

    //根据主ID查询
    //localhost:8080/page/admin/sysRole/getById?id=1
    @RequestMapping("getById")
    @ResponseBody
    public SysRoleResp getById(HttpServletRequest request, Long id) {
        SysRoleResp sysRoleResp = sysRoleService.getById(id);
        return sysRoleResp;
    }

    //添加权限用户
    //localhost:8080/page/admin/sysRole/doAdd
    @RequestMapping("doAdd")
    @ResponseBody
    public Result doAdd(SysRole sysRole, String ids) {
        Result result = new Result();
        String name = sysRole.getName();
        if (StringUtils.isNotBlank(name)) {
            //判断是否与数据已存在的角色名相等
            if (sysRoleService.getByName(name, null) == null) {
                if(!StringUtils.isBlank(ids)){
                    sysRoleService.save(sysRole,ids);
                    result.setSuccess(true);
                    result.setMessage("已成功添加角色!");
                }else{
                    result.setSuccess(false);
                    result.setMessage("请选择菜单!");
                }
                return result;
            } else {
                result.setSuccess(false);
                result.setMessage("添加失败，角色名已存在!");
                return result;
            }
        } else {
            result.setSuccess(false);
            result.setMessage("添加失败，角色不能为空!");
            return result;
        }
    }

    //localhost:8080/page/admin/sysRole/getRoleIdUser?roleID=2
    //根据角色的id查询当前角色下的用户信息
    @RequestMapping("getRoleIdUser")
    @ResponseBody
    public Result getRoleIdUser(Long roleID) {
        Result result = new Result();
        if (roleID != null) {
            List<SysUserResp> list = sysUserService.getRoleIDUser(roleID);
            if (!list.isEmpty()) {
                result.setSuccess(true);
                result.setMessage("查询成功!");
                result.setData(list);
                return result;
            }
        } else {
            result.setSuccess(false);
            //System.out.println("角色的ID为空异常，无法查询!");
            result.setMessage("角色的ID为空异常，无法查询!!");
            return result;
        }
        return result;
    }

    //对角色修改
    //localhost:8080/page/admin/sysRole/doUpdate?id=3&name=heheheh
    @RequestMapping(value = "doUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Result doUpdate(SysRoleResp sysRole,String ids) {
        //System.out.printf(sysRole.getName());
        Result result = new Result();
        try {
            String name = sysRole.getName();
            SysRoleResp sysRoleResp = sysRoleService.getById(sysRole.getId());
            if (StringUtils.isNotBlank(name)) {
                if (sysRoleService.getByName(name,sysRoleResp.getId()) == null) {
                    sysRoleResp.setName(name);
                    if(!StringUtils.isBlank(ids)){
                        int rows = sysRoleService.update(sysRoleResp,ids);
                        if (rows >= 1) {
                            result.setSuccess(true);
                            result.setMessage("角色修改成功!");
                        }
                    }else{
                        result.setSuccess(false);
                        result.setMessage("请选择菜单数据!");
                    }
                    return result;
                } else {
                    result.setSuccess(false);
                    result.setMessage("失败，修改的角色名已存在!");
                    return result;
                }
            } else {
                result.setSuccess(false);
                result.setMessage("修改的角色不能为空!");
                return result;
            }
        } catch (Exception e) {
            result.setF("");
            logger.error("修改角色的异常！" + e.getMessage());
        }
        return result;
    }

    //localhost:8080/page/admin/sysRole/initPassword?id=2
    //初始化用户的密码
    @RequestMapping("initPassword")
    @ResponseBody
    public Result initPassword(SysUser sysUser) {
        return sysRoleService.updatePassword(sysUser);
    }

    @RequestMapping("getRoleMenuIds")
    @ResponseBody
    public List<SysRoleMenu> getRoleMenuIds(HttpServletRequest request, Long roleId) {
        List<SysRoleMenu> menu = sysRoleService.getRoleMenu(roleId);
        return menu;
    }

}
