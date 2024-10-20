package com.course.springai_files_textreader.controller;

import com.course.springai_files_textreader.service.AIService;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class AIController {
    @Autowired
    AIService aiService;

    @Autowired
    ChatModel chatModel;

    @PostMapping("/v1/query")
    public String getQueryRespV1(@RequestParam String query){

        return aiService.getQueryDocs(query).get(0).getContent().toString();
        //return aiService.getQueryDocs(query);

    }

    @PostMapping("/v2/query")
    public String getQueryRespV2(@RequestParam String query){

        return aiService.getQueryDocsSR(query).get(0).getContent().toString();
    }

    @PostMapping("/v3/query")
    public String getQueryRespV3(@RequestParam String query){

        return chatModel.call(new Prompt(query)).getResult().getOutput().getContent();
    }
}
