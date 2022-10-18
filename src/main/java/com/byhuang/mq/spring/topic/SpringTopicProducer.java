package com.byhuang.mq.spring.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.MapMessage;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/18 14:32
 * @description TODO
 */
@Service
public class SpringTopicProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext cxt = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringTopicProducer producer = cxt.getBean(SpringTopicProducer.class);
        producer.jmsTemplate.send((session -> {
            MapMessage message = session.createMapMessage();
            message.setString("name", "zx");
            return message;
        }));
    }
}
