package com.lsxuan.saas.interfaces.api.v1.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ai")
public class ChatController {

    private final ChatClient chatClient;

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        System.out.println("API KEY: " + apiKey);
        return chatClient.prompt().user(message).call().content();
    }
}
