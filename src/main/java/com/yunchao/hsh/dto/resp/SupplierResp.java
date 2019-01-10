package com.yunchao.hsh.dto.resp;

import com.yunchao.hsh.model.ShoppingCar;
import com.yunchao.hsh.model.Supplier;
import com.yunchao.hsh.utils.ObjectUtils;

import java.util.List;

public class SupplierResp extends Supplier {

    //类别名称
    private String typeName;
    //管理人名称
    private String userName;
    //状态名称
    private String statusName;

    //新订单数
    private Integer newOrderNum;
    //待发货数
    private Integer waitOrderNum;

    //购物车商品临时用
    private List<ShoppingCar> cars;
    public String getStatusName(){
        if(this.getStatus() == 0){
            this.statusName = "否";
        }else{
            this.statusName = "是";
        }
        return statusName;
    }

    public String[] getImgsArr(){
        String imgs = this.getImgs();
        if(ObjectUtils.isNotEmpty(imgs)){
            String[] split = imgs.split(";");
            return split;
        }
        return null;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ShoppingCar> getCars() {
        return cars;
    }

    public void setCars(List<ShoppingCar> cars) {
        this.cars = cars;
    }

    private List<SupplierGoodsResp> goodsRespList;

    public void setGoodsRespList(List<SupplierGoodsResp> goodsRespList) {
        this.goodsRespList = goodsRespList;
    }

    public List<SupplierGoodsResp> getGoodsRespList() {
        return goodsRespList;
    }

    /****临时数据***/
    //总提现金额
    private Double outMoney;
    //总订单数
    private Long  orderNum;

    public Double getOutMoney() {
        return outMoney;
    }

    public void setOutMoney(Double outMoney) {
        this.outMoney = outMoney;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getNewOrderNum() {
        return newOrderNum;
    }

    public void setNewOrderNum(Integer newOrderNum) {
        this.newOrderNum = newOrderNum;
    }

    public Integer getWaitOrderNum() {
        return waitOrderNum;
    }

    public void setWaitOrderNum(Integer waitOrderNum) {
        this.waitOrderNum = waitOrderNum;
    }
}
