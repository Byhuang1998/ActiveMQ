package com.byhuang.mq.jms.head;

import com.byhuang.mq.util.ActiveMQSessionUtil;
import org.springframework.jmx.export.assembler.MethodNameBasedMBeanInfoAssembler;

import javax.jms.*;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/17 20:04
 * @description TODO
 */
public class MessageHeadConsumer {

    public static final String MQ_NAME = "mq_name";

    public static void main(String[] args) throws JMSException {
        Session session = ActiveMQSessionUtil.createSession();
        Queue queue = session.createQueue(MQ_NAME);
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener((msg) -> {
            TextMessage textMessage = (TextMessage)msg;
            try {
                System.out.println("receive msg: " + textMessage.getText() + "msg ID is: " + textMessage.getJMSMessageID());

            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

    }
}
