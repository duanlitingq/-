package com.yunchao.hsh.dto.resp;

import com.yunchao.hsh.model.HshActivityOrderGetLog;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/11/22 14:37
 * @Description:
 * @Email: hkwind959@gmail.com
 */
public class OrderGetLogResp extends HshActivityOrderGetLog {

    //（1、确定领取，2：待领取）',

    //提货地址
    private String shoppingAddress;

    public String getStatu() {
        byte status = this.getStatus();
        String satat = String.valueOf(status);
        if (satat.equals("1")) {
            return "确定领取";
        } else {
            return "待领取";
        }
    }

    public String getShoppingAddress() {
        return shoppingAddress;
    }

    public void setShoppingAddress(String shoppingAddress) {
        this.shoppingAddress = shoppingAddress;
    }

    @Override
    public String toString() {
        return "OrderGetLogResp{" +
                "shoppingAddress='" + shoppingAddress + '\'' +
                '}';
    }
}
