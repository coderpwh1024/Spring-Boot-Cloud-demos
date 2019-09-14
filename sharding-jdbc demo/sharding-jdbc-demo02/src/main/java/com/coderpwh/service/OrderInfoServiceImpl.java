package com.coderpwh.service;

import com.coderpwh.po.OrderInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author coderpwh
 * @create 2019-09-15 0:05
 * @desc ${DESCRIPTION}
 **/

@Service
public class OrderInfoServiceImpl implements  OrderInfoService {




    @Override
    public List<OrderInfo> selectByUserId(Long userId) {
        return null;
    }
}
