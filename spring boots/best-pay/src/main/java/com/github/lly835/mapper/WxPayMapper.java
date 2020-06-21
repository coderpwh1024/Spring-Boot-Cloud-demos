package com.github.lly835.mapper;

import com.github.lly835.entity.WxPay;
import com.github.lly835.entity.WxPayExample;
import org.springframework.stereotype.Repository;

/**
 * WxPayMapper继承基类
 */
@Repository
public interface WxPayMapper extends MyBatisBaseDao<WxPay, Integer, WxPayExample> {
}