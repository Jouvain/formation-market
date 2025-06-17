package com.example.demo.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Builder
public class Formation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@NonNull
	@Size(max = 30, min = 3, message = "Le nom doit contenir entre 3 et 30 caractères")
	@NotEmpty(message = "Nom obligatoire")
	String nom;
	
	@NonNull
	@Size(max = 30, min = 3, message = "La ville doit contenir entre 3 et 30 caractères")
	@NotEmpty(message = "Ville obligatoire")	
	String ville;
	
	@NonNull
	@DecimalMin(value = "0.5", inclusive = true, message = "durée en jours obligatoire")
	Long nbrJours;
	
	@NonNull
	@NotNull(message = "date de démarrage obligatoire")
	LocalDate dateDebut;

}
