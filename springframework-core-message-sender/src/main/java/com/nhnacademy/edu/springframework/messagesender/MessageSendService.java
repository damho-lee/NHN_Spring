package com.nhnacademy.edu.springframework.messagesender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSendService {
    private MessageSender messageSender;

    public MessageSendService() {
    }

    public MessageSendService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void doSendMessage(User user, String message) {
        messageSender.sendMessage(user, message);
    }

    @Autowired
    public void setMessageSender(MessageSender smsMessageSender) {
        this.messageSender = smsMessageSender;
    }
}
