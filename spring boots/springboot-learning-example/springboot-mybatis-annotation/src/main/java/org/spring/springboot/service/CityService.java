package org.spring.springboot.service;

import org.spring.springboot.domain.City;

/***
 * 业务层接口
 */
public interface CityService {

    /**
     * 根据城市名称，查询城市信息
     * @param cityName
     */
    City findCityByName(String cityName);
}
