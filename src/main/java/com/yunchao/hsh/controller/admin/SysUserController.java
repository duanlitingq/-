package com.yunchao.hsh.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.SysUserResp;
import com.yunchao.hsh.model.SysUser;
import com.yunchao.hsh.service.ISysUserService;
import com.yunchao.hsh.utils.Constant;
import com.yunchao.hsh.utils.MnUtil;
import com.yunchao.hsh.utils.ObjectUtils;
import com.yunchao.hsh.utils.ParamUtils;
import com.yunchao.hsh.utils.redis.RedisUtil;
import com.yunchao.hsh.utils.superdir.sub.Result;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/4/25.
 */
@Controller
@RequestMapping("/page/admin/sysUser/")
public class SysUserController {
    Logger logger = Logger.getLogger("SysUserController") ;
    @Autowired
    private ISysUserService sysUserService ;

    //localhost:8080/page/admin/sysUser/getPage?pageNum=1&pageSize=3&userName=admin&phone=138
    @RequestMapping("getPage")
    @ResponseBody
    public PageInfo<SysUserResp> getPage(HttpServletRequest request){

        try {
            int pageNum = ParamUtils.getIntParameter(request,"pageNum",1);
            int pageSize = ParamUtils.getIntParameter(request,"pageSize",10) ;
            Map<String,Object> params = new HashMap<String,Object>();

            String userName = ParamUtils.getParameter(request,"keyWord",""); //用户名
            String realName = ParamUtils.getParameter(request,"realName",""); //真实姓名
            String phone = ParamUtils.getParameter(request,"phone",""); //手机
            String roleId = ParamUtils.getParameter(request,"roleId",""); //角色ID
            String createDate_lt = ParamUtils.getParameter(request,"createDate_lt",""); //创建时间大于
            String createDate_gt = ParamUtils.getParameter(request,"createDate_gt",""); //创建时间小于
            String lastLoginDate_lt = ParamUtils.getParameter(request,"lastLoginDate_lt",""); //最后登录时间大于
            String lastLoginDate_gt = ParamUtils.getParameter(request,"lastLoginDate_gt",""); //最后登录时间小于
            createDate_lt= ObjectUtils.toDate(createDate_lt,0);
            createDate_gt= ObjectUtils.toDate(createDate_gt,1);
            lastLoginDate_lt= ObjectUtils.toDate(createDate_lt,0);
            lastLoginDate_gt= ObjectUtils.toDate(createDate_gt,1);

            params.put("userName",userName);
            params.put("realName",realName);
            params.put("phone",phone);
            params.put("roleId",roleId);
            params.put("createDate_lt",createDate_lt);
            params.put("createDate_gt",createDate_gt);
            params.put("lastLoginDate_lt",lastLoginDate_lt);
            params.put("lastLoginDate_gt",lastLoginDate_gt);
            PageHelper.orderBy("id desc");
            PageInfo<SysUserResp> pageInfo = sysUserService.getPage(params,pageNum,pageSize) ;
            return pageInfo ;
        }catch (Exception e){
            logger.error("管理员分页列表异常！" + e.getMessage());
        }
        return null ;
    }

   ///page/admin/sysUser/getById?id=1
    @RequestMapping("getById")
    @ResponseBody
    public SysUserResp getById(HttpServletRequest request ,Long id){
        SysUserResp sysUserResp = sysUserService.getById(id) ;
        return sysUserResp ;
    }

    //localhost:8080/page/admin/sysUser/login?userName=admin&password=1234
    //登录
    @RequestMapping(value = "login",method=RequestMethod.POST)
    @ResponseBody
    public Result login(HttpServletRequest request ,String userName , String password) {
        Result result = new Result();
        try {
            if(StringUtils.isBlank(userName)){
                return result.setF("用户名不能为空！") ;
            }
            if(StringUtils.isBlank(password)){
                return result.setF("密码不能为空！") ;
            }
            SysUserResp sysUserResp = sysUserService.getByUserName(userName) ;
            if(ObjectUtils.isNotEmpty(sysUserResp)){
                String md5Password = MnUtil.md5(password);
             //   System.out.println("md5 ===== " + md5Password);
                if(sysUserResp.getPassword().equals(md5Password)){
                    sysUserResp.setLastLoginDate(new Date());
                    sysUserService.update(sysUserResp);
                    request.getSession().setAttribute(Constant.LOGIN_USER,sysUserResp);
                    result.setS("",sysUserResp) ;
                    result.setMessage("登录成功！") ;
                }else {
                    result.setF("密码不正确!") ;
                }
            }else {
                result.setF("用户名不正确!") ;
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("用户登录异常！");
        }
        return result ;
    }
    //localhost:8080/page/admin/sysUser/logOut
    //退出登录
    @RequestMapping("logOut")
    @ResponseBody
    public Result login(HttpServletRequest request) {
        //System.out.println(request.getSession().getAttribute("loginUser")+"=======");
        request.getSession().removeAttribute(Constant.LOGIN_USER);
        //System.out.println(request.getSession().getAttribute("loginUser")+"&&&&&&&&");
        Result result = new Result();
        result.setMessage("已经安全退出!") ;
        return  result;
    }

    //localhost:8080/page/admin/sysUser/userGetAll
    //获取所有用户信息
    @RequestMapping(value = "userGetAll",method=RequestMethod.GET )
    @ResponseBody
    public List<SysUserResp>  userGetAll(){
        Result result = new Result();
        List<SysUserResp> list = sysUserService.getList();
        return  list;
    }

    //localhost:8080/page/admin/sysUser/addCheckEcho?id=4&roleId=2&userName=wanger&password=123456&realName=xia&phone=13156019905
    //对添加用户名和手机号唯一的验证 验证成功后添加  String userName,String phone,
    @RequestMapping(value = "addCheckEcho",method=RequestMethod.POST )
    @ResponseBody
    public Result addCheckEcho(@RequestBody SysUser sysUser){
        String userName = sysUser.getUserName();
        String phone = sysUser .getPhone();
        Result result = new Result();
            if(StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(userName)) {
                //StringUtil
                if (sysUserService.getNameUnique(userName).isSuccess() && sysUserService.getPhoneUnique(phone).isSuccess()) {
                    sysUser.setCreateDate(new Date());
                    sysUser.setLastLoginDate(new Date());
                    String md5Password = MnUtil.md5(sysUser.getPassword());
                    sysUser.setPassword(md5Password);
                    sysUserService.save(sysUser);
                    result.setSuccess(true);
                    result.setMessage("添加用户成功!");
                   result.setData(userName);
                    return result;
                }else {
                    if(!sysUserService.getNameUnique(userName).isSuccess()){
                        result.setSuccess(false);
                        result.setMessage("该用户已经注册");
                        result.setData(userName);
                        return result;
                    }else if(!sysUserService.getPhoneUnique(phone).isSuccess()){
                        result.setSuccess(false);
                        result.setMessage("手机号已经注册");
                        result.setData(phone);
                        return result;
                    }
                }
            }else{
                result.setSuccess(false);
                result.setMessage("用户名和手机号不能为空!");
                result.setData(sysUser);
                return result;
            }
        return result;
    }

    //localhost:8080/page/admin/sysUser/doUpdate?id=4&roleId=1&userName=wan678er&phone=13156019905
   //{"success":true,"message":"修改成功!","data":{"id":4,"roleId":1,"userName":"wan678er","password":"123","realName":"xipa","phone":"13156019905","createDate":null,"lastLoginDate":null}}
    @RequestMapping(value ="doUpdate",method=RequestMethod.POST)
    @ResponseBody
    public Result doUpdate( SysUser sysUser){
        Result result = new Result();
        try {
            SysUserResp  sysUserResp = sysUserService.getById(sysUser.getId());
            sysUserResp.setPhone(sysUser.getPhone());
            int rows = sysUserService.update(sysUserResp);
                if (rows >= 1){
                    result.setSuccess(true);
                    result.setMessage("修改成功!");
                    return result;
                }else{
                    result.setSuccess(false);
                    result.setMessage("修改失败！");
                    return result;
                }
        }catch (Exception e){
            result.setF("") ;
            logger.error("对用户名的信息进行修改异常！" + e.getMessage());
        }
        return result ;
    }

    //localhost:8080/page/admin/sysUser/doAdminUpdate?id=4&roleId=1
    //{"success":true,"message":"修改成功!","data":null}
    @RequestMapping(value="doAdminUpdate",method=RequestMethod.POST )
    @ResponseBody
    public Result doAdminUpdate(SysUserResp sysUserResp){
        Result result = new Result();
        try {
            int rows = sysUserService.updateAdmin(sysUserResp);
            if (rows >= 1){
                result.setSuccess(true);
                result.setMessage("权限修改成功!");
                return result;
            }else{
                result.setSuccess(false);
                result.setMessage("权限修改失败！");
                return result;
            }
        }catch (Exception e){
            result.setF("") ;
            logger.error("修改用户的权限异常！" + e.getMessage());
        }
        return result ;
    }
    //page/admin/sysUser/getPassword?id=1&oldPassword=123
    //根据用户id查询出密码
    @RequestMapping("getPassword")
    @ResponseBody
    public Result getPassword(Long id,String oldPassword){
        Result result = new Result();
        SysUserResp sysUserResp = sysUserService.getById(id) ;
        oldPassword = MnUtil.md5(oldPassword) ;
        String password = sysUserResp.getPassword();
        if(StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(password)){
            if(oldPassword.equals(password)){
                result.setS("");
            }else {
                result.setF("原密码不正确！");
            }
        }else {
            result.setF("密码不能为空！");
        }
        return result ;
    }

    //密码的修改  ,method=RequestMethod.POST
   // /page/admin/sysUser/doPasswordUpdate?id=1&newPassword=123&oldPassword=12345
   @RequestMapping("doPasswordUpdate")
    @ResponseBody
    public Result doPasswordUpdate(HttpServletRequest request,Long id,String newPassword,String oldPassword){
       Result result = new Result();
       try {
           SysUserResp sysUserResp = sysUserService.getById(id) ;
           String findPassword = sysUserResp.getPassword(); //取到数据库密码
           String inputPassword  = MnUtil.md5(oldPassword); //取出页面的密码
           if(ObjectUtils.isNotEmpty(oldPassword)){
               if(inputPassword.equals(findPassword)){
                   if(ObjectUtils.isNotEmpty(sysUserResp)){
                       sysUserResp.setPassword(MnUtil.md5(newPassword));
                       sysUserService.update(sysUserResp) ;
                       result.setSuccess(true);
                       result.setMessage("密码修改成功!");
                       request.getSession().removeAttribute(Constant.LOGIN_USER);
                       return result;
                   } else{
                       result.setSuccess(false);
                       result.setMessage("未查询到用户信息 密码修改失败！");
                       return result;
                   }
               }else{
                   result.setSuccess(false);
                   result.setMessage("原密码输入错误！");
                   return result;
               }
           }else{
               result.setSuccess(false);
               result.setMessage("原密码不能为空！");
               return result;
           }
       }catch (Exception e){
           result.setF("") ;
           logger.error("修改用户的密码异常！" + e.getMessage());
       }
       return result ;
    }

    //对锁定状态的修改
    // http://localhost:8080//page/admin/sysUser/doUpdateUserLock?id=1&userLock=0
    //{"success":true,"message":"未锁定","data":0}
    @RequestMapping("doUpdateUserLock")
    @ResponseBody
    public Result doUpdateUserLock(SysUserResp sysUser){
        return sysUserService.updateUserLock(sysUser);
    }


}
