package com.itzixi.service.impl;

import com.itzixi.common.pojo.JqGridResult;
import com.itzixi.mapper.DataDictMapper;
import com.itzixi.pojo.DataDict;
import com.itzixi.service.DataDictService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by 彭文浩 on 2018/4/21.
 */
public class DataDictServiceImpl implements DataDictService {
    @Autowired
    private DataDictMapper dataDictMapper;

//	@Autowired
//	private JedisClient jedis;

    @Autowired
//    private RedisOperator jedis;

    /**
     * 添加数据字典
     */
    @Override
    public void saveDataDict(DataDict dataDict) {
        dataDictMapper.insert(dataDict);
    }

    @Override
    public JqGridResult queryDataDictList(String typeName, String typeCode, Integer page, Integer pageSize) {
        return null;
    }

    /**
     * 删除数据字典
     * @param ddId
     */
    @Override
    public void deleteDataDictById(Integer ddId) {
        dataDictMapper.deleteByPrimaryKey(ddId);
    }


    /**
     *  根据ddId查询字典
     * @param ddId
     * @return
     */
    @Override
    public DataDict queryDataDictById(Integer ddId) {
        return dataDictMapper.selectByPrimaryKey(ddId);
    }

    /**
     * 更新数据字典
     * @param dataDict
     */
    @Override
    public void updateDataDictById(DataDict dataDict) {
        dataDictMapper.updateByPrimaryKey(dataDict);
    }

    @Override
    public String queryDataDictValueByCodeKey(String typeCode, String ddKey) {
        return null;
    }

    @Override
    public List<DataDict> queryDataDictListByTypeCode(String typeCode) {
        return null;
    }
}
