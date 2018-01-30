package com.coderpwh.code;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author coderpwh
 * @Date: 2018/1/27.
 * @Description:
 */
public interface GirlRepository extends JpaRepository<Girl, Integer> {

    /***
     *  通过age 来查询美眉
     * @param age
     * @return
     */
    public List<Girl> findByAge(Integer age);


    /**
     * 通过cupSize 来查询美眉
     *
     * @return
     */
   public List<Girl> findBycupSize(String cupSize);

}
