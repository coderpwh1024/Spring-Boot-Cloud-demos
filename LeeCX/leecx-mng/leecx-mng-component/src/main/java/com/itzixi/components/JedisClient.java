package com.itzixi.components;

/**
 * Created by 彭文浩 on 2018/4/21.
 */
public interface JedisClient {

    public String set(String key, String value);
    public String get(String key);
    public Long del(String key);
    public Long hset(String key, String item, String value);
    public String hget(String key, String item);
    public Long hdel(String key, String item);
    public Long incr(String key);
    public Long decr(String key);


    /**
     * 设置存活时间
     * @param key
     * @param second
     * @return
     */
    public long expire(String key,int second);


    /**
     * 判断key多久过期
     *
     * @param key
     * @return
     * 	>= 0 	剩余秒数
     * 			= -1	永久存活
     * 			= -2	已经消除
     */
    public Long ttl(String key);
}
