package org.spring.springboot.controller;

import org.spring.springboot.domain.City;
import org.spring.springboot.domain.Order;
import org.spring.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/api/order/save", method = RequestMethod.POST)
    public Long saveOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    /**
     * AND 语句查询
     *
     * @param description
     * @param score
     * @return
     */
    @RequestMapping(value = "/api/order/and/find", method = RequestMethod.GET)
    public List<Order> findByDescriptionAndScore(@RequestParam(value = "description") String description,
                                                 @RequestParam(value = "score") Integer score) {

        return orderService.findByDescriptionAndScore(description, score);
    }


    /**
     * OR 语句查询
     *
     * @param description
     * @param score
     * @return
     */
    @RequestMapping(value = "/api/order/or/find", method = RequestMethod.GET)
    public List<Order> findByDescriptionOrScore(@RequestParam(value = "description") String description,
                                                @RequestParam(value = "score") Integer score) {
        return orderService.findByDescriptionOrScore(description, score);
    }


    /**
     * 查询城市描述
     *
     * @param description
     * @return
     */
    @RequestMapping(value = "/api/order/description/find", method = RequestMethod.GET)
    public List<Order> findByDescription(@RequestParam(value = "description") String description) {
        return orderService.findByDescription(description);
    }


    /**
     * NOT 语句查询
     *
     * @param description
     * @return
     */
    @RequestMapping(value = "/api/order/description/not/find", method = RequestMethod.GET)
    public List<Order> findByDescriptionNot(@RequestParam(value = "description") String description) {
        return orderService.findByDescriptionNot(description);
    }


    /**
     * LIKE 语句查询
     *
     * @param description
     * @return
     */
    @RequestMapping(value = "/api/order/like/find", method = RequestMethod.GET)
    public List<Order> findByDescriptionLike(@RequestParam(value = "description") String description) {
        return orderService.findByDescriptionLike(description);
    }


}
