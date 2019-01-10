package com.yunchao.hsh.dto.resp;

import com.yunchao.hsh.constant.Constant;
import com.yunchao.hsh.model.HshLogistics;
import com.yunchao.hsh.model.SupplierOrder;

public class SupplierOrderResp extends SupplierOrder {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String payTypeName;

    //用户选择驿站地址
    private HshLogistics hshLogistics;
    private String supplierName;
    private String supplierPhone;
    public String getPayTypeName() {
        /**支付方式 1:微信 2：余额3：积分4：余额+积分5：支付宝*/
        Integer payType = this.getPayType();
        switch (payType){
            case 1:
                payTypeName = Constant.WX_PAY;
                break;
            case 2:
            payTypeName = Constant.YUE_PAY;
                break;
            case 3:
            payTypeName = Constant.INTEGRAL_PAY;
                break;
            case 4:
            payTypeName = Constant.YUE_INTEGRAL_PAY;
                break;
            case 5:
            payTypeName = Constant.ALI_PAY;
                break;
        }
        return payTypeName;
    }

    public String getSupplierName() {
        return supplierName;
    }


    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }
}
