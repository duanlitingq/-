package com.yunchao.hsh.dto.resp;

import com.yunchao.hsh.model.Menu;

/**
 * Created by Administrator on 2018/4/27.
 */
public class MenuResp extends Menu{

    public String getStatusStr() {
        String str = "" ;
        if(this.getStatus() == 1){
            str = "展示";
        }
        if(this.getStatus() == 0){
            str = "隐藏";
        }
        return str;
    }
}
