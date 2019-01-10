package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dao.CustomerMapper;
import com.yunchao.hsh.dto.resp.PageResultResp;
import com.yunchao.hsh.model.Customer;
import com.yunchao.hsh.model.Message;
import com.yunchao.hsh.service.ICustomerService;
import com.yunchao.hsh.utils.CommonUtil;
import com.yunchao.hsh.utils.ObjectUtils;
import com.yunchao.hsh.utils.superdir.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: customerService接口实现
 * @Author: 隗鹏
 * @CreateDate: 2018/11/7 0007 11:44
 * @UpdateUser:
 * @UpdateDate:
 * @UpdateRemark:
 * @Version: 1.0
 */
@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public int delete(Long id) {
        return customerMapper.delete(id);
    }

    @Override
    public int save(Customer customer) {
        //生成用户邀请码
        /*while (true) {
            String serialCode = CommonUtil.getSerialCode();
            Customer byCode = getByCode(serialCode);
            if (ObjectUtils.isEmpty(byCode)) {
                customer.setInviteCode(serialCode);
                break;
            } else {
                continue;
            }
        }*/
        customer.setAddTime(new Date());
        return customerMapper.insert(customer);
    }

    @Override
    public Customer getById(Long id) {
        return customerMapper.findById(id);
    }

    @Override
    public int update(Customer record) {
        return customerMapper.update(record);
    }

    @Override
    //通过邀请码查找用户
    public Customer getByCode(String code) {
        return customerMapper.findByCode(code);
    }

    @Override
    public Customer getByOpenID(String openId) {
        return customerMapper.findByOpenID(openId);
    }

    @Override
    public Customer getByPhone(String phone) {
        return customerMapper.findByPhone(phone);
    }

    @Override
    public List<Customer> getList(HashMap<String, Object> map) {
        return customerMapper.findList(map);
    }

    @Override
    public PageInfo<Customer> getPage(HashMap<String, Object> map, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Customer> list = customerMapper.findList(map);
        PageInfo<Customer> pageInfo = new PageInfo<Customer>(list);
        return pageInfo;
    }

    @Override
    public Customer getIdCustomer(Long id) {
        return customerMapper.getIdCustomer(id);
    }

    @Override
    public int getCount(Long id) {
        return customerMapper.getCount(id);
    }

    @Override
    public Customer getCustomerByOpenId(String openId) {
        return customerMapper.getCustomerId(openId);
    }

    @Override
    public List<Customer> findSuperior(Long superiorId) {
        return customerMapper.findSuperior(superiorId);
    }

    @Override
    public PageInfo<Customer> findPageSuperior(HashMap<String, Object> map, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Customer> list = customerMapper.findPageSuperior(map);
        PageInfo<Customer> pageInfo = new PageInfo<Customer>(list);
        return pageInfo;
    }
}
