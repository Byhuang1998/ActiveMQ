package com.byhuang.mq.jms.head;

import com.byhuang.mq.util.ActiveMQSessionUtil;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/17 19:46
 * @description the producer of a message can set the message head.
 */
public class MessageHead {

    public static final String MQ_NAME = "mq_name";

    public static void main(String[] args) throws JMSException {

        Session session = ActiveMQSessionUtil.createSession();
        Queue queue = session.createQueue(MQ_NAME);
        MessageProducer producer = session.createProducer(queue);

        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("msg" + i);
            // set the destination of the msg
            message.setJMSDestination(queue);
            // set persistent mode or non-persistent mode
            message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
            // set expiration time
            message.setJMSExpiration(3000);
            // set message id
            message.setJMSMessageID("id00" + i);
            // set message priority
            message.setJMSPriority(4);
            producer.send(message);
        }
    }
}
