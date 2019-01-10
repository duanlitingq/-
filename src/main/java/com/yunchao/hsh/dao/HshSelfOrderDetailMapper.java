package com.yunchao.hsh.dao;

import com.yunchao.hsh.model.HshSelfOrderDetail;
import com.yunchao.hsh.model.HshSelfOrderDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HshSelfOrderDetailMapper {
    int countByExample(HshSelfOrderDetailExample example);

    int deleteByExample(HshSelfOrderDetailExample example);

    int insert(HshSelfOrderDetail record);

    int insertSelective(HshSelfOrderDetail record);

    List<HshSelfOrderDetail> selectByExample(HshSelfOrderDetailExample example);

    int updateByExampleSelective(@Param("record") HshSelfOrderDetail record, @Param("example") HshSelfOrderDetailExample example);

    int updateByExample(@Param("record") HshSelfOrderDetail record, @Param("example") HshSelfOrderDetailExample example);
}