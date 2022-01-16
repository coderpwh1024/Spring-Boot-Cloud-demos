package com.coderpwh.kafka.consumer;

import java.util.ArrayList;
import java.util.List;

/***
 * 消费线程管理类(伪代码)
 */
public class ConsumerGroup {

    private List<ConsumerRunable> consumers;

    public ConsumerGroup(int consumerNum, String groupId, String topic, String brokerlist) {
        consumers = new ArrayList<>(consumerNum);

        for (int i = 0; i < consumerNum; ++i) {
            ConsumerRunable consumerThread = new ConsumerRunable(brokerlist, groupId, topic);
            consumers.add(consumerThread);
        }

    }

    public void execute() {

        for (ConsumerRunable task : consumers) {
            new Thread(task).start();
        }
    }

}
