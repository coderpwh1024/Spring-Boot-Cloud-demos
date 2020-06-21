package com.github.lly835.entity.dto.req;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "微信支付")
public class WxPayReq {

    @ApiModelProperty(value = "openId")
    private  String  openid;

    @ApiModelProperty(value = "orderId")
    private String orderId;

    @ApiModelProperty(value = "amount")
    private Double amount;

    @ApiModelProperty(value = "amount")
    private  String buyerLogonId;

    @ApiModelProperty(value = "buyerId")
    private String buyerId;

    @ApiModelProperty(value = "班级")
    private  String wxClass;

    @ApiModelProperty(value = "年级")
    private String grade;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "姓名")
    private String name;

    public String getWxClass() {
        return wxClass;
    }

    public void setWxClass(String wxClass) {
        this.wxClass = wxClass;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private BestPayTypeEnum payType;

    public void setPayType(BestPayTypeEnum payType) {
        this.payType = payType;
    }

    public WxPayReq() {

    }



    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }


}
