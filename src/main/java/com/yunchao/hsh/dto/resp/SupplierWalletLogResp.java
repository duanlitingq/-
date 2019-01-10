package com.yunchao.hsh.dto.resp;

import com.yunchao.hsh.constant.Constant;
import com.yunchao.hsh.model.SupplierWalletLog;

public class SupplierWalletLogResp extends SupplierWalletLog {

    //订单编号
    private String orderNo;
    //用户昵称
    private String nickName;
    //出入账类型
    private String inOrOutName;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getInOrOutName() {
        if(this.getInOrOut() == 1){
            inOrOutName = Constant.WALLET_IN;
        }else{
            inOrOutName = Constant.WALLET_OUT;
        }
        return inOrOutName;
    }

    public void setInOrOutName(String inOrOutName) {
        this.inOrOutName = inOrOutName;
    }
}
