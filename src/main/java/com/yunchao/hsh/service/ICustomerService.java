package com.yunchao.hsh.service;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.model.Customer;
import com.yunchao.hsh.utils.superdir.Result;

import java.util.HashMap;
import java.util.List;

/**
 * @Description: customer Service接口
 * @Author: 隗鹏
 * @CreateDate: 2018/11/7  11:27
 * @UpdateUser:
 * @UpdateDate:
 * @UpdateRemark:
 * @Version: 1.0
 */
public interface ICustomerService {
    int delete(Long id);

    int save(Customer record);

    Customer getById(Long id);

    int update(Customer record);
    //通过邀请码查找用户
    Customer getByCode(String code);

    Customer getByOpenID(String openId);

    Customer getByPhone(String phone);

    List<Customer> getList(HashMap<String,Object> map);

    PageInfo<Customer> getPage(HashMap<String,Object> map, int pageNum, int pageSize);

    //通过id查询用户信息
    Customer getIdCustomer(Long id);

    //获取邀请人数
    int getCount(Long id);

    Customer getCustomerByOpenId(String openId);
    //获取是否被邀请
    List<Customer> findSuperior(Long superiorId);

    PageInfo<Customer> findPageSuperior(HashMap<String,Object>map,int page,int pageSize);
}
