package com.example.demo.controllers;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/visiteurs")
public class VisiteurController {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@GetMapping("save")
	public void save() {
		Document doc1 = new Document("ville", "Marseille").append("cp", "13066").append("age", 46);
		Document doc2 = new Document("pays", "France").append("nom", "Jonh Locke").append("profession", "acteur");
		
		System.out.println(mongoTemplate.insert(doc1, "visiteurs"));
		System.out.println(mongoTemplate.insert(doc2, "visiteurs"));
	}
	
	
}
