package com.coderpwh.code.service;

import com.coderpwh.code.domain.Girl;
import com.coderpwh.code.enums.ResultEnums;
import com.coderpwh.code.exeception.GirlExeception;
import com.coderpwh.code.repository.GirlRepository;
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

    public void getAge(Integer id) throws Exception {
        Girl girl = girlRepository.findOne(id);
        int age = girl.getAge();

        if (age < 10) {
            throw new GirlExeception(ResultEnums.PRIMARY_SCHOOL);
        } else if (age > 10 && age < 16) {
            throw new GirlExeception(ResultEnums.PRIMARY_SCHOOL);
        }
    }


    /**
     * 通过id 查询一个女生
     *
     * @param id
     * @return
     */
    public Girl findone(Integer id) {
        return girlRepository.findOne(id);
    }


}
