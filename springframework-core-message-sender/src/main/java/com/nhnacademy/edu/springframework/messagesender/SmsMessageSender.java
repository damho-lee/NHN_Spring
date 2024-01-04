package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.annotation.Tracing;
import org.springframework.stereotype.Component;

@Component
public class SmsMessageSender implements MessageSender{

    @Override
    @Tracing
    public void sendMessage(User user, String message) {
        System.out.println("SMS Message Sent to " + user.getPhoneNumber() +  " : " + message);
    }

    public void init() {
        System.out.println("init method called in SmsMessageSender");
    }
}
