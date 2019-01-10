package com.yunchao.hsh.controller.admin;

import com.yunchao.hsh.model.IntegralRules;
import com.yunchao.hsh.service.IIntegralRulesService;
import com.yunchao.hsh.utils.superdir.sub.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/page/admin/integralRules/")
public class IntegralRulesController {
    @Autowired
    private IIntegralRulesService integralRulesService;

    /**
     * 加载所有规则
     */
    @RequestMapping(value = "getIntegralRules",method = RequestMethod.POST)
    @ResponseBody
    public Result getIntegralRules() {
        Result result = new Result();
        List<IntegralRules> integralRules = integralRulesService.selIntegralRules();
        result.setS("成功");
        result.setData(integralRules);
        return result;
    }

    /**
     * 加载一条数据
     */
    @RequestMapping(value = "getOneIntegralRules",method = RequestMethod.POST)
    @ResponseBody
    public Result getOneIntegralRules(Long id) {
        Result result = new Result();
        IntegralRules integralRules = integralRulesService.selOneIntegralRules(id);
        result.setS("成功");
        result.setData(integralRules);
        return result;
    }
    /**
     * 添加规则
     *
     * @param integralRules
     * @return
     */
    @RequestMapping(value = "addIntegralRules")
    @ResponseBody
    public Result addIntegralRules(IntegralRules integralRules){
        Result result = new Result();
        try {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (integralRules != null) {
            /*String format = df.format(new Date());
            Date parse = df.parse(format);*/
            integralRules.setCreateTime(new Date());
            int i = integralRulesService.addIntegralRules(integralRules);
            if (i > 0) {
                result.setS("添加成功");
            } else {
                result.setF("添加失败");
            }
        }
        }catch (Exception e){
            String message = e.getMessage();
            System.out.println("============================="+message);
        }
        return result;
    }

    /**
     * 修改规则
     * @param integralRules
     * @return
     */
    @RequestMapping(value = "uplIntegralRules", method = RequestMethod.POST)
    @ResponseBody
    public Result uplIntegralRules(IntegralRules integralRules) {
        Result result = new Result();
        if (integralRules != null) {
            int i = integralRulesService.uplIntegralRules(integralRules);
            if (i > 0) {
                result.setS("修改成功");
            } else {
                result.setF("修改失败");
            }
        }
        return result;
    }

    /**
     * 删除规则
     * @param
     * @return
     */
    @RequestMapping(value = "delIntegralRules",method = RequestMethod.POST)
    @ResponseBody
    public Result delIntegralRules(IntegralRules integralRules) {
        Result result = new Result();
        Long id = integralRules.getId();
        if (id != null) {
            int i = integralRulesService.delIntegralRules(id);
            if (i > 0) {
                result.setS("删除成功");
            } else {
                result.setF("删除失败");
            }
        }
        return result;
    }
}

