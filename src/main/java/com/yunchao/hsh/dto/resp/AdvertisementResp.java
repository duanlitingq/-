package com.yunchao.hsh.dto.resp;

import com.yunchao.hsh.model.Advertisement;
import org.apache.commons.lang.StringUtils;

/**
 * Created by wdz on 2018/11/7
 * Remarks:
 */
public class AdvertisementResp extends Advertisement {

    public String[] getImgsArr(){
        if(!StringUtils.isBlank(this.getImgs())){
            String replace = this.getImgs().replace("\\", "/");
            return replace.split(";");
        }
        return null;
    }
    private String statusName;

    public String getStatusName() {
        if(this.getStatus() == 0){
            statusName = "否";
        }else{
            statusName = "是";
        }
        return statusName;
    }
}
