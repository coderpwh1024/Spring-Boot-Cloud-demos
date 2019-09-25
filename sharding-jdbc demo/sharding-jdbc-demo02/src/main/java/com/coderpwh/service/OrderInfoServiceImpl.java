package com.coderpwh.service;

import com.coderpwh.po.OrderInfo;
import com.coderpwh.repository.OrderInfoRepository;
import com.coderpwh.vo.OrderInfoListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author coderpwh
 * @create 2019-09-15 0:05
 * @desc ${DESCRIPTION}
 **/

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoRepository orderInfoRepository;
    
     @Autowired
    private OrderGoodRepository orderGoodRepository;


    @Override
    public List<OrderInfo> selectByUserId(Long userId) {
        return orderInfoRepository.selectByUserId(userId);

    }

     /**
     * 获取用户的订单列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<OrderInfoListDto> selectOrderList(Long userId) {

        /* List<OrderInfoListDto> list = orderGoodRepository.selectOrderList(userId);*/


        List<OrderInfoListDto> list = orderGoodRepository.getGood(userId);
        return list;
    }
}
