package com.coderpwh.controller;

import com.coderpwh.po.OrderInfo;
import com.coderpwh.service.OrderInfoService;
import com.coderpwh.vo.OrderInfoListVo;
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


    @RequestMapping(value = "/orderList", method = RequestMethod.GET)
    public Object getOrderList(@NotNull Long userId) {

         List<OrderInfoListVo> list = orderInfoService.selectOrderList(userId);

        return null;
    }

    // todo 根据用户id ,订单号 查看订单详情

    @RequestMapping(value = "/get/orderDeatail")
    public Object getOrderDetail(@RequestBody OrderDetailVo orderDetailVo) {
        List<OrderDetailDto> list = orderInfoService.selectOrderDetail(orderDetailVo);
        return list;
    }

    // todo  根据订单号查看订单详情(无订单号)

    // todo 根据用户手机号模糊搜索订单


}
