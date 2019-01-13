package com.coderpwh.repository;

import com.coderpwh.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author coderpwh
 * @create 2019-01-13 22:30
 * @desc ${DESCRIPTION}
 **/

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);


}
