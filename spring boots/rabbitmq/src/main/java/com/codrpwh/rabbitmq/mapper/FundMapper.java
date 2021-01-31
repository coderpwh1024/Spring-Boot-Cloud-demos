package com.codrpwh.rabbitmq.mapper;


import com.codrpwh.rabbitmq.dto.FundDto;
import com.codrpwh.rabbitmq.pojo.Fund;
import com.codrpwh.rabbitmq.service.batch.BatchProcessMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundMapper extends BatchProcessMapper<Fund> {

    List<Fund> selectAll();

    Fund selectOne(Integer id);

    void insert(Fund fund);

    void update(Fund fund);

    void delete(Integer id);

    Fund selectByCodeAndType(Fund fund);

    List<Fund> selectByType(FundDto fundDto);

    List<Integer> selectFundTypeList();

    List<Fund> selectByNameOrCode(FundDto fundDto);

}
