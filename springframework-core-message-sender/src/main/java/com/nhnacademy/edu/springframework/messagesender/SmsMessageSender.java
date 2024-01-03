package com.nhnacademy.edu.springframework.messagesender;

import org.springframework.stereotype.Component;

@Component
public class SmsMessageSender implements MessageSender{

    @Override
    public void sendMessage(User user, String message) {
        System.out.println("SMS Message Sent to " + user.getPhoneNumber() +  " : " + message);
    }

    public void init() {
        System.out.println("init method called in SmsMessageSender");
    }
}
