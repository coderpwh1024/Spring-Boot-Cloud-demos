package com.coderpwh.vo;

import java.util.Date;

/**
 * @author coderpwh
 * @create 2019-09-24 23:24
 * @desc ${DESCRIPTION}
 **/
public class OrderInfoListVo {

    private String orderNo;

    private Date createDate;

    private String goodName;


    private String goodId;

    private String goodSku;


    private String goodImage;

    public OrderInfoListVo() {
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
