package com.example.demo.controllers;

import java.time.LocalDate;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Formation;
import com.example.demo.repositories.FormationRepository;
import com.example.demo.repositories.UserRepository;

import jakarta.validation.Valid;
import org.bson.Document;


@Controller
public class FormationController {
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private FormationRepository formationRepository;
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/formations")
	public String formationRendering(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("user", userRepository.findByUsername(username));
		model.addAttribute("listeFormations", formationRepository.findAll());
		model.addAttribute("today", LocalDate.now());
		Document stat = new Document("nom", userRepository.findByUsername(username).getLastname())
				.append("prenom", userRepository.findByUsername(username).getFirstname())
				.append("consultation", "formations")
				.append("date", LocalDate.now());
		System.out.println(mongoTemplate.insert(stat, "statistiques"));
		return "formations";
	}

//	@GetMapping("/addFormation")
//	public String addFormation(Model model) {
//		model.addAttribute("formation", new Formation());
//		return "addFormation";
//	}
//
//	@PostMapping("/addFormation")
//	public String addFormation(@Valid @ModelAttribute Formation formation, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			return "redirect:/addFormation";
//		}
//		model.addAttribute("formation", formationRepository.save(formation));
//		return "home";
//	}

	@GetMapping("/sortFormationsByNom")
	public String sortFormationsByNom(Model model) {
		model.addAttribute("listeFormations", formationRepository.findAll(Sort.by("nom").ascending()));
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("user", userRepository.findByUsername(username));
		model.addAttribute("today", LocalDate.now());
		Document stat = new Document("nom", userRepository.findByUsername(username).getLastname())
				.append("prenom", userRepository.findByUsername(username).getFirstname())
				.append("tri", "par nom")
				.append("date", LocalDate.now());
		System.out.println(mongoTemplate.insert(stat, "statistiques"));
		return "formations";
	}

	@GetMapping("/sortFormationsByDate")
	public String sortFormationsByDate(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("user", userRepository.findByUsername(username));
		model.addAttribute("today", LocalDate.now());
		model.addAttribute("listeFormations", formationRepository.findAll(Sort.by("dateDebut").descending()));
		Document stat = new Document("nom", userRepository.findByUsername(username).getLastname())
				.append("prenom", userRepository.findByUsername(username).getFirstname())
				.append("tri", "par date")
				.append("date", LocalDate.now());
		System.out.println(mongoTemplate.insert(stat, "statistiques"));
		return "formations";
	}

	@PostMapping("/filterFormationsByNom")
	public String filterFormationsByNom(Model model, @RequestParam String nom) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("user", userRepository.findByUsername(username));
		model.addAttribute("today", LocalDate.now());
		model.addAttribute("listeFormations", formationRepository.findByNomContaining(nom));
		Document stat = new Document("nom", userRepository.findByUsername(username).getLastname())
				.append("prenom", userRepository.findByUsername(username).getFirstname())
				.append("recherche", nom)
				.append("date", LocalDate.now());
		System.out.println(mongoTemplate.insert(stat, "statistiques"));
		return "formations";
	}

}
