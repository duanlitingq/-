package com.yunchao.hsh.dao;

import com.yunchao.hsh.model.HshAnnoun;
import com.yunchao.hsh.model.HshAnnounExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HshAnnounMapper {
    int countByExample(HshAnnounExample example);

    int deleteByExample(HshAnnounExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HshAnnoun record);

    int insertSelective(HshAnnoun record);

    List<HshAnnoun> selectByExample(HshAnnounExample example);

    HshAnnoun selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HshAnnoun record, @Param("example") HshAnnounExample example);

    int updateByExample(@Param("record") HshAnnoun record, @Param("example") HshAnnounExample example);

    int updateByPrimaryKeySelective(HshAnnoun record);

    int updateByPrimaryKey(HshAnnoun record);
}