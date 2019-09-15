package com.coderpwh.controller;

import com.coderpwh.po.OrderInfo;
import com.coderpwh.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author coderpwh
 * @create 2019-09-15 0:03
 * @desc ${DESCRIPTION}
 **/
@RestController
@RequestMapping("/order/orderInfo")
public class OrderInfoController {


    @Autowired
    private OrderInfoService orderInfoService;

    @RequestMapping(value = "/get/OrderInfoList", method = RequestMethod.GET)
    public Object selectByUserId(@NotNull Long userId) {
        List<OrderInfo> list = orderInfoService.selectByUserId(userId);
        return list;
    }


}
