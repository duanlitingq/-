package com.yunchao.hsh.dao;

import com.yunchao.hsh.dto.resp.OrderItemListResp;
import com.yunchao.hsh.dto.resp.SelfOrderDetailResp;
import com.yunchao.hsh.model.HshSelfOrder;
import com.yunchao.hsh.model.HshSelfOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HshSelfOrderMapper {
    int countByExample(HshSelfOrderExample example);

    int deleteByExample(HshSelfOrderExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(HshSelfOrder record);

    int insertSelective(HshSelfOrder record);

    List<HshSelfOrder> selectByExample(HshSelfOrderExample example);

    List<OrderItemListResp> findPage(HshSelfOrderExample example);

    HshSelfOrder selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") HshSelfOrder record, @Param("example") HshSelfOrderExample example);

    int updateByExample(@Param("record") HshSelfOrder record, @Param("example") HshSelfOrderExample example);

    int updateByPrimaryKeySelective(HshSelfOrder record);

    int updateByPrimaryKey(HshSelfOrder record);

    List<SelfOrderDetailResp> selectByPage(HshSelfOrderExample example);

}