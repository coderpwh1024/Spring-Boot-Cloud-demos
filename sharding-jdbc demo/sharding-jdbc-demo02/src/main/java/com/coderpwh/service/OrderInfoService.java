package com.coderpwh.service;

import com.coderpwh.po.OrderInfo;

import java.util.List;

/**
 * @author coderpwh
 * @create 2019-09-15 0:04
 * @desc ${DESCRIPTION}
 **/
public interface OrderInfoService {


    /**
     * 通过用户id 查询
     *
     * @param userId
     * @return
     */
    public List<OrderInfo> selectByUserId(Long userId);


}
