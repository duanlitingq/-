package com.yunchao.hsh.service;

import com.yunchao.hsh.model.HshAnnoun;

import java.util.List;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/11/23 09:58
 * @Description:
 * @Email: hkwind959@gmail.com
 */
public interface IAnnounService {


    public List<HshAnnoun> getAnnounListMsg() throws Exception;
}
