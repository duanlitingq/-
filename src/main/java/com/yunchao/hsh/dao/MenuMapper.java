package com.yunchao.hsh.dao;

import com.yunchao.hsh.dto.resp.MenuResp;
import com.yunchao.hsh.model.Menu;

import java.util.List;
import java.util.Map;

public interface MenuMapper {

    void insert(Menu record);

    void update(Menu record);

    MenuResp findById(Long id);

    List<MenuResp> findList();

    List<MenuResp> findPage(Map<String,Object> params) ;

    List<MenuResp> findByIds(List<Integer> childs);
}