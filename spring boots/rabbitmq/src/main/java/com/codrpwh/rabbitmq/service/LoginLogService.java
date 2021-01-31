package com.codrpwh.rabbitmq.service;


import com.codrpwh.rabbitmq.pojo.LoginLog;

public interface LoginLogService {

    void insert(LoginLog loginLog);

    LoginLog selectByMsgId(String msgId);

}
