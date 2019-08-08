package com.coderpwh.po;


import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单信息表
 * </p>
 *
 * @author coderpwh
 * @since 2019-07-13
 */

public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long orderId;


    private String orderNo;


    private Integer orderType;


    private Integer businessId;


    private Integer orderStatus;


    private Integer orderAmount;


    private Integer goodAmount;


    private Integer discountAmount;


    private Integer couponAmount;


    private Integer expressAmount;


    private Date expirtTime;


    private String orderSource;


    private String consumerDesc;


    private Integer goldPrice;


    private Boolean memberOrder;


    private Boolean hasAfterSale;


    private Integer goldNum;


    private Integer delFlag;


    private String passingData;


    private Long createrId;


    private Date createrTime;


    private Date modifyTime;

    public OrderInfo() {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
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

    public Integer getExpressAmount() {
        return expressAmount;
    }

    public void setExpressAmount(Integer expressAmount) {
        this.expressAmount = expressAmount;
    }

    public Date getExpirtTime() {
        return expirtTime;
    }

    public void setExpirtTime(Date expirtTime) {
        this.expirtTime = expirtTime;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getConsumerDesc() {
        return consumerDesc;
    }

    public void setConsumerDesc(String consumerDesc) {
        this.consumerDesc = consumerDesc;
    }

    public Integer getGoldPrice() {
        return goldPrice;
    }

    public void setGoldPrice(Integer goldPrice) {
        this.goldPrice = goldPrice;
    }

    public Boolean getMemberOrder() {
        return memberOrder;
    }

    public void setMemberOrder(Boolean memberOrder) {
        this.memberOrder = memberOrder;
    }

    public Boolean getHasAfterSale() {
        return hasAfterSale;
    }

    public void setHasAfterSale(Boolean hasAfterSale) {
        this.hasAfterSale = hasAfterSale;
    }

    public Integer getGoldNum() {
        return goldNum;
    }

    public void setGoldNum(Integer goldNum) {
        this.goldNum = goldNum;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getPassingData() {
        return passingData;
    }

    public void setPassingData(String passingData) {
        this.passingData = passingData;
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
