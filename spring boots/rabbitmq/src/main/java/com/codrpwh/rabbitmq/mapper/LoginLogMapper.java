package com.codrpwh.rabbitmq.mapper;


import com.codrpwh.rabbitmq.pojo.LoginLog;

public interface LoginLogMapper {

    void insert(LoginLog loginLog);

    LoginLog selectByMsgId(String msgId);

}
