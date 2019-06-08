package com.debug.mooc.dubbo.one.api.service;

import com.debug.mooc.dubbo.one.api.request.PushOrderDto;
import com.debug.mooc.dubbo.one.api.response.BaseResponse;


/**
 * @author coderpwh
 * @create 2019-06-08 23:24
 * @desc ${DESCRIPTION}
 **/
public interface IDubboRecordService {

    public BaseResponse pushOrder(PushOrderDto dto);

}
