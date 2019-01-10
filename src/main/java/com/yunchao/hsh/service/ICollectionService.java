package com.yunchao.hsh.service;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.model.HshCollection;

import java.util.HashMap;


/**
 *
 */
public interface ICollectionService {
    //添加
   int addCollection(HshCollection hshCollection);
    //修改
   int updateCollection(HshCollection hshCollection);
    //删除
   int delCollction(HshCollection hshCollection);
   //通过用户id,商品id  获取信息
   HshCollection selectUserCollection(HshCollection collection);
   //分页
    PageInfo<HshCollection> getPage(HashMap<String,Object> map,Integer pageNum, Integer pageSize);
}
