package com.yunchao.hsh.service;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.model.Message;

import java.util.HashMap;

public interface IMessageService {
    int addMessage(Message message);
    PageInfo<Message> getPage(HashMap<String,Object> map, int pageNum, int pageSize);
    PageInfo<Message> getMessagePage(HashMap<String,Object>map,int pageNum,int pageSize);
}
