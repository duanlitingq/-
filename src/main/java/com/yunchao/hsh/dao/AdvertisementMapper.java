package com.yunchao.hsh.dao;

import com.yunchao.hsh.dto.resp.AdvertisementResp;
import com.yunchao.hsh.model.Advertisement;

import java.util.List;
import java.util.Map; /**
 * 广告数据层接口
 */
public interface AdvertisementMapper {

    /**
     * 分页list
     * @param params
     * @return
     */
    List<AdvertisementResp> getPage(Map<String, Object> params);

    /**
     * 修改
     * @param ad
     */
    void update(Advertisement ad);

    /**
     * 保存
     * @param ad
     */
    void save(Advertisement ad);

    /**
     * 获取广告
     * @param status  展示/隐藏
     * @return
     */
    List<AdvertisementResp> getList(Integer status);

    /**
     * 修改状态
     * @param id
     * @param status
     */
    void doUpdateAdStatus(Map<String,Object> map);

    AdvertisementResp getById(Long id);
}
