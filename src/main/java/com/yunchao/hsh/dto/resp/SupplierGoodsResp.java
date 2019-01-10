package com.yunchao.hsh.dto.resp;

import com.yunchao.hsh.model.SupplierGoods;

public class SupplierGoodsResp extends SupplierGoods {

    public String getStatusName(){
        if(this.getStatus() == 1){
            return "是";
        }else{
            return "否";
        }
    }

    public String typeName;
    public String supplierName;
    //该商品在购物车中的数量
    public Integer carNum;

    public Integer getCarNum() {
        return carNum;
    }

    public void setCarNum(Integer carNum) {
        this.carNum = carNum;
    }


    public String getSupplierName() {
        return supplierName;
    }

    public String[] getImgsArr(){
        String str = this.getImgs().replace("\\","/");
        return str.split(";");
    }

    private String unitName;

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getTypeName() {
        if(this.typeName == null){
            typeName = "--";
        }
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
