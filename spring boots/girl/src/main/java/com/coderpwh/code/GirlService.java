package com.coderpwh.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 业务层
 *
 * @author coderpwh
 * @Date: 2018/1/27.
 * @Description:
 */
@Service
@Transactional
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;



    public void insertTwo() {
        Girl girlA = new Girl();
        girlA.setAge(29);
        girlA.setCupSize("BB");

        girlRepository.save(girlA);

        Girl girlB = new Girl();
        girlB.setAge(28);
        girlB.setCupSize("BBB");

        girlRepository.save(girlB);

    }
}
