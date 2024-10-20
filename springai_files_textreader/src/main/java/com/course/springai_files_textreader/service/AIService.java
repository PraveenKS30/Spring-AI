package com.course.springai_files_textreader.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIService {

    @Autowired
    VectorStore vectorStore;

    @Value("classpath:/data/story.txt")
    private Resource textResource;

    public List<Document> getQueryDocs(String query) {

        // read text file
        TextReader textReader = new TextReader(textResource);

        // convert the text into Document object
        List<Document> documents = textReader.get();

        System.out.println(documents);

        // chunking of text
        var textSplitter = new TokenTextSplitter();

        // add it into vector store
        vectorStore.add(textSplitter.apply(documents));

        // vector search
        List<Document> results = vectorStore.similaritySearch(query);

        return results;

    }

    public List<Document> getQueryDocsSR(String query) {

        // read text file
        TextReader textReader = new TextReader(textResource);

        // convert the text into Document object
        List<Document> documents = textReader.get();

        // chunking of text
        var textSplitter = new TokenTextSplitter();

        // add it into vector store
        vectorStore.add(textSplitter.apply(documents));

        // vector search
        List<Document> results = vectorStore.similaritySearch(
                SearchRequest.defaults()
                        .withQuery(query)
                        .withTopK(1)
                        .withSimilarityThreshold(0.8)
        );

        return results;
    }
}
