package com.codrpwh.rabbitmq.service;


import com.codrpwh.rabbitmq.common.ServerResponse;
import com.codrpwh.rabbitmq.dto.FundDto;
import com.codrpwh.rabbitmq.dto.SearchFormDto;

import java.util.List;

public interface FundService {

    ServerResponse rank(FundDto fundDto);

    ServerResponse search(SearchFormDto searchFormDto);

    void update(List<FundDto> list);

    ServerResponse getSearchFormDto();


}
