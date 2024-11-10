package com.course.springai_chat_client_rag.controller;

import com.course.springai_chat_client_rag.service.AIService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AIController {

    @Autowired
    AIService aiService;

    @Autowired
    ChatClient chatClient;

    @PostMapping("/response")
    public Map<String, String> getResponse(@RequestParam String message){

        VectorStore vectorStore = aiService.loadDataInVectorStore();

        return Map.of("response", chatClient.prompt()
                .advisors(new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults()))
                .user(message)
                .call()
                .content());

    }
}
