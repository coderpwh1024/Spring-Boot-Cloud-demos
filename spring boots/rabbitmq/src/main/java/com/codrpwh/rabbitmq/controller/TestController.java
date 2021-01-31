package com.codrpwh.rabbitmq.controller;

import com.codrpwh.rabbitmq.common.ServerResponse;
import com.codrpwh.rabbitmq.mq.producer.BusinessMessageSender;
import com.codrpwh.rabbitmq.mq.producer.DelayMessageSender;
import com.codrpwh.rabbitmq.pojo.Mail;
import com.codrpwh.rabbitmq.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping(value = "/test")
@Slf4j
public class TestController {


    @Resource
    private TestService testService;

    @Resource
    private BusinessMessageSender sender;

    @Resource
    private DelayMessageSender delayMessageSender;


    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public ServerResponse sendMail(@Validated @RequestBody Mail mail, Errors errors) {

        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ServerResponse.error(msg);
        }
        return testService.send(mail);
    }


    @RequestMapping(value = "/sendmsg", method = RequestMethod.GET)
    public void sendMsg(String msg) {
        sender.sendMsg(msg);
    }


    @RequestMapping(value = "/delay/msg", method = RequestMethod.GET)
    public void sendMesg(String msg, Integer delayType) {
        log.info("当前时间：{},收到请求，msg:{},delayType:{}", new Date(), msg, delayType);
        delayMessageSender.sendMsg(msg, delayType);
    }


    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public void sendMessage(String message, Integer delayType) {
        log.info("当前时间为:{},收到请求,msg:{} delayType:{}", new Date(),message, delayType);
        delayMessageSender.sendMsgs(message, delayType);
    }


}
