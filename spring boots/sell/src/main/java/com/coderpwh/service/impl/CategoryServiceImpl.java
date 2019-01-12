package com.coderpwh.service.impl;

import com.coderpwh.dataobject.ProductCategory;
import com.coderpwh.repository.ProductCategoryRepository;
import com.coderpwh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @author coderpwh
 * @create 2019-01-09 23:42
 * @desc ${DESCRIPTION}
 **/
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository repository;


    @Override
    public ProductCategory findOne(Integer categoryId) {

        return repository.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {

        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {

        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {

        return repository.save(productCategory);
    }
}
