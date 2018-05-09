package org.spring.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.dao.CityDao;
import org.spring.springboot.domain.City;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


/**
 * @author coderpwh
 * @Date: 2018/5/8.
 * @Description:
 */
@Service
public class CityServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityDao cityDao;

    @Autowired
    private RedisTemplate redisTemplate;




    @Override
    public City findCityById(Long id) {
        String key = "city_" + id;
        ValueOperations<String,String> operations = redisTemplate.opsForValue();

        boolean hasKey = redisTemplate.hasKey(key);
        // 从redis中获取
        if (hasKey) {
//             City city   = operations.get(key);
              String c = operations.get(key);
            City city =  JSON.parseObject(c,City.class);
            LOGGER.info("CityServiceImpl.findCityById():从缓存中获取了城市>>" + city.toString());
            return city;
        }

        // 从数据库中获取后存入到redis
        City city = cityDao.findById(id);
//          JSON.toJSONString(city);
        operations.set(key, JSON.toJSONString(city), 1000000000, TimeUnit.SECONDS);
        return city;
    }

    @Override
    public Long saveCity(City city) {
        return cityDao.saveCity(city);
    }

    /**
     * 修改，
     * 先修改数据库，如果redis中存在则修改，如果不存在就不处理
     *
     * @param city
     * @return
     */
    @Override
    public Long updateCity(City city) {
        Long ret = cityDao.updateCity(city);

        String key = "city_" + city.getId();
        boolean haskey = redisTemplate.hasKey(key);
        if (haskey) {
            redisTemplate.delete(key);
            LOGGER.info("CityServiceImpl.updateCity():从缓存中删除城市>>" + city.toString());
        }
        return ret;
    }

    @Override
    public Long deleteCity(Long id) {

        Long ret = cityDao.deleteCity(id);

        String key = "city_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            LOGGER.info("CityServiceImpl.deleteCity() : 从缓存中删除城市 ID >> " + id);
        }

        return ret;

    }
}
