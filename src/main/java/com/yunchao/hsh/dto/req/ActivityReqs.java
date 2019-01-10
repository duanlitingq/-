package com.yunchao.hsh.dto.req;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/12/14 10:17
 * @Description:
 * @Email: hkwind959@gmail.com
 */
public class ActivityReqs implements Serializable {
    /**
     * 活动ID
     */
    private Long activityId;
    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 活动商品价格
     */
    private BigDecimal activityPrice;

    /**
     * 购买返还的积分
     */
    private Long itemRebate;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public BigDecimal getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(BigDecimal activityPrice) {
        this.activityPrice = activityPrice;
    }

    public Long getItemRebate() {
        return itemRebate;
    }

    public void setItemRebate(Long itemRebate) {
        this.itemRebate = itemRebate;
    }
}
