package com.debug.mooc.dubbo.one.api.service;

import com.debug.mooc.dubbo.one.api.response.BaseResponse;

/**
 * 2 * @Author: coderpwh
 * <p>
 * 3 * @Date: 2019/5/31 10:56
 * 4
 */
public interface IDubboItemService {

    BaseResponse listItems();

    BaseResponse listPageItems(Integer pageNo, Integer pageSize);

    BaseResponse listPageItemsParams(Integer pageNo, Integer pageSize, String search);


}
