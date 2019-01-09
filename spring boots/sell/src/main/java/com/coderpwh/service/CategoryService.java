package com.coderpwh.service;

import com.coderpwh.dataobject.ProductCategory;

import java.util.List;

/**
 * 类名
 *
 * @author coderpwh
 * @create 2019-01-09 23:41
 * @desc ${DESCRIPTION}
 **/
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);


}
