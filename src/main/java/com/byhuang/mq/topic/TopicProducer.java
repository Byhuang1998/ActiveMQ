package com.byhuang.mq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/17 17:28
 * @description TODO
 */
public class TopicProducer {

    public static final String MQ_URL = "tcp://192.168.119.130:61616";

    public static final String TOPIC_NAME = "topic01";

    public static void main(String[] args) throws JMSException {
        // Create a Connection Factory
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MQ_URL);
        // Create a Connection with Factory
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        // Create a session with Connection
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // Create Destination(queue or topic)
        Topic topic = session.createTopic(TOPIC_NAME);
        // Create Producer
        MessageProducer producer = session.createProducer(topic);
        // Send Msg
        for (int i = 0; i < 3; i++) {
            // Create Msg
            TextMessage message = session.createTextMessage("msg: " + i);
            // Send Msg
            producer.send(message);
        }
        // Close resources
        producer.close();
        session.close();
        connection.close();
        System.out.println("message sent!");
    }
}