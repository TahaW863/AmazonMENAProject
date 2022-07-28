package com.taha.webfluxrealtimechat.messages.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class CollectionConfig {
    public CollectionConfig(final MongoTemplate mongoTemplate) {
        mongoTemplate.dropCollection("messages");
        mongoTemplate.createCollection("messages", CollectionOptions.empty().capped()
                .size(100000).maxDocuments(100000));
    }
}
