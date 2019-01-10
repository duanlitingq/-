package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dao.MenuMapper;
import com.yunchao.hsh.dao.SysRoleMapper;
import com.yunchao.hsh.dao.SysRoleMenuMapper;
import com.yunchao.hsh.dao.SysUserMapper;
import com.yunchao.hsh.dto.resp.MenuResp;
import com.yunchao.hsh.dto.resp.SysUserResp;
import com.yunchao.hsh.model.Menu;
import com.yunchao.hsh.model.SysRoleMenu;
import com.yunchao.hsh.service.IMenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/5/15.
 */
@Service
@Transactional
public class MenuServiceImpl implements IMenuService{
    @Autowired
    private MenuMapper menuMapper ;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public void save(Menu menu) {
        menuMapper.insert(menu);
    }

    @Override
    public void update(Menu menu) {
        menuMapper.update(menu);
    }

    @Override
    public MenuResp getById(Long id) {
        return menuMapper.findById(id);
    }

    @Override
    public List<MenuResp> getList(Map<String, Object> params) {
        return menuMapper.findPage(params) ;
    }

    @Override
    public PageInfo<MenuResp> getPage(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize) ;
        List<MenuResp> list = menuMapper.findPage(params) ;
        PageInfo<MenuResp> pageInfo = new PageInfo<MenuResp>(list) ;
        return pageInfo;
    }

    @Override
    public void batchUpdateState(List<Menu> advertisementList, Integer status) {
        for (int i = 0 ; i < advertisementList.size(); i ++){
            Menu menu = advertisementList.get(i) ;
            menu.setStatus(status);
            update(menu);
        }
    }

    /**
     * 获取左侧菜单
     * @param userId
     * @return
     */
    @Override
    public List<Menu> getLeftMenu(Long userId) {
        List<Menu> menus = new ArrayList<>();
        //获取用户
        SysUserResp user = sysUserMapper.findById(userId);
        Long roleId = user.getRoleId();
        //根据用户角色获取角色所关联的菜单
        List<SysRoleMenu> roleMenu = sysRoleMenuMapper.getRoleMenu(roleId);
        if(roleMenu.size() > 0){
            int len = roleMenu.size();
            for (int i = 0; i < len; i++) {
                SysRoleMenu srm =  roleMenu.get(i);
                Long menuId = srm.getMenuId();
                MenuResp mr = menuMapper.findById(menuId);
                if(mr.getStatus() == 1){
                    String childs = srm.getChilds();
                    if(!StringUtils.isBlank(childs)){
                        String[] idsArr = childs.split(",");
                        List<MenuResp> childss = new ArrayList<>(idsArr.length);
                        for (int j = 0; j < idsArr.length; j++) {
                            String strId = idsArr[j];
                            MenuResp mrs = menuMapper.findById(Long.valueOf(strId));
                           if(mrs.getStatus() == 1){
                               childss.add(mrs);
                           }
                        }
                        mr.setChilds(childss);
                        menus.add(mr);
                    }
                }
            }
        }
        return menus;
    }
}
