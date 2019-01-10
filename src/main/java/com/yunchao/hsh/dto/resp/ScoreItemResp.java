package com.yunchao.hsh.dto.resp;

import com.yunchao.hsh.model.HshSelfItem;
import org.apache.commons.lang.StringUtils;

public class ScoreItemResp extends HshSelfItem {

    private int flag;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String[] getItemImgArr() {
        String imgs = this.getItemImg().replace("\\","/");
        if (StringUtils.isNotEmpty(imgs)) {
            String[] split = imgs.split(";");
            return split;
        }
        return null;
    }
}
