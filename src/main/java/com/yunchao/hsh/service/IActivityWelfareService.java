package com.yunchao.hsh.service;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.model.HshActivityWelfare;

import java.util.List;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/11/28 15:54
 * @Description:
 * @Email: hkwind959@gmail.com
 */
public interface IActivityWelfareService {

    PageInfo<HshActivityWelfare> getPage(int pageNum, int pageSize);

    void save(HshActivityWelfare welfare);

    void update(HshActivityWelfare welfare);

    void doUpdateAdStatus(Long id, Integer status);

    HshActivityWelfare getById(Long id);

    List<HshActivityWelfare> selectAll();

    HshActivityWelfare selectById(Long cusNum2);
}
