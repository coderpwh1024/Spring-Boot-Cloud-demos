package com.coderpwh.repository;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author coderpwh
 * @create 2019-09-18 23:08
 * @desc ${DESCRIPTION}
 **/
@Mapper
public interface OrderGoodRepository {
    List<OrderInfoListDto> selectOrderList(Long userId);

    List<OrderInfoListDto> getGood(Long userId);
}
