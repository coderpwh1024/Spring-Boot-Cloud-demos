package com.itzixi.web.controller;


import com.itzixi.common.pojo.JqGridResult;
import com.itzixi.common.utils.LeeJSONResult;
import com.itzixi.pojo.DataDict;
import com.itzixi.service.DataDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author coderpwh
 * @Date: 2018/4/23.
 * @Description:
 */
@Controller
@RequestMapping("/dataDict")
public class DataDictController extends BaseController {

    final static Logger log = LoggerFactory.getLogger(DataDictController.class);


    @Autowired
    private DataDictService dataDictService;

    @RequestMapping("/showCreateDataDictPage")
    public String showCreateUserPage(String userId, HttpServletRequest request) {

        log.debug("显示数组字典数组");
        return "dataDict/createDataDict";
    }

    /**
     * 新增与修改
     *
     * @param dd
     * @return
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public LeeJSONResult saveOrUpdate(DataDict dd) {
        Integer ddId = dd.getId();
        if (ddId != null && ddId > 0) {
            dataDictService.updateDataDictById(dd);
        } else {
            dataDictService.saveDataDict(dd);
        }
        return LeeJSONResult.ok();
    }

    /**
     * 显示数组字典信息列表页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/showDataDictListPage")
    public String showDataDictListPage(HttpServletRequest request) {
        return "dataDict/dataDictList";
    }

    /**
     * 条件查询
     *
     * @param dataDict
     * @param page
     * @return
     */
    @RequestMapping("/getDataDictList")
    @ResponseBody
    public JqGridResult getDataDictList(DataDict dataDict, Integer page) {

        if (page == null) {
            page = 1;
        }
        JqGridResult result = dataDictService.queryDataDictList(null, null, page, pageSize);
        return result;
    }

    /**
     * 修改数据字典页面
     *
     * @param ddId
     * @param request
     * @return
     */
    @RequestMapping("/modifyDataDict")
    public ModelAndView modifyDataDict(Integer ddId, HttpServletRequest request) {

        DataDict dataDict = dataDictService.queryDataDictById(ddId);

        ModelAndView mv = new ModelAndView("dataDict/modifyDataDict");
        mv.addObject("dataDict", dataDict);

        return mv;
    }

    /**
     * 删除字典
     *
     * @param ddId
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public LeeJSONResult delete(Integer ddId) {

        dataDictService.deleteDataDictById(ddId);

        return LeeJSONResult.ok();
    }

    @RequestMapping("/queryDataDictValue")
    @ResponseBody
    public LeeJSONResult queryDataDictValue(String typeCode, String ddkey){

        String ddvalue = dataDictService.queryDataDictValueByCodeKey(typeCode, ddkey);

        return LeeJSONResult.ok(ddvalue);
    }

}
