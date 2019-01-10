package com.yunchao.hsh.dto.resp;

import com.yunchao.hsh.model.HshActivity;
import com.yunchao.hsh.model.HshActivityWelfare;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/11/21 13:40
 * @Description:
 * @Email: hkwind959@gmail.com
 */
public class ActivityItemResp extends HshActivity {

    //商品主图
//    public String[] getActivityImgArr() {
//        String imgs = this.getActivityImg().replace("\\","/");
//        if (StringUtils.isNotEmpty(imgs)) {
//            String[] split = imgs.split(";");
//            return split;
//        }
//        return null;
//    }

    //商品主图
    public String[] getActivityItemImgArr() {
        String imgs = this.getActivityItemImg().replace("\\", "/");
        if (StringUtils.isNotEmpty(imgs)) {
            String[] split = imgs.split(";");
            return split;
        }
        return null;
    }

    //收货地址ID
    private Long logId;
    //收货人
    private String receiverName;
    //收货人手机号
    private String receiverMobile;
    //提货地址
    private String receiverAddress;
    private List<HshActivityWelfare> welfareList;

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public List<HshActivityWelfare> getWelfareList() {
        return welfareList;
    }

    public void setWelfareList(List<HshActivityWelfare> welfareList) {
        this.welfareList = welfareList;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }
}
