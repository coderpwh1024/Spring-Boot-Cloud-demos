package com.debug.mooc.dubbo.one.api.request;

import lombok.Data;
import lombok.ToString;

/**
 * @author coderpwh
 * @create 2019-06-08 23:24
 * @desc ${DESCRIPTION}
 **/
@Data
@ToString
public class PushOrderDto {
    //商品id
    private Integer itemId;

    //下单数量
    private Integer total;

    //客户姓名
    private String customerName;

}
