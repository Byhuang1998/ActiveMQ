package com.byhuang.mq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.xml.soap.Text;
import java.io.IOException;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/17 16:50
 * @description TODO
 */
public class JmsConsumer {

    public static final String MQ_URL = "tcp://192.168.119.130:61616";

    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        // Create consumer
        MessageConsumer consumer = session.createConsumer(queue);
        // Receive Msg
        /*
        while (true) {
            TextMessage message = (TextMessage)consumer.receive();
            if (null != message) {
                System.out.println("receive msg: " + message.getText());
            } else {
                break;
            }
        }
        */
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (null != message && message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage)message;
                    try {
                        System.out.println("receive msg: " + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        // Let Main thread keep running, otherwise once Main thread finished, MessageListener will hava to finish...
        System.in.read();
        connection.close();
        session.close();
        connection.close();
    }
}
