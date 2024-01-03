package com.nhnacademy.edu.springframework.messagesender;

import org.springframework.stereotype.Component;

@Component
public class EmailMessageSender implements MessageSender {
//    public EmailMessageSender() {
//        System.out.println(">>>>>>>>>>>>>>>EmailMessageSender created");
//    }

    @Override
    public void sendMessage(User user, String message) {
        System.out.println("Email Message Sent " + user.getEmail() + " : " + message);
    }
}
