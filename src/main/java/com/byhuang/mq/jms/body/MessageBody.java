package com.byhuang.mq.jms.body;

import com.byhuang.mq.util.ActiveMQSessionUtil;

import javax.jms.*;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/17 20:28
 * @description TODO
 */
public class MessageBody {

    public static final String MQ_NAME = "mq_name";

    public static void main(String[] args) throws JMSException {
        Session session = ActiveMQSessionUtil.createSession();
        Topic topic = session.createTopic(MQ_NAME);
        MessageProducer producer = session.createProducer(topic);
        for (int i = 0; i < 3; i++) {
            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("name", String.valueOf(i));
            mapMessage.setInt("age", i);
            producer.send(mapMessage);
        }
        System.out.println("msg sent!");
        producer.close();
        session.close();
    }
}
