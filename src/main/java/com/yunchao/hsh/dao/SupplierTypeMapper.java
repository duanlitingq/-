package com.yunchao.hsh.dao;

import com.yunchao.hsh.dto.resp.SupplierTypeResp;
import com.yunchao.hsh.model.SupplierType;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface SupplierTypeMapper {

    List<SupplierTypeResp> findPage(HashMap<String, Object> map);

    void update(SupplierType su);

    void insert(SupplierType su);

    void updateStatus(SupplierType su);

    SupplierTypeResp findById(Long id);

    SupplierTypeResp findByName(SupplierType su);

    List<SupplierTypeResp> findList(@Param("status") Integer status);
}
