package com.coderpwh.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author coderpwh
 * @Date: 2018/1/27.
 * @Description:
 */

@RestController
public class GirlController {


    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    /***
     * 查询所有的女生列表
     * @return
     */
    @GetMapping(value = "/girls")
    public List<Girl> girlList() {

        return girlRepository.findAll();
    }


    /**
     * 新增一个女生
     *
     * @param cupSize
     * @param age
     * @return
     */
    @PostMapping(value = "/girls/add")
    public Girl girlAdd(@RequestParam("cupSize") String cupSize, @RequestParam("age") Integer age) {
        Girl girl = new Girl();
        girl.setAge(age);
        girl.setCupSize(cupSize);
        return girlRepository.save(girl);
    }

    /**
     * 查询一个美眉
     *
     * @param id
     * @return
     */
    @GetMapping("/selectgirls/{id}")
    public Girl selectGirl(@PathVariable("id") Integer id) {
        return girlRepository.findOne(id);
    }

    /***
     *  更新美眉
     * @param id
     * @param cupSize
     * @param age
     * @return
     */
    @PostMapping("/updategirls")
    public Girl updateGirl(@RequestParam("id") Integer id, @RequestParam("cupSize") String cupSize, @RequestParam("age") Integer age) {
        Girl girl = girlRepository.findOne(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return girlRepository.save(girl);
    }

    /**
     * 删除美眉
     *
     * @param id
     */
    @GetMapping("/deletegirls")
    public void deleteGirl(@RequestParam("id") Integer id) {
        girlRepository.delete(id);
    }

    /**
     * 通过age来查询美眉
     *
     * @param
     * @return
     */
    @GetMapping("/select/age")
    public List<Girl> selectGirlByAge(@RequestParam("age") Integer age) {
        return girlRepository.findByAge(age);

    }

    /**
     * 通过cupSize 来查询
     *
     * @param cupSize
     * @return
     */
    @GetMapping("/select/cupSize")
    public List<Girl> selectGirlBycupSize(@RequestParam("cupSize") String cupSize) {

        return girlRepository.findBycupSize(cupSize);
    }

    @PostMapping(value = "/girls/two")
    public void insertTwo() {

        girlService.insertTwo();
    }

}
