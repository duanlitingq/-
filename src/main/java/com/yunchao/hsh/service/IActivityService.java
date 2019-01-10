package com.yunchao.hsh.service;

import com.yunchao.hsh.dto.req.ActivityReq;
import com.yunchao.hsh.dto.req.ActivityReqs;
import com.yunchao.hsh.dto.resp.ActivityItemResp;
import com.yunchao.hsh.model.HshActivity;
import com.yunchao.hsh.utils.superdir.Result;

import java.math.BigDecimal;

public interface IActivityService {

    Result getItemList(Integer pageNum, Integer pageSize);

    Result addItem(ActivityReq activity, String desc);

    Result delItem(long activityId);

    Result findByActivityId(Long activityId);

    ActivityItemResp getByActivityId(Long activityId) throws Exception;

    Result updateItem(ActivityReqs reqs);
}
