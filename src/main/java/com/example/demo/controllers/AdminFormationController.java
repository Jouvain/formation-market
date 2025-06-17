package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Formation;
import com.example.demo.repositories.FormationRepository;

import jakarta.validation.Valid;

@Controller
public class AdminFormationController {

	@Autowired
	private FormationRepository formationRepository;
	
	@GetMapping("/adminFormations")
	public String formationRendering(Model model) {
		model.addAttribute("listeFormations", formationRepository.findAll());
		return "adminFormations";
	}

	@GetMapping("/addFormation")
	public String addFormation(Model model) {
		model.addAttribute("formation", new Formation());
		return "addFormation";
	}

	@PostMapping("/addFormation")
	public String addFormation(@Valid @ModelAttribute Formation formation, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "redirect:/addFormation";
		}
		model.addAttribute("formation", formationRepository.save(formation));
		model.addAttribute("listeFormations", formationRepository.findAll());
		return "adminFormations";
	}
	@GetMapping("/sortFormationsByNomADMIN")
	public String sortFormationsByNom(Model model) {
		model.addAttribute("listeFormations", formationRepository.findAll(Sort.by("nom").ascending()));
		return "adminFormations";
	}

	@GetMapping("/sortFormationsByDateADMIN")
	public String sortFormationsByDate(Model model) {
		model.addAttribute("listeFormations", formationRepository.findAll(Sort.by("dateDebut").descending()));
		return "adminFormations";
	}

	@PostMapping("/filterFormationsByNomADMIN")
	public String filterFormationsByNom(Model model, @RequestParam String nom) {
		model.addAttribute("listeFormations", formationRepository.findByNomContaining(nom));
		return "adminFormations";
	}
	
}
