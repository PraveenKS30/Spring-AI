package com.course.springai_openai_entraID.controller;

import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AIController {

    @Autowired
    AzureOpenAiChatModel chatModel;

    @GetMapping("/response")
    public String getResponse(@RequestParam String query) {

        UserMessage userMessage = new UserMessage(query);

        return chatModel.call(userMessage).toString();

    }
}
