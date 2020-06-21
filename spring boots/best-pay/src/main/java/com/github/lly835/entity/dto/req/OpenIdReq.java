package com.github.lly835.entity.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@ApiModel(value = "获取微信openid入参")
@Data
public class OpenIdReq implements Serializable {

    @ApiModelProperty(value = "appId", position = 1)
    @NonNull
    private String appid;

    @ApiModelProperty(value = "secret", position = 2)
    @NonNull
    private String secret;

    @ApiModelProperty(value = "code", position = 3)
    @NonNull
    private String code;

    @ApiModelProperty(value = "grant_type", position = 4)
    @NonNull
    private String grant_type;

    public OpenIdReq() {
    }

    public OpenIdReq(@NonNull String appid, @NonNull String secret, @NonNull String code, @NonNull String grant_type) {
        this.appid = appid;
        this.secret = secret;
        this.code = code;
        this.grant_type = grant_type;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }
}
