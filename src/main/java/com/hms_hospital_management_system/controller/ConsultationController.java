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
import org.springframework.web.bind.annotation.RequestParam;

import com.hms_hospital_management_system.entity.Consultation;
import com.hms_hospital_management_system.service.ConsultationService;

@Controller
public class ConsultationController {
	
	ConsultationService consultationService;

	@Autowired
	public ConsultationController(ConsultationService consultationService) {
		this.consultationService = consultationService;
	}
	
	/*
	 * Design an api to save the record 
	 */
	@PostMapping("/consultations")
	public ResponseEntity<Consultation> saveConsultationController(@RequestBody Consultation con){
		return consultationService.saveConsultationService(con);
	}
	
	/*
	 * Design an api to fetch all Consulataion
	 */
	@GetMapping("/consultations")
	public ResponseEntity<List<Consultation>> fetchAllConsultationService(){
		return consultationService.fetchAllConsultationService();
	}
	/*
	 * Design a api to fetch Consultation record by id
	 */
	@GetMapping("/consultations/{id}")
	public ResponseEntity<Consultation> fetchConsulationByIdController(@PathVariable long id ){
		return consultationService.fetchConsulationByIdServier(id);
	}
	

	/*
	 * Design a method to update the Consultation+Appointment
	 */
	@PatchMapping("/consultations/{id}")
	public ResponseEntity<Consultation> updateConsultaionService( @RequestBody Consultation con, @PathVariable long id){
		 return consultationService.updateConsultaionService(con, id);
	}
	
	/*
	 * Design a method to delete Consultation record
	 */
	@DeleteMapping("/consultations/{id}")
	public ResponseEntity<String> deleteConsultationService(@PathVariable long id){
		return consultationService.deleteConsultationService(id);
	}
}

