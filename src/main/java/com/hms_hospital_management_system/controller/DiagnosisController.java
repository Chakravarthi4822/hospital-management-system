package com.hms_hospital_management_system.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hms_hospital_management_system.entity.Diagnosis;
import com.hms_hospital_management_system.service.DiagnosisService;

@Controller
public class DiagnosisController {

	DiagnosisService diagnosisService;

	@Autowired
	public DiagnosisController(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}
	
	/*
	  * Design an api to save Diagnosis Records
	  */
	@PostMapping("/diagnosiss")
	 public ResponseEntity<Diagnosis> saveDiadnosisController(@RequestBody Diagnosis d){
		  return diagnosisService.saveDiadnosisService(d);
	 }
	
	
	/*
	  * design an api to fetch all the Diagnosis
	  * 
	  */
	@GetMapping("/diagnosiss")
	 public ResponseEntity<List<Diagnosis>> fetchAllDiagnosisService() {
		 	return diagnosisService.fetchAllDiagnosisService();
		}
	
	/*
	  * Design an api method to fetch Diagnosis by id
	  * 
	  */
	 @GetMapping("/diagnosiss/{id}")
	 public ResponseEntity<Diagnosis> fetchDiagnosisByIdService(@PathVariable long id) {
		 return diagnosisService.fetchDiagnosisByIdService(id);
		}
	 
	 /*
	  * design an api to update Diagnosis Record
	  */
	 
	 @PatchMapping("/diagnosiss/{id}")
	 public ResponseEntity<Diagnosis> updateDiagnosisController(@RequestBody Diagnosis diagnosis,@PathVariable long id){
		 return diagnosisService.updateDiagnosisService(diagnosis, id);
	 }
	 
	 /*
	  * Design an api  to delete Diagnosis Record
	  */
	 @DeleteMapping("/diagnosiss/{id}")
	 public ResponseEntity<String> deleteDiagnosisController(@PathVariable long id) {
		   return diagnosisService.deleteDiagnosisService(id);
		}
}
