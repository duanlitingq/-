package com.yunchao.hsh.dao;

import com.yunchao.hsh.model.HshCollection;
import com.yunchao.hsh.model.HshCollectionExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HshCollectionMapper {
    int countByExample(HshCollectionExample example);

    int deleteByExample(HshCollectionExample example);

    int deleteByPrimaryKey(Long collId);

    int insert(HshCollection record);

    int insertSelective(HshCollection record);

    List<HshCollection> selectByExample(HshCollectionExample example);

    HshCollection selectByPrimaryKey(Long collId);

    int updateByExampleSelective(@Param("record") HshCollection record, @Param("example") HshCollectionExample example);

    int updateByExample(@Param("record") HshCollection record, @Param("example") HshCollectionExample example);

    int updateByPrimaryKeySelective(HshCollection record);

    int updateByPrimaryKey(HshCollection record);

    int deleteCollection(HshCollection collection);

    HshCollection selectUserCollection(HshCollection collection);

    List<HshCollection> findPage(HashMap<String,Object> map);
}