package com.yunchao.hsh.dto.req;

import java.io.Serializable;

public class StationOrderReq implements Serializable {

    //付款人token
    private String token;

    //收款人ID stationId
    private Long id;

    //收款金额
    private Double price;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
