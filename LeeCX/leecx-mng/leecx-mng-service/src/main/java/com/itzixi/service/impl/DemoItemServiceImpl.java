package com.itzixi.service.impl;

import com.itzixi.mapper.DemoItemMapper;
import com.itzixi.pojo.DemoItem;
import com.itzixi.service.DemoItemService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 彭文浩 on 2018/4/19.
 */
public class DemoItemServiceImpl implements DemoItemService {

    @Autowired
    private DemoItemMapper demoItemMapper;

    @Autowired
    private Sid sid;

    @Override
    public void saveItem(DemoItem item) {


        String itemId = sid.nextShort();

        item.setId(itemId);
        item.setAmount(item.getAmount() * 100);

        demoItemMapper.insert(item);
    }

}
