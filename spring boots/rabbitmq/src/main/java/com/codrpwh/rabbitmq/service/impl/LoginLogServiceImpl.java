package com.codrpwh.rabbitmq.service.impl;


import com.codrpwh.rabbitmq.mapper.LoginLogMapper;
import com.codrpwh.rabbitmq.pojo.LoginLog;
import com.codrpwh.rabbitmq.service.LoginLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Resource
    private LoginLogMapper loginLogMapper;

    @Override
    public void insert(LoginLog log) {
        loginLogMapper.insert(log);
    }

    @Override
    public LoginLog selectByMsgId(String msgId) {
        return loginLogMapper.selectByMsgId(msgId);
    }

}
