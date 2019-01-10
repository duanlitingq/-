package com.yunchao.hsh.controller.admin;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.controller.BaseController;
import com.yunchao.hsh.model.HshActivityWelfare;
import com.yunchao.hsh.service.IActivityWelfareService;
import com.yunchao.hsh.utils.CommonUtil;
import com.yunchao.hsh.utils.ParamUtils;
import com.yunchao.hsh.utils.superdir.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/11/28 15:50
 * @Description:
 * @Email: hkwind959@gmail.com
 */
@Controller
@RequestMapping(value = "/page/admin/welfare/")
public class ActivityWelfareController extends BaseController {

    @Autowired
    private IActivityWelfareService activityWelfareService;

    /**
     * 列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = {"getPage"}, method = RequestMethod.GET)
    @ResponseBody
    public Result findStationList(HttpServletRequest request) {
        int pageNum = ParamUtils.getIntParameter(request, "pageNum", 1);
        int pageSize = ParamUtils.getIntParameter(request, "pageSize", 15);
        PageInfo<HshActivityWelfare> info = this.activityWelfareService.getPage(pageNum, pageSize);
        return Result.ok(info);
    }

    @RequestMapping(value = "{update}", method = RequestMethod.GET)
    @ResponseBody
    public Result updateWelfare() {

        return null;
    }

    @RequestMapping("doAdd")
    public String doAdd(HshActivityWelfare welfare) {
        Result result = new Result();
        try {
            this.activityWelfareService.save(welfare);
            return "activityWelfare/list";
        } catch (Exception e) {
            log.error("添加异常",e.getMessage());
            result.setSuccess(false);
            result.setMessage("添加异常");
        }
        return "activityWelfare/list";
    }

    @RequestMapping("doUpdate")
    public String doUpdate(HttpServletRequest request, HshActivityWelfare welfare,String stationsImg){
        Result result = new Result();
        try {
            stationsImg= stationsImg.replaceAll("http://other.yueyongyueyou.com:8888/","").replaceAll(",",";");
            welfare.setWelfareImg(stationsImg);
            this.activityWelfareService.update(welfare);
            result.setSuccess(true);
            return "activityWelfare/list";
        } catch (Exception e) {
            log.error("更新异常 = " + e.getMessage());
        }
        return "activityWelfare/list";
    }

    @RequestMapping("updateStatus")
    @ResponseBody
    public Result doUpdateStatus(Long id,Integer status){
        Result result = new Result();
        try {
            if(id != null && id > 0){
                this.activityWelfareService.doUpdateAdStatus(id,status);
                result.setMessage("更新状态成功！");
                result.setSuccess(true);
            }else{
                result.setMessage("数据编号不能为空！");
                result.setSuccess(false);
            }
        } catch (Exception e){
            result.setMessage("更新状态失败！");
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping("getById")
    @ResponseBody
    public Result getByWelfareId(Long id){
        HshActivityWelfare welfare = this.activityWelfareService.getById(id);
        return Result.ok(welfare);
    }
}
