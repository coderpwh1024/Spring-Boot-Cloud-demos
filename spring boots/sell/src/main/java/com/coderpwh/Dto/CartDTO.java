package com.coderpwh.Dto;

import lombok.Data;

/**
 *  购物车
 * @author coderpwh
 * @create 2019-01-15 22:50
 * @desc ${DESCRIPTION}
 **/
@Data
public class CartDTO {
    /** 商品Id. */
    private String productId;

    /** 数量. */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
