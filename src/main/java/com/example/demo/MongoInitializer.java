package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoInitializer {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void run() throws Exception {
        mongoTemplate.getDb().drop(); // 💣 Supprime toute la base ciblée par spring.data.mongodb.uri
        System.out.println("✅ Base MongoDB purgée au démarrage.");
    }
}
