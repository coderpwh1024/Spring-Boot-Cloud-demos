package com.coderpwh.code.controller;

import com.coderpwh.code.aspect.HttpAspect;
import com.coderpwh.code.domain.Girl;
import com.coderpwh.code.domain.Result;
import com.coderpwh.code.repository.GirlRepository;
import com.coderpwh.code.service.GirlService;
import com.coderpwh.code.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author coderpwh
 * @Date: 2018/1/27.
 * @Description:
 */

@RestController
public class GirlController {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

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
        logger.info("girlList");
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
     * 表单验证
     *
     * @param girl
     * @return
     */
    @PostMapping(value = "girls/insert")
    public Result<Girl> girlInsert(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        girl.setCupSize(girl.getCupSize());
        girl.setAge(girl.getAge());

        return ResultUtil.success(girlRepository.save(girl));

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

    @GetMapping(value = "/girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception {
        girlService.getAge(id);
    }

}
