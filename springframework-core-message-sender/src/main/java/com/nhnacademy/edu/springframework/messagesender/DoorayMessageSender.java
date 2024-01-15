package com.nhnacademy.edu.springframework.messagesender;

import com.nhn.dooray.client.DoorayHook;
import com.nhn.dooray.client.DoorayHookSender;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DoorayMessageSender implements MessageSender {

    @Override
    public void sendMessage(User user, String message) {
        new DoorayHookSender(new RestTemplate(),
                "https://hook.dooray.com/services/3204376758577275363/3707922642738216364/WdJUIozGSgGdnxhQnS1lSA")
                .send(DoorayHook.builder()
                        .botName("이담호")
                        .text("안녕하세요")
                        .build());
    }
}
