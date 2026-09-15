package com.hms_hospital_management_system.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.hms_hospital_management_system.entity.Prescription;
import com.hms_hospital_management_system.service.PrescriptionService;

@Controller
public class PrescriptionController {
	
	private PrescriptionService prescriptionService;

	@Autowired
	public PrescriptionController(PrescriptionService prescriptionService) {
		this.prescriptionService = prescriptionService;
	}
	
	/*
	 * Design an api to save the Prescription Record
	 * 
	 */
	@PostMapping("/prescriptions")
	public ResponseEntity<Prescription> savePrescriptionController(@RequestBody Prescription p){
		return prescriptionService.savePrescriptionService(p);
	}
	
	/*
	 * Design an api to fetch all the Prescription Records
	 */
	@GetMapping("/prescriptions")
	public ResponseEntity<List<Prescription>> fetchAllPrescriptionController() {
		    return prescriptionService.fetchAllPrescriptionService();
	}
	
	
	/*
	 * Design an api to fetch Prescription record by id 
	 * 
	 */
	@GetMapping("/prescriptions/{id}")
	public ResponseEntity<Prescription> fetchPrescriptionByIdController(@PathVariable long id) {
			return prescriptionService.fetchPrescriptionByIdService(id);
	}
	
	/*
	 * Design an api method to update the PRescription
	 */

	@PatchMapping("/prescriptions/{id}")
	public ResponseEntity<Prescription> updatePrescriptionController( @PathVariable long id,@RequestBody Prescription pre) {
		return prescriptionService.updatePrescriptionService(id, pre);
	}

	
	/*
	 * design an api to delete the Prescription
	 */
	@DeleteMapping("/prescriptions/{id}")
	public ResponseEntity<String> deletePrescriptionController(@PathVariable long id) {
		return prescriptionService.deletePrescriptionService(id);
	}
}
