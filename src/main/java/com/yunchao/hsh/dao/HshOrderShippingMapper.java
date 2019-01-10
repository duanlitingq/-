package com.yunchao.hsh.dao;

import com.yunchao.hsh.model.HshOrderShipping;
import com.yunchao.hsh.model.HshOrderShippingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HshOrderShippingMapper {
    int countByExample(HshOrderShippingExample example);

    int deleteByExample(HshOrderShippingExample example);

    int insert(HshOrderShipping record);

    int insertSelective(HshOrderShipping record);

    List<HshOrderShipping> selectByExample(HshOrderShippingExample example);

    int updateByExampleSelective(@Param("record") HshOrderShipping record, @Param("example") HshOrderShippingExample example);

    int updateByExample(@Param("record") HshOrderShipping record, @Param("example") HshOrderShippingExample example);
}