package com.github.lly835.mapper;

import com.github.lly835.entity.WxUser;
import com.github.lly835.entity.WxUserExample;
import org.springframework.stereotype.Repository;

/**
 * WxUserMapper继承基类
 */
@Repository
public interface WxUserMapper extends MyBatisBaseDao<WxUser, Integer, WxUserExample> {
}