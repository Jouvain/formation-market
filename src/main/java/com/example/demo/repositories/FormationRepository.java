package com.example.demo.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Formation;

public interface FormationRepository extends JpaRepository<Formation, Integer>{
	List<Formation> findByNomContaining(String nom);
	List<Formation> findByDateDebutAfterOrderByDateDebutAsc(LocalDate date);

}
