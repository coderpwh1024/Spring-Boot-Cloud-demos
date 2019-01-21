package com.coderpwh.controller;

import com.coderpwh.service.CategoryService;
import com.coderpwh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 买家商品
 * @author coderpwh
 * @create 2019-01-21 22:54
 * @desc ${DESCRIPTION}
 **/
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;



}
