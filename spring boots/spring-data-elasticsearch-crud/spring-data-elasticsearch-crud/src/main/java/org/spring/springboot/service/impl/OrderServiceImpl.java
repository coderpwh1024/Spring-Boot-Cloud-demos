package org.spring.springboot.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.domain.Order;
import org.spring.springboot.repository.OrderRepository;
import org.spring.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityESServiceImpl.class);

    // 分页参数 -> TODO 代码可迁移到具体项目的公共 common 模块
    private static final Integer pageNumber = 0;
    private static final Integer pageSize = 10;
    Pageable pageable = new PageRequest(pageNumber, pageSize);


    @Autowired
    private OrderRepository orderRepository;


    /**
     * 保存订单
     *
     * @param order
     * @return
     */
    @Override
    public Long saveOrder(Order order) {
        Order orderInfo = orderRepository.save(order);
        return orderInfo.getId();

    }

    /**
     * and
     *
     * @param description
     * @param score
     * @return
     */
    @Override
    public List<Order> findByDescriptionAndScore(String description, Integer score) {
        return orderRepository.findByDescriptionAndScore(description, score);
    }

    /**
     * or
     *
     * @param description
     * @param score
     * @return
     */
    @Override
    public List<Order> findByDescriptionOrScore(String description, Integer score) {
        return orderRepository.findByDescriptionOrScore(description, score);
    }

    @Override
    public List<Order> findByDescription(String description) {
        return orderRepository.findByDescription(description, pageable).getContent();
    }


    @Override
    public List<Order> findByDescriptionNot(String description) {
        return orderRepository.findByDescriptionNot(description, pageable).getContent();
    }


    @Override
    public List<Order> findByDescriptionLike(String description) {
        return orderRepository.findByDescriptionLike(description, pageable).getContent();
    }
}
