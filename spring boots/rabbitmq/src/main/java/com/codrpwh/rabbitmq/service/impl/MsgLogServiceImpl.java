package com.codrpwh.rabbitmq.service.impl;


import com.codrpwh.rabbitmq.mapper.MsgLogMapper;
import com.codrpwh.rabbitmq.pojo.MsgLog;
import com.codrpwh.rabbitmq.service.MsgLogService;
import com.codrpwh.rabbitmq.util.JodaTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MsgLogServiceImpl implements MsgLogService {

    @Resource
    private MsgLogMapper msgLogMapper;

    @Override
    public void updateStatus(String msgId, Integer status) {
        MsgLog msgLog = new MsgLog();
        msgLog.setMsgId(msgId);
        msgLog.setStatus(status);
        msgLog.setUpdateTime(new Date());
        msgLogMapper.updateStatus(msgLog);
    }

    @Override
    public MsgLog selectByMsgId(String msgId) {
        return msgLogMapper.selectByPrimaryKey(msgId);
    }

    @Override
    public List<MsgLog> selectTimeoutMsg() {
        return msgLogMapper.selectTimeoutMsg();
    }

    @Override
    public void updateTryCount(String msgId, Date tryTime) {
        Date nextTryTime = JodaTimeUtil.plusMinutes(tryTime, 1);

        MsgLog msgLog = new MsgLog();
        msgLog.setMsgId(msgId);
        msgLog.setNextTryTime(nextTryTime);

        msgLogMapper.updateTryCount(msgLog);
    }

}
