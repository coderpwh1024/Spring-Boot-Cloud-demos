package com.itzixi.service;

import com.itzixi.common.pojo.JqGridResult;
import com.itzixi.pojo.DemoItem;


public interface DemoItemService {


    public void saveItem(DemoItem item);


    public JqGridResult queryItemList(Integer page, Integer pageSize);


    public DemoItem queryItemById(String itemId);


    public void updateItem(DemoItem item);


    public void deleteItem(String itemId);
}
