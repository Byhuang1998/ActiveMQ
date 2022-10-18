package com.byhuang.mq.spring.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/18 12:27
 * @description TODO
 */
@Service
public class SpringConsumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringConsumer consumer = (SpringConsumer) ctx.getBean("springConsumer");
        String message = (String) consumer.jmsTemplate.receiveAndConvert();
        System.out.println(message);
    }
}
