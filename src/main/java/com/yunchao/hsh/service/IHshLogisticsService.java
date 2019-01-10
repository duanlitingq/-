package com.yunchao.hsh.service;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.model.HshLogistics;

import java.util.HashMap;
import java.util.List;

public interface IHshLogisticsService {
    int addLoginstics(HshLogistics hshLogistics);

    HshLogistics selectBycustomer(Long catimeId);

    int updateByPrimaryKeySelective(HshLogistics record);

    HshLogistics selectLogistics(Long id);

    int delLogiStics(Long id);

    List<HshLogistics> selectAllLogistics(Long id);

    PageInfo<HshLogistics> getPage(HashMap<String, Object> map, int pageNum, int pageSize);

    void saveShoppingAddress(Long customerId,String realName, String realMobile, Long stationId) throws Exception;

    //获取是否有默认地址
    HshLogistics getDefault(HshLogistics hshLogistics);
}
