package com.yunchao.hsh.controller.admin;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.model.HshCustomerWithdrawals;
import com.yunchao.hsh.service.ICustomerWithdrawalsService;
import com.yunchao.hsh.service.IWalletService;
import com.yunchao.hsh.utils.ParamUtils;
import com.yunchao.hsh.utils.superdir.sub.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/page/admin/customerWithdrawals/")
public class CustomerWithdrawalsController {
    @Autowired
    private ICustomerWithdrawalsService customerWithdrawalsService;

    /**
     * 获取所有提现信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.POST)
    @ResponseBody
    public Result getPage(HttpServletRequest request){
        Result result = new Result();
        int pageNum = ParamUtils.getIntParameter(request, "pageNum", 1);
        int pageSize = ParamUtils.getIntParameter(request, "pageSize", 20);
        PageInfo<HshCustomerWithdrawals> page = customerWithdrawalsService.getPage(pageNum, pageSize);
        return result.setS(0,page);
    }

    /**
     * 获取   个人总余额/提现
     * @return
     */
    @RequestMapping(value = "total",method = RequestMethod.POST)
    @ResponseBody
    public Result getTotal(){
        Result result=new Result();
        HshCustomerWithdrawals total = customerWithdrawalsService.findTotal();
        return  result.setS(0,total);
    }

    /**
     * 确认打款(修改状态)
     * @param id
     * @return
     */
    @RequestMapping(value = "updateState",method = RequestMethod.POST)
    @ResponseBody
    public Result updateState(Long id){
        Result result=new Result();
        if (id!=null){
            int i = customerWithdrawalsService.updateState(id);
            System.out.print(i+"???????????????????????????????????????????????????????????????????");
            result.setS(0,"打款成功");
        }
        return result;
    }
}
