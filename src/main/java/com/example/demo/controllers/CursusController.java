package com.example.demo.controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Formation;
import com.example.demo.entities.User;
import com.example.demo.repositories.FormationRepository;
import com.example.demo.repositories.UserRepository;

@Controller
public class CursusController {
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private UserRepository userRepository;
	@Autowired 
	private FormationRepository formationRepository;

	@GetMapping("/cursus")
	public String cursusRendering(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("user", userRepository.findByUsername(username));
		model.addAttribute("listeFormations", userRepository.findByUsername(username).getFormations());
		model.addAttribute("today", LocalDate.now());
		Document stat = new Document("nom", userRepository.findByUsername(username).getLastname())
				.append("prenom", userRepository.findByUsername(username).getFirstname())
				.append("consultation", "cursus")
				.append("date", LocalDate.now());
		System.out.println(mongoTemplate.insert(stat, "statistiques"));
		return "cursus";
	}
	
	
	@PostMapping("/inscription")
	public String inscription(@RequestParam Integer id) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username);
		Formation formation = formationRepository.findById(id).orElseThrow();
		if(!user.getFormations().contains(formation)) {
			user.getFormations().add(formation);
			userRepository.save(user);
		}
		Document stat = new Document("nom", userRepository.findByUsername(username).getLastname())
				.append("prenom", userRepository.findByUsername(username).getFirstname())
				.append("inscription", formation)
				.append("date", LocalDate.now());
		System.out.println(mongoTemplate.insert(stat, "statistiques"));
		return "redirect:formations";
	}
	
	@GetMapping("/pastCursus")
	public String pastCursus(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var today = LocalDate.now();
	    List<Formation> formationsTerminees = userRepository.findByUsername(username).getFormations().stream()
	            .filter(formation -> formation.getDateDebut().plus(formation.getNbrJours(), ChronoUnit.DAYS).isBefore(today))
	            .toList();
		model.addAttribute("user", userRepository.findByUsername(username));
		model.addAttribute("today", today);
		model.addAttribute("listeFormations", formationsTerminees);
		Document stat = new Document("nom", userRepository.findByUsername(username).getLastname())
				.append("prenom", userRepository.findByUsername(username).getFirstname())
				.append("consultation", "cursus passé")
				.append("date", LocalDate.now());
		System.out.println(mongoTemplate.insert(stat, "statistiques"));
		return "cursus";
	}
	
	@GetMapping("/ongoingCursus")
	public String ongoingCursus(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var today = LocalDate.now();
	    List<Formation> formationsEnCours = userRepository.findByUsername(username).getFormations().stream()
	            .filter(formation -> formation.getDateDebut().isBefore(today))
	            .filter(formation -> formation.getDateDebut().plus(formation.getNbrJours(), ChronoUnit.DAYS).isAfter(today))
	            .toList();
		model.addAttribute("user", userRepository.findByUsername(username));
		model.addAttribute("today", today);
		model.addAttribute("listeFormations", formationsEnCours);
		Document stat = new Document("nom", userRepository.findByUsername(username).getLastname())
				.append("prenom", userRepository.findByUsername(username).getFirstname())
				.append("consultation", "cursus en cours")
				.append("date", LocalDate.now());
		System.out.println(mongoTemplate.insert(stat, "statistiques"));
		return "cursus";
	}
	
	@GetMapping("/futureCursus")
	public String futureCursus(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var today = LocalDate.now();
	    List<Formation> formationsAVenir = userRepository.findByUsername(username).getFormations().stream()
	            .filter(formation -> formation.getDateDebut().isAfter(today))
	            .toList();
		model.addAttribute("user", userRepository.findByUsername(username));
		model.addAttribute("today", today);
		model.addAttribute("listeFormations", formationsAVenir);
		Document stat = new Document("nom", userRepository.findByUsername(username).getLastname())
				.append("prenom", userRepository.findByUsername(username).getFirstname())
				.append("consultation", "cursus à venir")
				.append("date", LocalDate.now());
		System.out.println(mongoTemplate.insert(stat, "statistiques"));
		return "cursus";
	}
	
}
