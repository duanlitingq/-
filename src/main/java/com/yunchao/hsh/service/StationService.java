package com.yunchao.hsh.service;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.req.StationReq;
import com.yunchao.hsh.dto.resp.StationResp;
import com.yunchao.hsh.model.HshStation;

import java.util.List;

public interface StationService {


    PageInfo<HshStation> findStationList(StationReq stationReq);

    void addStation(StationReq stationReq) throws Exception;

    void delStation(String stationId);

    void upStation(String stationId);

    void updateStation(StationReq stationReq);

    HshStation findByStationId(Long stationId);

    List<StationResp> getStationList();
    PageInfo<StationResp> getStationPage(int page,int pageSize);

    PageInfo<StationResp> getConditionPage(int page,int pageSize,String name);
}
