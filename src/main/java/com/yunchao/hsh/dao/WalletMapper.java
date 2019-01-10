package com.yunchao.hsh.dao;

import com.yunchao.hsh.model.Wallet;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WalletMapper {
    int delete(Long id);

    int insert(Wallet record);


    Wallet findById(Long id);

    Wallet findByCustomerId(Long id);

    List<Wallet> findList(Map<String, Object> map);

    int update(Wallet record);

    int updateByCustomerIdWallet(@Param("score") Double score, @Param("customerId") Long customerId);

    Double sumCustomerPrice();
}