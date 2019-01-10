package com.yunchao.hsh.controller.wxapp;

import com.yunchao.hsh.model.Message;
import com.yunchao.hsh.service.IMessageService;
import com.yunchao.hsh.utils.superdir.sub.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MessageAppController {
    @Autowired
    private IMessageService messageService;

    public Result addMessage(Long id,String str){
        Result result=new Result();
        if (id!=null){
            Message message=new Message();
            message.setSendId(1L);
            message.setReceiveId(id);
            message.setContent(str);
            messageService.addMessage(message);
        }
        return  result;
    }
}
