package com.coderpwh.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author coderpwh
 * @create 2019-09-22 15:33
 * @desc ${DESCRIPTION}
 **/
public class OrderPackage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer packageId;

    private String packageNo;

    private Long orderId;

    private String orderNo;

    private Integer orderStatus;

    private Integer orderType;

    private Integer orderAmount;

    private Integer goodAmount;


    private Integer logisticsAmount;


    private String packageDesc;


    private Integer discountAmount;


    private Integer couponAmount;


    private Integer virtualGood;


    private Integer goldPrice;


    private Integer delFlag;


    private Long createrId;


    private Date createrTime;


    private Date modifyTime;


    public OrderPackage(Integer packageId, String packageNo, Long orderId, String orderNo, Integer orderStatus, Integer orderType, Integer orderAmount, Integer goodAmount, Integer logisticsAmount, String packageDesc, Integer discountAmount, Integer couponAmount, Integer virtualGood, Integer goldPrice, Integer delFlag, Long createrId, Date createrTime, Date modifyTime) {
        this.packageId = packageId;
        this.packageNo = packageNo;
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.orderStatus = orderStatus;
        this.orderType = orderType;
        this.orderAmount = orderAmount;
        this.goodAmount = goodAmount;
        this.logisticsAmount = logisticsAmount;
        this.packageDesc = packageDesc;
        this.discountAmount = discountAmount;
        this.couponAmount = couponAmount;
        this.virtualGood = virtualGood;
        this.goldPrice = goldPrice;
        this.delFlag = delFlag;
        this.createrId = createrId;
        this.createrTime = createrTime;
        this.modifyTime = modifyTime;
    }

    public OrderPackage() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getPackageNo() {
        return packageNo;
    }

    public void setPackageNo(String packageNo) {
        this.packageNo = packageNo;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getGoodAmount() {
        return goodAmount;
    }

    public void setGoodAmount(Integer goodAmount) {
        this.goodAmount = goodAmount;
    }

    public Integer getLogisticsAmount() {
        return logisticsAmount;
    }

    public void setLogisticsAmount(Integer logisticsAmount) {
        this.logisticsAmount = logisticsAmount;
    }

    public String getPackageDesc() {
        return packageDesc;
    }

    public void setPackageDesc(String packageDesc) {
        this.packageDesc = packageDesc;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Integer getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(Integer couponAmount) {
        this.couponAmount = couponAmount;
    }

    public Integer getVirtualGood() {
        return virtualGood;
    }

    public void setVirtualGood(Integer virtualGood) {
        this.virtualGood = virtualGood;
    }

    public Integer getGoldPrice() {
        return goldPrice;
    }

    public void setGoldPrice(Integer goldPrice) {
        this.goldPrice = goldPrice;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Long getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    public Date getCreaterTime() {
        return createrTime;
    }

    public void setCreaterTime(Date createrTime) {
        this.createrTime = createrTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
