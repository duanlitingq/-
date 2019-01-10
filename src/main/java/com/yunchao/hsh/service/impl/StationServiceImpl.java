package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dao.HshStationMapper;
import com.yunchao.hsh.dto.req.StationReq;
import com.yunchao.hsh.dto.resp.StationResp;
import com.yunchao.hsh.model.HshStation;
import com.yunchao.hsh.model.HshStationExample;
import com.yunchao.hsh.service.BaseService;
import com.yunchao.hsh.service.StationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class StationServiceImpl extends BaseService implements StationService {

    @Autowired
    private HshStationMapper stationMapper;

    /**
     * 驿站分页列表
     *
     * @param stationReq
     * @return
     */
    @Override
    public PageInfo<HshStation> findStationList(StationReq stationReq) {
        HshStationExample example = new HshStationExample();
        HshStationExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(stationReq.getName())) {
            criteria.andNameLike("%" + stationReq.getName() + "%");
        }
        if (StringUtils.isNotBlank(stationReq.getIsDel())) {
            criteria.andIsDelEqualTo(Byte.valueOf(stationReq.getIsDel()));
        } else {
            criteria.andIsDelEqualTo((byte) 1);
        }
        List<HshStation> list = this.stationMapper.selectByExample(example);
        PageInfo<HshStation> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void addStation(StationReq stationReq) throws Exception {
        HshStation station = new HshStation();
        station.setName(stationReq.getName());
        station.setFeaturs(stationReq.getFeaturs());
        station.setPopularity(0);
        station.setPreAvg(stationReq.getPreAvg());
        station.setAddress(stationReq.getAddress());
        //经度
        station.setLongitude(stationReq.getLongitude());
        //纬度
        station.setLatitude(stationReq.getLatitude());
        //联系方式
        station.setCusNumOne(stationReq.getPhone());
        if (stationReq.getStationImg().equals("")) {
            //设置一个默认值
            station.setStationImg("/upload/userAgreement/caitingmoreng.png;");
        } else {
            station.setStationImg(stationReq.getStationImg());
        }
        station.setStationLicense(stationReq.getStationLicense());
        station.setStationQuality(stationReq.getStationQuality());
        station.setCreateTime(new Date());
        station.setOfflineTime(new Date());
        this.stationMapper.insertSelective(station);
    }

    @Override
    public void delStation(String stationId) {
        HshStation record = new HshStation();
        record.setStationId(Long.valueOf(stationId));
        record.setIsDel((byte) 0);
        record.setOfflineTime(new Date());
        this.stationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void upStation(String stationId) {
        HshStation record = new HshStation();
        record.setStationId(Long.valueOf(stationId));
        record.setIsDel((byte) 1);
        record.setOnlineTime(new Date());
        this.stationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void updateStation(StationReq stationReq) {
        HshStation station = new HshStation();
        station.setStationId(stationReq.getStationId());
        station.setName(stationReq.getName());
        station.setFeaturs(stationReq.getFeaturs());
        station.setPopularity(0);
        station.setPreAvg(stationReq.getPreAvg());
        station.setAddress(stationReq.getAddress());
        station.setLongitude(stationReq.getLongitude());
        station.setLatitude(stationReq.getLatitude());
        station.setStationImg(stationReq.getStationImg());
        station.setStationLicense(stationReq.getStationLicense());
        station.setStationQuality(stationReq.getStationQuality());
        station.setUpdateTime(new Date());
        //联系方式
        station.setCusNumOne(stationReq.getPhone());
        this.stationMapper.updateByPrimaryKeySelective(station);
    }

    @Override
    public HshStation findByStationId(Long stationId) {
        HshStation station = this.stationMapper.selectByPrimaryKey(stationId);
        return station;
    }

    @Override
    public List<StationResp> getStationList() {
        List<StationResp> list = this.stationMapper.getStationList();
        return list;
    }

    @Override
    public PageInfo<StationResp> getStationPage(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<StationResp> list = this.stationMapper.getStationList();
        PageInfo<StationResp> info=new PageInfo<>(list);
        return info;
    }

    @Override
    public PageInfo<StationResp> getConditionPage(int page, int pageSize, String name) {
        PageHelper.startPage(page,pageSize);
        List<StationResp> conditionList = this.stationMapper.getCondition(name);
        PageInfo<StationResp> info=new PageInfo<>(conditionList);
        return info;
    }
}
