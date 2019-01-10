package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dao.HshLogisticsMapper;
import com.yunchao.hsh.dao.HshStationMapper;
import com.yunchao.hsh.model.HshLogistics;
import com.yunchao.hsh.model.HshStation;
import com.yunchao.hsh.service.IHshLogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class HshLogisticsServiceImpl implements IHshLogisticsService {
    @Autowired
    private HshLogisticsMapper hshLogisticsMapper;

    @Autowired
    private HshStationMapper stationMapper;

    @Override
    public int addLoginstics(HshLogistics hshLogistics) {
        return hshLogisticsMapper.insertSelective(hshLogistics);
    }

    @Override
    public HshLogistics selectBycustomer(Long catimeId) {
        return hshLogisticsMapper.selectBycustomer(catimeId);
    }

    @Override
    public int updateByPrimaryKeySelective(HshLogistics record) {
        return hshLogisticsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public HshLogistics selectLogistics(Long id) {
        return hshLogisticsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delLogiStics(Long id) {
        return hshLogisticsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<HshLogistics> selectAllLogistics(Long id) {
        return hshLogisticsMapper.selectAllLogistics(id);
    }

    @Override
    public PageInfo<HshLogistics> getPage(HashMap<String, Object> map, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Object customerId = map.get("customerId");
        long l = Long.valueOf(String.valueOf(customerId)).longValue();
        List<HshLogistics> hshLogistics = hshLogisticsMapper.selectAllLogistics(l);
        PageInfo<HshLogistics> pageInfo = new PageInfo<HshLogistics>(hshLogistics);
        return pageInfo;
    }

    @Override
    public void saveShoppingAddress(Long customerId, String realName, String realMobile, Long stationId) {
        HshLogistics record = new HshLogistics();
        record.setCustomerId(customerId);
        record.setRealName(realName);
        record.setPhone(realMobile);

        HshStation station = this.stationMapper.selectByPrimaryKey(stationId);
        if (station != null) {
            record.setAddress("");
        }
        String[] split = station.getStationImg().split(";");
        record.setStationImg(split[0]);
        record.setAddress(station.getAddress());
        this.hshLogisticsMapper.insertSelective(record);
    }

    @Override
    public HshLogistics getDefault(HshLogistics hshLogistics) {
        return hshLogisticsMapper.getDefault(hshLogistics);
    }
}
