package com.yunchao.hsh.dao;

import com.yunchao.hsh.model.HshSelfItemDesc;
import com.yunchao.hsh.model.HshSelfItemDescExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HshSelfItemDescMapper {
    int countByExample(HshSelfItemDescExample example);

    int deleteByExample(HshSelfItemDescExample example);

    int insert(HshSelfItemDesc record);

    int insertSelective(HshSelfItemDesc record);

    List<HshSelfItemDesc> selectByExampleWithBLOBs(HshSelfItemDescExample example);

    List<HshSelfItemDesc> selectByExample(HshSelfItemDescExample example);

    int updateByExampleSelective(@Param("record") HshSelfItemDesc record, @Param("example") HshSelfItemDescExample example);

    int updateByExampleWithBLOBs(@Param("record") HshSelfItemDesc record, @Param("example") HshSelfItemDescExample example);

    int updateByExample(@Param("record") HshSelfItemDesc record, @Param("example") HshSelfItemDescExample example);
}