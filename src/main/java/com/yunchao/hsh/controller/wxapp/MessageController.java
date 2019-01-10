package com.yunchao.hsh.controller.wxapp;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.model.Customer;
import com.yunchao.hsh.model.Message;
import com.yunchao.hsh.service.ICustomerService;
import com.yunchao.hsh.service.IMessageService;
import com.yunchao.hsh.utils.ToolsUtil;
import com.yunchao.hsh.utils.superdir.sub.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * 消息管理
 */
@Controller(value = "appMessageController")
@RequestMapping(value = "/page/wxapp/message/")
public class MessageController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IMessageService messageService;

    /**
     * 获取当前登陆用户消息
     * @param token
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("findMessage")
    @ResponseBody
    public Result findMessage(String token, int page, @RequestParam(defaultValue = "10") int pageSize){
        Result result=new Result();
        try {
            if (token!=null){
                String redisOpenId = ToolsUtil.getRedisOpenId(token);
                Customer customer = customerService.getByOpenID(redisOpenId);
            if (customer!=null){
                Long id = customer.getId();
                HashMap<String,Object>map=new HashMap<String,Object>();
                map.put("receiveId",id);
                PageInfo<Message> page1 = messageService.getMessagePage(map, page, pageSize);
                result.setS("",page1);
                return result;
            }
            }
        }catch (Exception e){
            e.printStackTrace();
            return  result.setF("消息获取失败!");
        }
        return  null;
    }
}
