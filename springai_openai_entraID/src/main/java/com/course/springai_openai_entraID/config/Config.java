package com.course.springai_openai_entraID.config;

import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.TokenCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.azure.openai.AzureOpenAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${spring.ai.azure.openai.endpoint}")
    String endpoint;

    @Value("${spring.ai.azure.openai.deployment-name}")
    String deploymentName;

    @Value("${azure.client-id}")
    String clientID;

    @Value("${azure.tenant-id}")
    String tenantID;

    @Value("${azure.client-secret}")
    String secret;


    @Bean
    TokenCredential tokenCredential(){
        return new ClientSecretCredentialBuilder()
                .clientId(clientID)
                .clientSecret(secret)
                .tenantId(tenantID)
                .build();

    }

    @Bean
    OpenAIClientBuilder openAIClientBuilder (TokenCredential tokenCredential){
        return new OpenAIClientBuilder()
                .credential(tokenCredential)
                .endpoint(endpoint);

    }

    @Bean
    public AzureOpenAiChatOptions openAIChatOptions() {
        return AzureOpenAiChatOptions.builder()
                .withDeploymentName(deploymentName)
                .build();
    }

    @Bean
    public AzureOpenAiChatModel azureOpenAiChatModel(OpenAIClientBuilder openAIClientBuilder,
                                                     AzureOpenAiChatOptions openAIChatOptions) {
        return new AzureOpenAiChatModel(openAIClientBuilder, openAIChatOptions);

    }
}
