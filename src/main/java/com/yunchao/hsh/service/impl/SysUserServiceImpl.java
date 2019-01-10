package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dao.SysUserMapper;
import com.yunchao.hsh.dto.resp.SysUserResp;
import com.yunchao.hsh.model.SysUser;
import com.yunchao.hsh.service.ISysUserService;
import com.yunchao.hsh.utils.ObjectUtils;
import com.yunchao.hsh.utils.superdir.sub.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by admin on 2018/4/25.
 */
@Service
@Transactional
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    public Logger logger =Logger.getLogger("SysUserServiceImpl");

    public void save(SysUser sysUser) {
        sysUserMapper.insert(sysUser) ;
    }

    public int update(SysUserResp  sysUserResp) {
       return sysUserMapper.update(sysUserResp);
    }

    public SysUserResp getById(Long id) {
        if(id!=null){
            return sysUserMapper.findById(id);
        }else {
        System.out.printf("用户的id之为空");
    }
       return  null;
    }

    public List<SysUserResp> getList() {
        return sysUserMapper.findList();
    }

    @Override
    public SysUserResp getByUserName(String userName) {
        return sysUserMapper.findByName(userName);
    }


    public PageInfo<SysUserResp> getPage(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize) ;
        List<SysUserResp> list = sysUserMapper.findPage(params) ;
        PageInfo<SysUserResp> pageInfo = new PageInfo<SysUserResp>(list) ;
        return pageInfo;
    }




    //判断帐号和密码的正确性
    public Result cheakName(String userName, String password)  {
        Result result = new Result();
        if (userName!=null &&userName!="" && password != null &&password!="") {
            SysUserResp userResp = sysUserMapper.findByName(userName);
            if(ObjectUtils.isEmpty(userResp)){
                return result.setF("输入的用户名不存在");
            }
            if (userResp.getUserName().equals(userName) && userResp.getPassword().equals(password)) {

                result.setSuccess(true);
                result.setMessage("登录成功!");
                Map<String,Object> data = new HashMap<String, Object>();
                Long id = userResp.getId();
                String userName1 =userResp.getUserName();
                data.put("id",id);
                data.put("userName",userName1);
                result.setData(data);
                return result;
            } else{
                result.setSuccess(false);
                result.setMessage("用户名或者密码不正确!");
                return result;
            }
        }else{
            result.setSuccess(false);
            result.setMessage("输入用户名不存在");
            return result;
        }
    }

    //判断用户名唯一不可重复
    public Result getNameUnique(String userName) {
        Result result = new Result();
        try {
            if(userName!=null && userName!=""){
                SysUserResp sysUserResp = sysUserMapper.checkNameUnique(userName);
                if(sysUserResp==null){
                    result.setSuccess(true);
                    result.setMessage("用户名可用!");
                    result.setData(userName);
                    return result;
                }else {
                    result.setSuccess(false);
                    result.setMessage("该用户已经注册");
                    result.setData(userName);
                    return result;
                }
            }else{
                result.setSuccess(false);
                result.setMessage("用户名不能为空");
                result.setData(userName);
                return result;
            }
        }catch (Exception e){
            logger.error("判断用户名唯一不可重复异常！" + e.getMessage() );
        }
        return result ;
    }
    //判断手机号唯一不可重
    public Result getPhoneUnique(String phone) {
        Result result = new Result();
        try {
            if (phone != null) {
                SysUserResp sysUserResp = sysUserMapper.checkPhoneUnique(phone);
                if (sysUserResp == null) {
                    result.setSuccess(true);
                    result.setMessage("手机号可注册!");
                    result.setData(phone);
                    return result;
                } else {
                    result.setSuccess(false);
                    result.setMessage("手机号已经注册");
                    result.setData(phone);
                    return result;
                }
            } else {
                result.setSuccess(false);
                result.setMessage("手机号不能为空！");
                result.setData(phone);
                return result;
            }
        }catch (Exception e){
            logger.error("判断手机号唯一不可重复异常！" + e.getMessage() );
        }
        return result;
    }

    //权限id更新操作
    public int updateAdmin(SysUserResp sysUserResp) {

        return sysUserMapper.updateAdmin(sysUserResp);
    }

    //密码的修改
    public int updatePassword(SysUser sysUser){
        return  sysUserMapper.updatePassword(sysUser);
    }

    //对锁定状态的修改
    public Result updateUserLock(SysUserResp sysUser) {
        Result result = new Result();
        SysUserResp sysUserResp = sysUserMapper.findById(sysUser.getId());
        if("0".equals(sysUser.getUserLock())){
            sysUserResp.setId(sysUser.getId());
            sysUserResp.setUserLock(sysUser.getUserLock());
            int rows = sysUserMapper.updateUserLock(sysUserResp);
            if(rows>=1){
                result.setSuccess(true);
                result.setMessage("未锁定");
                result.setData(0);
            }else {
                result.setSuccess(false);
                result.setMessage("已锁定");
                result.setData(1);
            }
        }else if("1".equals(sysUser.getUserLock())){
            sysUserResp.setId(sysUser.getId());
            sysUserResp.setUserLock(sysUser.getUserLock());
            int rows = sysUserMapper.updateUserLock(sysUserResp);
            if(rows>=1){
                result.setSuccess(true);
                result.setMessage("已锁定");
                result.setData(1);
            }else {
                result.setSuccess(false);
                result.setMessage("未锁定");
                result.setData(0);
            }
        }
        return  result;
    }

    //根据权限的id查询用户
    public List<SysUserResp> getRoleIDUser(Long id) {
        return sysUserMapper.findByRoleIDUser(id);
    }

    public int updatePassword(SysUserResp sysUserResp) {
        return sysUserMapper.initPassword(sysUserResp);
    }

}
