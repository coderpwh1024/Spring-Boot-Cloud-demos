package com.debug.mooc.dubbo.two.server.data;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author coderpwh
 * @create 2019-06-09 14:00
 * @desc ${DESCRIPTION}
 **/
@Data
@ToString
public class DubboRecordResponse implements Serializable {

    private Integer code;

    private String msg;

    private Integer data;

}
