package com.nhnacademy.edu.springframework.messagesender;

public class TestMain {
    public static void main(String[] args) {
        User user = new User("dlekagh@naver.com", "010-5581-1302");

        MessageSendService emailMessageSendService = new MessageSendService(new EmailMessageSender());
        MessageSendService smsMessageSendService = new MessageSendService(new SmsMessageSender());

        emailMessageSendService.doSendMessage(user, "hi");
        smsMessageSendService.doSendMessage(user, "hi");
    }
}
