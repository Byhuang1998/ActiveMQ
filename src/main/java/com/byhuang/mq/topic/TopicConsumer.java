package com.byhuang.mq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/17 17:33
 * @description TODO
 */
public class TopicConsumer {

    public static final String MQ_URL = "tcp://192.168.119.130:61616";

    public static final String TOPIC_NAME = "topic01";

    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        // Create consumer
        MessageConsumer consumer = session.createConsumer(topic);
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
                if (message instanceof TextMessage) {
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