package com.github.lly835.entity.dto.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "加密返回参数")
public class EncryptResponseDTO implements Serializable {


    @ApiModelProperty(value = "签名", example = "ddar3242134", position = 1)
    private String sing;

    @ApiModelProperty(value = "时间戳", example = "523423", position = 2)
    private String timestamp;

    @ApiModelProperty(value = "随机字符串", example = "523423", position = 2)
    private String nonceStr;

}
