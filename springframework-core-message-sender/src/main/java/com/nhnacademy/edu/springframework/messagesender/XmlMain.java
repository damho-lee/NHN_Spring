package com.nhnacademy.edu.springframework.messagesender;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlMain {
    public static void main(String[] args) {
        User user = new User("dlekagh@naver.com", "010-5581-1302");

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
            MessageSender emailMessageSender = context.getBean("emailMessageSender", MessageSender.class);
            MessageSender smsMessageSender = context.getBean("smsMessageSender", MessageSender.class);

            emailMessageSender.sendMessage(user, "hi");
            smsMessageSender.sendMessage(user, "hi");
        }
    }
}
