package com.yunchao.hsh.dto.resp;

import com.yunchao.hsh.model.Advertisement;
import com.yunchao.hsh.model.HshSelfItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class IndexResp implements Serializable {

    //积分
    @ApiModelProperty(value = "积分", required = true, dataType = "double")
    private Double score;
    //商品list
    @ApiModelProperty(value = "推荐商品展示列表", required = true, dataType = "List")
    private List<ScoreItemResp> itemList;

    //广告list
    @ApiModelProperty(value = "活动展示列表", required = true, dataType = "List")
    private List<AdvertisementResp> adList;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public List<ScoreItemResp> getItemList() {
        return itemList;
    }

    public void setItemList(List<ScoreItemResp> itemList) {
        this.itemList = itemList;
    }

    public List<AdvertisementResp> getAdList() {
        return adList;
    }

    public void setAdList(List<AdvertisementResp> adList) {
        this.adList = adList;
    }

    @Override
    public String toString() {
        return "IndexResp{" +
                "score=" + score +
                ", itemList=" + itemList +
                ", adList=" + adList +
                '}';
    }
}
