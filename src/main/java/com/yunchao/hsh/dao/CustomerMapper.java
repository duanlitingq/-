package com.yunchao.hsh.dao;

import com.yunchao.hsh.model.Customer;

import java.util.HashMap;
import java.util.List;


public interface CustomerMapper {
    int delete(Long id);

    int insert(Customer record);

    int getCount(Long id);

    Customer findById(Long id);

    int update(Customer record);

    Customer findByCode(String code);

    Customer findByOpenID(String code);

    Customer findByPhone(String code);

    List<Customer> findList(HashMap<String, Object> map);

    Customer getIdCustomer(Long id);

    Customer getCustomerId(String openId);

    List<Customer> findSuperior(Long superiorId);

    List<Customer> findPageSuperior(HashMap<String,Object>map);
}