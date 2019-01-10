package com.yunchao.hsh.service;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.MenuResp;
import com.yunchao.hsh.model.Menu;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/5/15.
 * 菜单业务接口
 */
public interface IMenuService {
    void save(Menu menu) ;

    void update(Menu menu) ;

    MenuResp getById(Long id);

    List<MenuResp> getList(Map<String, Object> params);

    PageInfo<MenuResp> getPage(Map<String, Object> params, int pageNum, int pageSize);

    void batchUpdateState(List<Menu> list,Integer status);

    List<Menu> getLeftMenu(Long userId);
}
