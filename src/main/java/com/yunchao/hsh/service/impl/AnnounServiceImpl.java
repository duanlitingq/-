package com.yunchao.hsh.service.impl;

import com.yunchao.hsh.dao.HshAnnounMapper;
import com.yunchao.hsh.model.HshAnnoun;
import com.yunchao.hsh.model.HshAnnounExample;
import com.yunchao.hsh.service.IAnnounService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/11/23 09:58
 * @Description:
 * @Email: hkwind959@gmail.com
 */
@Service
@Transactional
public class AnnounServiceImpl implements IAnnounService {

    @Autowired
    private HshAnnounMapper announMapper;


    @Override
    public List<HshAnnoun> getAnnounListMsg() throws Exception {
        HshAnnounExample example = new HshAnnounExample();
        example.setOrderByClause("create_time desc");
        List<HshAnnoun> announs = this.announMapper.selectByExample(example);
        return announs;
    }
}
