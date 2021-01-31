package com.codrpwh.rabbitmq.service;


import com.codrpwh.rabbitmq.common.ServerResponse;
import com.codrpwh.rabbitmq.pojo.Mail;

public interface TestService {

    ServerResponse testIdempotence();

    ServerResponse accessLimit();

    ServerResponse send(Mail mail);
}
