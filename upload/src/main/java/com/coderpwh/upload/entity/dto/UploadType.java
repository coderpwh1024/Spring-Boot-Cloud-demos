package com.coderpwh.upload.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description:
 * @author: pengwenhao
 * @date: 2020/5/8 0008 18:59
 */

@ApiModel(value = "产品类型入参")
public class UploadType {

    @ApiModelProperty(value = "产品线编号", example = "ISV")
    private String businessid;

    public String getBusinessid() {
        return businessid;
    }

    public void setBusinessid(String businessid) {
        this.businessid = businessid;
    }
}
