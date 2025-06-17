package com.example.demo.services;

import java.time.LocalDate;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class StatService {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void insertStat( String nom, String prenom, String actionName, Object actionValue, LocalDate localDate) {
		Document stat = new Document("nom", nom).append("prenom", prenom).append(actionName, actionValue).append("date", localDate);
		System.out.println(mongoTemplate.insert(stat, "statistiques"));
	}	

}
