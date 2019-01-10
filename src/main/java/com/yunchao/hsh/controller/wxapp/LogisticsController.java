package com.yunchao.hsh.controller.wxapp;

import com.yunchao.hsh.model.Customer;
import com.yunchao.hsh.model.HshLogistics;
import com.yunchao.hsh.model.HshStation;
import com.yunchao.hsh.service.ICustomerService;
import com.yunchao.hsh.service.IHshLogisticsService;
import com.yunchao.hsh.service.StationService;
import com.yunchao.hsh.utils.ToolsUtil;
import com.yunchao.hsh.utils.superdir.sub.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@Controller(value = "appLogisticsController")
@RequestMapping("/page/wxapp/logistics/")
public class LogisticsController {
    @Autowired
    private IHshLogisticsService hshLogisticsService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private StationService stationService;

    /**
     * 添加提货地址
     *
     * @param hshLogistics
     * @return
     */

    @RequestMapping(value = "addLoginstics", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "wxapp添加提货地址", tags = {"wxapp添加提货地址"}, notes = "wxapp添加提货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "realName", value = "收件人姓名", required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "手机号", required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "stationId", value = "驿站id", required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "token", value = "token", required = false, dataType = "Integer", paramType = "query"),
    })
    public Result addLoginStics(HshLogistics hshLogistics,@RequestParam(value = "token",required =false ) String token) {
        Result result = new Result();
        String redisOpenId = ToolsUtil.getRedisOpenId(token);
        Customer customer = customerService.getCustomerByOpenId(redisOpenId);
        Long id = customer.getId();
        hshLogistics.setCustomerId(id);
        HshLogistics hshLogistics1 = hshLogisticsService.selectBycustomer(id);
        if (hshLogistics1!=null){
            hshLogistics.setId(hshLogistics1.getId());
            hshLogisticsService.updateByPrimaryKeySelective(hshLogistics);
            result.setS("添加成功");
        }else {
            hshLogisticsService.addLoginstics(hshLogistics);
            result.setS("添加成功");
        }
        return result;
    }

    /**
     * 添加默认地址
     * @param hshLogistics
     * @param token
     * @return
     */
    public Result addLoginSticsDefault(HshLogistics hshLogistics,@RequestParam(value = "token",required =false ) String token) {
        Result result = new Result();
        //获取当前用户的openID
        String redisOpenId = ToolsUtil.getRedisOpenId(token);
        Customer customer = customerService.getCustomerByOpenId(redisOpenId);
        Long id = customer.getId();
        hshLogistics.setCustomerId(id);
        //当前用户
        if (hshLogistics.getRealName() == null) {
            result.setF("请填写收件人姓名!");
        }
        if (hshLogistics.getPhone() == null) {
            result.setF("请填写收件人电话!");
        }
        if (hshLogistics.getIsDefault()!=null){
            if (hshLogistics.getIsDefault() == 1) {
                /**
                 * 0 否  1是
                 * 1.先去查是否有默认地址,有默认修改成不是默认地址,添加现在的为默认地址,没有直接添加为默认地址
                 */
                // HshLogistics hshLogistics1 = hshLogisticsService.selectBycustomer(hshLogistics.getCustomerId());
                hshLogistics.setIsDefault(1);
                HshLogistics hshLogistics1 = hshLogisticsService.getDefault(hshLogistics);
                if (hshLogistics1 != null) {
                    //获取默认地址的id
                    hshLogistics1.setIsDefault(0);

                    int i = hshLogisticsService.updateByPrimaryKeySelective(hshLogistics1);
                    hshLogisticsService.addLoginstics(hshLogistics);
                    if (1 > 0) {
                        result.setS("修改成功");
                    }
                } else {
                    hshLogisticsService.addLoginstics(hshLogistics);
                }
            } else {
                hshLogisticsService.addLoginstics(hshLogistics);
            }
        }
        return result;
    }

    /**
     * 获取所有提货地址
     *
     * @param
     * @return
     */
    @RequestMapping(value = "selectAllLogistics", method = RequestMethod.POST)
    @ResponseBody
    public Result selectAllLogistics() {
        Result result = new Result();
        String openID = ToolsUtil.getRedisOpenId("random");
        Customer customer = customerService.getByOpenID(openID);
        Long id = customer.getId();
        List<HshLogistics> hshLogistics = hshLogisticsService.selectAllLogistics(id);
        result.setData(hshLogistics);
        return result;
    }

    /**
     * 获取单条提货地址
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "selectLogistics", method = RequestMethod.GET)
    @ResponseBody
    public Result selectLogistics(String token) {
        Result result = new Result();
        if (StringUtils.isNotBlank(token)) {
            String redisOpenId = ToolsUtil.getRedisOpenId(token);
            //用户信息
            Customer customer = this.customerService.getByOpenID(redisOpenId);
            if (customer != null) {
                HshLogistics logistics = this.hshLogisticsService.selectBycustomer(customer.getId());
                if (logistics!=null){
                    Long stationId = logistics.getStationId();
                    HshStation station= stationService.findByStationId(stationId);
                    if (station!=null){
                        logistics.setAddress(station.getName());
                        logistics.setStationAddress(station.getAddress());
                    }
                }
                result.setSuccess(true);
                result.setMessage("操作成功");
                result.setData(logistics);
                return result;
            }else{
                result.setMessage("用户未登陆");
                result.setSuccess(false);
                result.setData("");
                return result;
            }
        } else {
            result.setMessage("用户未授权");
            result.setSuccess(false);
            result.setData("");
            return result;
        }
    }

    /**
     * 修改提货地址
     *
     * @param hshLogistics
     * @return
     */
    @RequestMapping(value = "/updateLoginStics", method = RequestMethod.POST)
    @ResponseBody
    public Result updateLoginStics(@RequestBody HshLogistics hshLogistics) {
        Result result = new Result();
        int i = hshLogisticsService.updateByPrimaryKeySelective(hshLogistics);
        if (i > 0) {
            result.setS("修改成功");
        } else {
            result.setF("修改失败");
        }
        return result;
    }

    /**
     * 删除提货地址
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "delLogiStics", method = RequestMethod.POST)
    @ResponseBody
    public Result delLogiStics(Long id) {
        Result result = new Result();
        int i = hshLogisticsService.delLogiStics(id);
        if (i > 0) {
            result.setS("删除成功!");
        }
        return result;
    }

}
