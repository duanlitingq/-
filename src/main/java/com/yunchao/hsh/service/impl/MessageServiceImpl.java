package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dao.MessageMapper;
import com.yunchao.hsh.model.Message;
import com.yunchao.hsh.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements IMessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public PageInfo<Message> getPage(HashMap<String, Object> map, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Message> messages = messageMapper.selectByExample(null);
        PageInfo<Message> pageInfo=new PageInfo<Message>(messages);
        return pageInfo;
    }

    @Override
    public PageInfo<Message> getMessagePage(HashMap<String, Object> map, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Message> list = messageMapper.getMessageGetPage(map);
        PageInfo<Message> pageInfo = new PageInfo<Message>(list);
        return pageInfo;
    }

    @Override
    public int addMessage(Message message) {
        return messageMapper.insert(message);
    }
}
