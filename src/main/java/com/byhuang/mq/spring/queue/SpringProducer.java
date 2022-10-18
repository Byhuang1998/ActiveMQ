package com.byhuang.mq.spring.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/18 12:26
 * @description TODO
 */
@Service
public class SpringProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringProducer producer = (SpringProducer) ctx.getBean("springProducer");
        producer.jmsTemplate.send((session -> {
            TextMessage msg = session.createTextMessage("integrate spring and Active MQ");
            return msg;
        }));
        System.out.println("send task over");
    }
}
