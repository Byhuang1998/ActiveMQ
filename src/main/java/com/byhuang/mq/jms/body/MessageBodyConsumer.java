package com.byhuang.mq.jms.body;

import com.byhuang.mq.util.ActiveMQSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.*;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/17 20:28
 * @description TODO
 */
public class MessageBodyConsumer {

    public static final String MQ_NAME = "mq_name";

    public static void main(String[] args) throws JMSException {
        Session session = ActiveMQSessionUtil.createSession();
        Topic topic = session.createTopic(MQ_NAME);
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (message instanceof MapMessage) {
                    MapMessage msg = (MapMessage)message;
                    try {
                        System.out.println("name: " + msg.getString("name"));
                        System.out.println("age" + msg.getInt("age"));
                    } catch (JMSException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}
