package com.yunchao.hsh.dto.resp;

import com.yunchao.hsh.model.SupplierType;

public class SupplierTypeResp extends SupplierType {

    private String statusName;
    public String getStatusName(){
        if(this.getStatus() == 0){
            statusName="否";
        }else{
            statusName="是";
        }
        return statusName;
    }

}
