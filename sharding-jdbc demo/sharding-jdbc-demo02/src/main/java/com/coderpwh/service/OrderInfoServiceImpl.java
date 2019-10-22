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
    
      @Autowired
    private OrderPackageRepository orderPackageRepository;


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
    
     /**
     * 订单详情
     *
     * @param orderDetailVo
     * @return
     */
    @Override
    public List<OrderDetailDto> selectOrderDetail(OrderDetailVo orderDetailVo) {
        List<OrderDetailDto> list = new ArrayList<>();
        //  获取包裹号
        List<String> packageList  = orderPackageRepository.selectByUserIdOrderNo(orderDetailVo.getOrderNo(), orderDetailVo.getUserId());
        
        return null;
    }

    
}
