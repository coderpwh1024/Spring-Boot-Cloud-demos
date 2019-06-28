package org.spring.springboot.service;

import org.spring.springboot.domain.City;
import org.spring.springboot.domain.Order;

import java.util.List;

public interface OrderService {

    Long saveOrder(Order order);


    /**
     * AND 语句查询
     *
     * @param description
     * @param score
     * @return
     */
    List<Order> findByDescriptionAndScore(String description, Integer score);


    /**
     * OR 语句查询
     *
     * @param description
     * @param score
     * @return
     */
    List<Order> findByDescriptionOrScore(String description, Integer score);


    /**
     * 查询城市描述
     *
     * @param description
     * @return
     */
    List<Order> findByDescription(String description);


    /**
     * NOT 语句查询
     *
     * @param description
     * @return
     */
    List<Order> findByDescriptionNot(String description);

    /**
     * LIKE 语句查询
     *
     * @param description
     * @return
     */
    List<Order> findByDescriptionLike(String description);



}
