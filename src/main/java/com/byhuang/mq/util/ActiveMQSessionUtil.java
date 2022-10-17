package com.byhuang.mq.util;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/17 19:49
 * @description TODO
 */
public class ActiveMQSessionUtil {

    public static final String URL = "tcp://192.168.119.130:61616";

    public static Session createSession() throws JMSException {

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
        Connection connection = factory.createConnection();
        connection.start();
        return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }
}
