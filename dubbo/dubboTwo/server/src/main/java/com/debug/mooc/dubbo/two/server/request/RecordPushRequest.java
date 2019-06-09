package com.debug.mooc.dubbo.two.server.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author coderpwh
 * @create 2019-06-09 13:55
 * @desc ${DESCRIPTION}
 **/
@Data
@ToString
public class RecordPushRequest implements Serializable {
    //商品id
    private Integer itemId;

    //下单数量
    private Integer total;

    //客户姓名
    private String customerName;
}
