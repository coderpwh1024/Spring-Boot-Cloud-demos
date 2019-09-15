package com.coderpwh.repository;

import com.coderpwh.po.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author coderpwh
 * @create 2019-08-13 15:07
 * @desc ${DESCRIPTION}
 **/
@Mapper
public interface OrderInfoRepository {

    /**

     * 通过用户id 查询
     *
     * @param createrId
     * @return
     */
    public List<OrderInfo> selectByUserId(Long createrId);


}
