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


    @Override
    public List<OrderInfo> selectByUserId(Long userId) {
        return orderInfoRepository.selectByUserId(userId);

    }

    @Override
    public List<OrderInfoListVo> selectOrderList(Long userId) {
        return null;
    }
}
