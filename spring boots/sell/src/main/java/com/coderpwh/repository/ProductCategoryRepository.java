package com.coderpwh.repository;

import com.coderpwh.dataObject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author coderpwh
 * @create 2019-01-01 22:26
 * @desc ${DESCRIPTION}
 **/
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
