package com.debug.mooc.dubbo.two.server.controller;

import com.debug.mooc.dubbo.two.server.request.RecordPushRequest;
import com.debug.mooc.dubbo.two.server.service.OrderRecordService;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author coderpwh
 * @create 2019-06-09 13:54
 * @desc ${DESCRIPTION}
 **/
@RestController
public class OrderRecordController {

    private static final Logger log= LoggerFactory.getLogger(OrderRecordController.class);

    private static final String prefix="order";

    @Autowired
    private OrderRecordService orderRecordService;

    /**
     * 面向客户：用户下单
     */
    @RequestMapping(value = prefix+"/record/push",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String,Object> pushRecord(@RequestBody RecordPushRequest pushRequest){
        Map<String,Object> resMap= Maps.newHashMap();
        resMap.put("code",0);
        resMap.put("msg","成功");
        try {
            log.info("接收到请求数据：{} ",pushRequest);

            //TODO:处理用户下单数据-发起用户下单接口的调用
            //orderRecordService.pushOrder(pushRequest);
            orderRecordService.pushOrderV2(pushRequest);

        }catch (Exception e){
            log.error("面向客户：用户下单-发生异常：",e.fillInStackTrace());
            resMap.put("code",-1);
            resMap.put("msg","失败");
        }
        return resMap;
    }


}
