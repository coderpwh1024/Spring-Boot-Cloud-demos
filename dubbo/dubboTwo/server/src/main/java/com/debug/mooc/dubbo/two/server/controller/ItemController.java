package com.debug.mooc.dubbo.two.server.controller;

import com.debug.mooc.dubbo.one.api.response.BaseResponse;
import com.debug.mooc.dubbo.one.api.service.IDubboItemService;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author coderpwh
 * @create 2019-06-03 22:07
 * @desc ${DESCRIPTION}
 **/

@RestController
public class ItemController {


    private static final Logger log= LoggerFactory.getLogger(ItemController.class);

    private static final String prefix="item";

    @Autowired
    private IDubboItemService dubboItemService;


    @RequestMapping(value = prefix+"/page/list",method = RequestMethod.GET)
    public Map<String,Object> pageList(Integer pageNo,Integer pageSize){
        if (pageNo==null || pageSize==null || pageNo<=0 || pageSize<=0){
            pageNo=1;
            pageSize=2;
        }

        Map<String,Object> resMap= Maps.newHashMap();
        resMap.put("code","0");
        resMap.put("msg","成功");

        //TODO:调用服务提供方dubboOne提供的列表查询-分页查询功能
        try {
            BaseResponse response=dubboItemService.listPageItems(pageNo,pageSize);
            if (response!=null && response.getCode().equals(0)){
                resMap.put("data",response.getData());

            }
        }catch (Exception e){
            e.printStackTrace();
            resMap.put("code","-1");
            resMap.put("msg","失败");
        }
        return resMap;
    }



}
