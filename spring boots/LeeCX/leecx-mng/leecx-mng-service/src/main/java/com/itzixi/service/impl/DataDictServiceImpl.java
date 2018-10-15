package com.itzixi.service.impl;

import cn.jpush.api.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itzixi.common.enums.YesOrNo;
import com.itzixi.common.pojo.JqGridResult;
import com.itzixi.common.utils.JsonUtils;
import com.itzixi.components.utils.RedisOperator;
import com.itzixi.mapper.DataDictMapper;
import com.itzixi.pojo.DataDict;
import com.itzixi.pojo.DataDictExample;
import com.itzixi.service.DataDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 彭文浩 on 2018/4/21.
 */
@Service
public class DataDictServiceImpl implements DataDictService {
    @Autowired
    private DataDictMapper dataDictMapper;

//	@Autowired
//	private JedisClient jedis;

    @Autowired
    private RedisOperator jedis;

    /**
     * 添加数据字典
     */
    @Override
    public void saveDataDict(DataDict dataDict) {
        dataDictMapper.insert(dataDict);
    }

    @Override
    public JqGridResult queryDataDictList(String typeName, String typeCode, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        DataDictExample dde = new DataDictExample();
        dde.setOrderByClause("type_code,ddkey asc ");
        DataDictExample.Criteria dc = dde.createCriteria();

        if (StringUtils.isNotEmpty(typeCode)) {
            dc.andTypeCodeLike("%" + typeCode + "%");
        }

        List<DataDict> userList = dataDictMapper.selectByExample(dde);

        PageInfo<DataDict> pageList = new PageInfo<DataDict>(userList);

        JqGridResult grid = new JqGridResult();
        grid.setTotal(pageList.getPages());
        grid.setRows(userList);
        grid.setPage(pageList.getPageNum());
        grid.setRecords(pageList.getTotal());
        return grid;

    }

    /**
     * 删除数据字典
     *
     * @param ddId
     */
    @Override
    public void deleteDataDictById(Integer ddId) {
        dataDictMapper.deleteByPrimaryKey(ddId);
    }


    /**
     * 根据ddId查询字典
     *
     * @param ddId
     * @return
     */
    @Override
    public DataDict queryDataDictById(Integer ddId) {
        return dataDictMapper.selectByPrimaryKey(ddId);
    }

    /**
     * 更新数据字典
     *
     * @param dataDict
     */
    @Override
    public void updateDataDictById(DataDict dataDict) {
        dataDictMapper.updateByPrimaryKey(dataDict);
    }

    /**
     * 根据 CodeKey查询
     *
     * @param typeCode
     * @param ddKey
     * @return
     */
    @Override
    public String queryDataDictValueByCodeKey(String typeCode, String ddKey) {
        String redisKey = "redis_datadict:" + typeCode + ":" + ddKey;

        String redisDdvalue = jedis.get(redisKey);
        if (StringUtils.isNotEmpty(redisDdvalue)) {
            return redisDdvalue;
        }
        DataDictExample dataDictExample = new DataDictExample();
        DataDictExample.Criteria dataDictCriteria = dataDictExample.createCriteria();
        dataDictCriteria.andTypeCodeEqualTo(typeCode);
        dataDictCriteria.andDdkeyEqualTo(ddKey);
        dataDictCriteria.andIsShowEqualTo(YesOrNo.YES.value);
        List<DataDict> list = dataDictMapper.selectByExample(dataDictExample);
        if (list != null && list.size() > 0) {
            DataDict dd = list.get(0);
            String ddvalue = dd.getDdvalue();
            // 存放到redis中去
            jedis.set(redisKey, ddvalue);
            return ddvalue;
        }
        return "";
    }

    /**
     * 根据TypeCode查询
     *
     * @param typeCode
     * @return
     */
    @Override
    public List<DataDict> queryDataDictListByTypeCode(String typeCode) {
        String redisKey = "redis_datadict_list:" + typeCode;

        String redisDdListJson = jedis.get(redisKey);
        if (StringUtils.isNotEmpty(redisDdListJson)) {
            List<DataDict> list = JsonUtils.jsonToList(redisDdListJson, DataDict.class);
            return list;
        }

        DataDictExample dataDictExample = new DataDictExample();
        DataDictExample.Criteria dataDictCriteria = dataDictExample.createCriteria();
        dataDictCriteria.andTypeCodeEqualTo(typeCode);
        dataDictCriteria.andIsShowEqualTo(YesOrNo.YES.value);
        List<DataDict> list = dataDictMapper.selectByExample(dataDictExample);

        // 存放到redis 中去
        jedis.set(redisKey, JsonUtils.objectToJson(list));

        return list;
    }
}
