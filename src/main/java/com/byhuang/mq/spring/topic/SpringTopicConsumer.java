package com.byhuang.mq.spring.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/18 14:32
 * @description TODO
 */
@Service
public class SpringTopicConsumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringTopicConsumer consumer = ctx.getBean(SpringTopicConsumer.class);
        Object o = consumer.jmsTemplate.receiveAndConvert();
        System.out.println(o);
    }
}
