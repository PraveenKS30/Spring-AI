package com.course.springai_chat_client_rag.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfig {

    @Bean
    VectorStore vectorStore(EmbeddingModel embeddingModel){
        return new SimpleVectorStore(embeddingModel);
    }

    @Bean
    ChatClient chatClient(ChatClient.Builder builder){
        return builder.build();
    }
}
