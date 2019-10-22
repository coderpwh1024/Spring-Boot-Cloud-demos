package com.coderpwh.dto;

import java.util.Date;

/**
 * @author coderpwh
 * @create 2019-09-24 23:24
 * @desc ${DESCRIPTION}
 **/
public class OrderInfoListDto {

    private String orderType;

    private String orderNo;

    private Date createrTime;

    private String goodName;

    // 商品sku
    private String goodId;

    // sku
    private String goodSku;

    // 商品图片
    private String goodImage;

    // 商品数量
    private Integer goodNum;


    public OrderInfoListDto() {
    }


    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(Integer goodNum) {
        this.goodNum = goodNum;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreaterTime() {
        return createrTime;
    }

    public void setCreaterTime(Date createrTime) {
        this.createrTime = createrTime;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getGoodSku() {
        return goodSku;
    }

    public void setGoodSku(String goodSku) {
        this.goodSku = goodSku;
    }

    public String getGoodImage() {
        return goodImage;
    }

    public void setGoodImage(String goodImage) {
        this.goodImage = goodImage;
    }
}
