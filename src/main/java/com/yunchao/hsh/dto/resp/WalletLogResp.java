package com.yunchao.hsh.dto.resp;

import com.yunchao.hsh.model.CustomerWalletLog;
import com.yunchao.hsh.utils.Constant;
import com.yunchao.hsh.utils.DateUtils;

import java.util.Date;

public class WalletLogResp extends CustomerWalletLog {

    private String payModeStr;
    private String typeStr;
    private String statusStr;

    private String createDateStr;

    public String getPayModeStr() {
        Integer payMode = this.getPayMode();
        return Constant.WALLET_PAYMODE_MAP.get(payMode);
    }

    public String getStatusStr() {
        Integer status = this.getStatus();
        return Constant.STATUS_MAP.get(status);
    }

    public String getTypeStr() {
        Integer type = this.getType();
        return Constant.WALLET_TYPE_MAP.get(type);
    }

    public String getCreateDateStr(){
        Date createDate = this.getCreateDate();
        return DateUtils.dateToStr(createDate);
    }


}
