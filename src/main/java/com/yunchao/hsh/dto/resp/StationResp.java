package com.yunchao.hsh.dto.resp;

import com.yunchao.hsh.model.HshStation;
import org.apache.commons.lang.StringUtils;

public class StationResp extends HshStation {


    public String[] getItemImgArr() {
        String imgs = this.getStationImg().replace("\\","/");;
        if (StringUtils.isNotEmpty(imgs)) {
            String[] split = imgs.split(";");
            return split;
        }
        return null;
    }
}
