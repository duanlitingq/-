package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dao.HshActivityMapper;
import com.yunchao.hsh.dto.req.ActivityReq;
import com.yunchao.hsh.dto.req.ActivityReqs;
import com.yunchao.hsh.dto.resp.ActivityItemResp;
import com.yunchao.hsh.model.HshActivity;
import com.yunchao.hsh.model.HshActivityExample;
import com.yunchao.hsh.service.BaseService;
import com.yunchao.hsh.service.IActivityService;
import com.yunchao.hsh.utils.BigDecimalUtils;
import com.yunchao.hsh.utils.superdir.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 2352 活动商品
 *
 * @ClassName: ActivityServiceImpl
 * @Description: TODO
 * @Author: ZHAI Q
 * @Email:hkwind959@google.com
 * @Date: 2018/11/8 18:21
 * @Version: 1.0
 */
@Service
@Transactional
public class ActivityServiceImpl extends BaseService implements IActivityService {

    @Autowired
    private HshActivityMapper activityMapper;

    @Override
    public Result getItemList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        HshActivityExample example = new HshActivityExample();
        example.createCriteria().andStatusEqualTo((byte) 1);
        example.setOrderByClause("create_time DESC");
        List<HshActivity> activityList = this.activityMapper.selectByExample(example);
        PageInfo<HshActivity> info = new PageInfo<>(activityList);
        return Result.ok(info);
    }

    @Override
    public Result addItem(ActivityReq activity, String desc) {
        HshActivity hshActivity = new HshActivity();
        hshActivity.setActivityName(activity.getActivityName());
        hshActivity.setActivityPrice(BigDecimalUtils.toBigDecimal(activity.getActivityPrice(), 2));
        hshActivity.setActivityImg(activity.getActivityImg());
        hshActivity.setActivityItemImg(activity.getItemImgs());
        //活动上线状态  1 上线， 2 下架
        hshActivity.setStatus(Byte.valueOf("1"));
        //购买商品返还积分
        hshActivity.setItemRebate(Long.valueOf(activity.getItemRebate()));
        hshActivity.setCreateTime(new Date());
        //保存商品描述
        hshActivity.setCusNum1(activity.getItemDescImgs());
        //玩法介绍
        hshActivity.setCusNum4(activity.getGameImgs());
        this.activityMapper.insertSelective(hshActivity);
        return Result.ok(null);
    }

    @Override
    public Result updateItem(ActivityReqs reqs) {
        ActivityReqs activity = new ActivityReqs();
        activity.setActivityId(reqs.getActivityId());
        activity.setActivityName(reqs.getActivityName());
        activity.setActivityPrice(reqs.getActivityPrice());
        activity.setItemRebate(reqs.getItemRebate());
        this.activityMapper.updateByPrimaryKeyPrice(activity);
        return Result.ok(null);
    }

    @Override
    public Result delItem(long activityId) {
        this.activityMapper.updateByActivityId(activityId);
        return Result.ok(null);
    }

    @Override
    public Result findByActivityId(Long activityId) {
        HshActivity hshActivity = this.activityMapper.selectByPrimaryKey(activityId);
        return Result.ok(hshActivity);
    }

    @Override
    public ActivityItemResp getByActivityId(Long activityId) {
        ActivityItemResp activityItemResp = this.activityMapper.getByActivityId(activityId);
        return activityItemResp;
    }

}