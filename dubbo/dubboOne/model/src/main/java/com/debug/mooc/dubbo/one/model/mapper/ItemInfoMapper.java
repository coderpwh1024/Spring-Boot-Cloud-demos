package com.debug.mooc.dubbo.one.model.mapper;

import com.debug.mooc.dubbo.one.model.entity.ItemInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 2 * @Author: coderpwh
 * 3 * @Date: 2019/5/31 10:48
 * 4
 */
public interface ItemInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ItemInfo record);

    int insertSelective(ItemInfo record);

    ItemInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemInfo record);

    int updateByPrimaryKey(ItemInfo record);

    List<ItemInfo> selectAll();

    List<ItemInfo> selectAllByParams(@Param("search") String search);
}
