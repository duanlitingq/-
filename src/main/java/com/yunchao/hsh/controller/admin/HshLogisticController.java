package com.yunchao.hsh.controller.admin;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.model.HshLogistics;
import com.yunchao.hsh.service.IHshLogisticsService;
import com.yunchao.hsh.utils.ParamUtils;
import com.yunchao.hsh.utils.superdir.sub.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 用户提货地址
 */
@Controller
@RequestMapping("/page/admin/hshLogistics/")
public class HshLogisticController {
    @Autowired
    private IHshLogisticsService hshLogisticsService;
    /**
     * 获取用户提货地址
     *
     * @param
     * @return
     */
    @RequestMapping(value = "getPage", method = RequestMethod.POST)
    @ResponseBody
    public Result selectAllLogistics(Long customerId, HttpServletRequest request) {
        Result result = new Result();
        int pageNum = ParamUtils.getIntParameter(request, "pageNum", 1);
        int pageSize = ParamUtils.getIntParameter(request, "pageSize", 20);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("customerId",customerId);
        PageInfo<HshLogistics> page = hshLogisticsService.getPage(map, pageNum, pageSize);
       // List<HshLogistics> hshLogistics = hshLogisticsService.selectAllLogistics(customerId);
        result.setS("操作成功");
        result.setData(page);
        return result;
    }

}
