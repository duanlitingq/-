package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dao.SysRoleMapper;
import com.yunchao.hsh.dao.SysRoleMenuMapper;
import com.yunchao.hsh.dao.SysUserMapper;
import com.yunchao.hsh.dto.resp.SysRoleResp;
import com.yunchao.hsh.dto.resp.SysUserResp;
import com.yunchao.hsh.model.SysRole;
import com.yunchao.hsh.model.SysRoleMenu;
import com.yunchao.hsh.model.SysUser;
import com.yunchao.hsh.service.ISysRoleService;
import com.yunchao.hsh.utils.MnUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yunchao.hsh.utils.superdir.sub.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangqi on 2018/4/25
 */
@Service
@Transactional
public class SysRoleServiceImpl implements ISysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    public Logger logger =Logger.getLogger("SysRoleServiceImpl");

    public void save(SysRole sysRole, String ids) {
        sysRoleMapper.insert(sysRole);
        Long id = sysRole.getId();
        JSONArray jsonArray = JSONArray.fromObject(ids);
        if(jsonArray.size() > 0){
            int size = jsonArray.size();
            for (int i = 0; i < size; i++) {
                Object obj = jsonArray.get(i);
                JSONObject jsonObject = JSONObject.fromObject(obj);
                long first_id = jsonObject.getLong("first_id");
                String childs = jsonObject.getString("childs");
                SysRoleMenu srm = new SysRoleMenu();
                srm.setMenuId(first_id);
                srm.setRoleId(id);
                srm.setChilds(childs);
                sysRoleMenuMapper.save(srm);
            }
        }
    }

    public int update(SysRole sysRole, String ids) {
        Long id = sysRole.getId();
        sysRoleMenuMapper.delete(id);
        JSONArray jsonArray = JSONArray.fromObject(ids);
        if(jsonArray.size() > 0){
            int size = jsonArray.size();
            for (int i = 0; i < size; i++) {
                Object obj = jsonArray.get(i);
                JSONObject jsonObject = JSONObject.fromObject(obj);
                long first_id = jsonObject.getLong("first_id");
                String childs = jsonObject.getString("childs");
                SysRoleMenu srm = new SysRoleMenu();
                srm.setMenuId(first_id);
                srm.setRoleId(id);
                srm.setChilds(childs);
                sysRoleMenuMapper.save(srm);
            }
        }
        return sysRoleMapper.update(sysRole);
    }

    public SysRoleResp getById(Long id) { return sysRoleMapper.findById(id);}

    //根据角色名查询角色信息
    @Override
    public SysRoleResp getByName(String name, Long id) {
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("id",id);
        return sysRoleMapper.findByName(map);
    }

    public List<SysRoleResp> getList() {
        return sysRoleMapper.findList();
    }

    //对分页处理 分页模板
    @Override
    public PageInfo<SysRoleResp> getPage(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysRoleResp> list = sysRoleMapper.findPage(params);
        PageInfo<SysRoleResp> pageInfo = new PageInfo<SysRoleResp>(list);
        return pageInfo;
    }

    //对密码进行初始化
    public Result updatePassword(SysUser sysUser) {
        Result result = new Result();
        try{
            SysUserResp sysUserResp = new SysUserResp();
            sysUserResp.setId(sysUser.getId());
            sysUserResp.setPassword(MnUtil.md5("123456"));
            int rows = sysUserMapper.initPassword(sysUserResp);
            if (rows>=1){
                result.setSuccess(true);
                result.setMessage("用户密码初始化成功！密码为123456");
            }else{
                result.setSuccess(false);
                result.setMessage("密码初始化失败！");
            }
        }catch (Exception e){
            result.setF("") ;
            logger.error("对密码进行初始化！" + e.getMessage());
        }
            return result;
        }

    @Override
    public List<SysRoleMenu> getRoleMenu(Long roleId) {
        return sysRoleMenuMapper.getRoleMenu(roleId);
    }

}
