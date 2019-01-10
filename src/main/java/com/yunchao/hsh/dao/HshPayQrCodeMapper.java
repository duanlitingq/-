package com.yunchao.hsh.dao;

import com.yunchao.hsh.model.HshPayQrCode;
import com.yunchao.hsh.model.HshPayQrCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HshPayQrCodeMapper {
    int countByExample(HshPayQrCodeExample example);

    int deleteByExample(HshPayQrCodeExample example);

    int deleteByPrimaryKey(Long codeId);

    int insert(HshPayQrCode record);

    int insertSelective(HshPayQrCode record);

    List<HshPayQrCode> selectByExample(HshPayQrCodeExample example);

    HshPayQrCode selectByPrimaryKey(Long codeId);

    int updateByExampleSelective(@Param("record") HshPayQrCode record, @Param("example") HshPayQrCodeExample example);

    int updateByExample(@Param("record") HshPayQrCode record, @Param("example") HshPayQrCodeExample example);

    int updateByPrimaryKeySelective(HshPayQrCode record);

    int updateByPrimaryKey(HshPayQrCode record);
}