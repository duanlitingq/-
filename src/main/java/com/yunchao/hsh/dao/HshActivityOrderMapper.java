package com.yunchao.hsh.dao;

import com.yunchao.hsh.dto.resp.OrderDetailsResp;
import com.yunchao.hsh.dto.resp.OrderItemResp;
import com.yunchao.hsh.dto.resp.SelfOrderDetailResp;
import com.yunchao.hsh.model.HshActivityOrder;
import com.yunchao.hsh.model.HshActivityOrderExample;
import com.yunchao.hsh.model.HshSelfOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HshActivityOrderMapper {
    int countByExample(HshActivityOrderExample example);

    int deleteByExample(HshActivityOrderExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(HshActivityOrder record);

    int insertSelective(HshActivityOrder record);

    List<HshActivityOrder> selectByExample(HshActivityOrderExample example);

    List<HshActivityOrder> findPage(HshActivityOrderExample example);

    HshActivityOrder selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") HshActivityOrder record, @Param("example") HshActivityOrderExample example);

    int updateByExample(@Param("record") HshActivityOrder record, @Param("example") HshActivityOrderExample example);

    int updateByPrimaryKeySelective(HshActivityOrder record);

    int updateByPrimaryKey(HshActivityOrder record);
    int updateById(HshActivityOrder record);

    OrderDetailsResp getOrderDetail(@Param("orderId")String orderId);

    List<OrderItemResp> selectByPage(HshActivityOrderExample example);
}