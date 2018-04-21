package com.itzixi.service;

import com.itzixi.common.pojo.JqGridResult;
import com.itzixi.pojo.DataDict;

import java.util.List;

/**
 * Created by 彭文浩 on 2018/4/21.
 */
public interface DataDictService {

    /**
     * 添加数据字典
     * @param dataDict
     */
    public void saveDataDict(DataDict dataDict);


    /**
     * 查询数据字典列表
     * @param typeName
     * @param typeCode
     * @param page
     * @param pageSize
     * @return
     */
    public JqGridResult queryDataDictList(String typeName, String typeCode, Integer page, Integer pageSize);

    /**
     * 删除数据字典
     * @param ddId
     */
    public void deleteDataDictById(Integer ddId);


    /**
     * 查询数据字典
     * @param ddId
     * @return
     */
    public DataDict queryDataDictById(Integer ddId);


    /**
     * 修改数据字典
     * @param dataDict
     */
    public void updateDataDictById(DataDict dataDict);

    /***
     * 根据类型码和ddkey查询ddvalue
     * @param typeCode
     * @param ddKey
     * @return
     */
    public  String queryDataDictValueByCodeKey(String typeCode, String ddKey);


    /**
     *  根据字典码获取数据字典的列表
     * @param typeCode
     * @return
     */
    public List<DataDict> queryDataDictListByTypeCode(String typeCode);



}
