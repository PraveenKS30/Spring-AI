package com.course.springai_chat_client_rag.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AIService {

    @Value("classpath:/data/sample.txt")
    private Resource fileResource;

    @Autowired
    VectorStore vectorStore;

    public VectorStore loadDataInVectorStore(){

        TextReader textReader = new TextReader(fileResource);

        textReader.getCustomMetadata().put("filename", "sample.txt");

        List<Document> documents0 = textReader.get();

        log.info("Total documents counts are : {}", documents0.size());

        List<Document> documents = new TokenTextSplitter().apply(documents0);

        log.info("Total documents counts are : {}", documents.size());

        // add document to vector store
        vectorStore.add(documents);

        return vectorStore;
    }

}
