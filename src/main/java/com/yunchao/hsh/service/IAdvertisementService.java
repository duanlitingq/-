package com.yunchao.hsh.service;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.AdvertisementResp;
import com.yunchao.hsh.model.Advertisement;

import java.util.List;
import java.util.Map; /**
 * 广告业务接口
 */
public interface IAdvertisementService {
    PageInfo<AdvertisementResp> getPage(Map<String, Object> params, int pageNum, int pageSize);

    void update(Advertisement ad);

    void save(Advertisement ad);

    List<AdvertisementResp> getList(Integer status);

    void doUpdateAdStatus(Long id, Integer status);

    void batchUpdateAdStatus(String adIds, Integer status);

    AdvertisementResp getById(Long id);
}
