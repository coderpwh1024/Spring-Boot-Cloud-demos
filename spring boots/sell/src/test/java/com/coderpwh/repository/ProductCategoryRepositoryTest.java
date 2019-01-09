package com.coderpwh.repository;

import com.coderpwh.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by coderpwh
 * 2018-12-20 14:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;


    public void findOneTest() {
        ProductCategory productCategory = repository.findOne(1);
        System.out.println(productCategory.toString());
    }

    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        Date date = new Date();
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(3);
        productCategory.setCategoryId(2);
        productCategory.setCreateTime(date);
        productCategory.setUpdateTime(date);
        repository.save(productCategory);
    }

    @Test
    public void saveTest2() {
        ProductCategory productCategory = new ProductCategory("女神最爱", 3);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
    }


    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(2, 3, 4);

        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }
}