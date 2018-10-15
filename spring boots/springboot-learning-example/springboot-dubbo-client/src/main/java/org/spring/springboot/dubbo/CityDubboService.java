package org.spring.springboot.dubbo;

import org.spring.springboot.domain.City;

/**
 * @author coderpwh
 * @Date: 2018/5/9.
 * @Description:
 */
public interface CityDubboService {

     City findCityName(String cityName);
}
