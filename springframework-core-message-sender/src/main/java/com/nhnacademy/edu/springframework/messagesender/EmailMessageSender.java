package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.annotation.Tracing;
import org.springframework.stereotype.Component;

@Component
public class EmailMessageSender implements MessageSender {
//    public EmailMessageSender() {
//        System.out.println(">>>>>>>>>>>>>>>EmailMessageSender created");
//    }

    @Override
    @Tracing
    public void sendMessage(User user, String message) {
        System.out.println("Email Message Sent " + user.getEmail() + " : " + message);
    }
}
