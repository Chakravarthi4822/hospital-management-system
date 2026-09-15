package com.hms_hospital_management_system.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import com.hms_hospital_management_system.entity.Patient;
import com.hms_hospital_management_system.service.PatientService;

@RestController
public class PatientController {

	PatientService patientService;

	@Autowired
	public PatientController(PatientService patientService) {
		super();
		this.patientService = patientService;
	}

	/*
	 * design an api to save the record
	 */
	@PostMapping("/patients")
	public ResponseEntity<Patient> savePatientController(@RequestBody Patient patient) {

		return patientService.savePatientService(patient);
	}

	/*
	 * design an api to fetch all the data
	 * 
	 */
	@GetMapping("/patients")
	public ResponseEntity<List<Patient>> fetchAllPatientController() {
		return patientService.fetchAllPatientService();
	}

	/*
	 * design a api to fetch patient by ID
	 */

	@GetMapping("/patients/{id}")
	public ResponseEntity<Patient> fetchPatientByIdController(@PathVariable long id) {
		return patientService.fetchPatientByIdService(id);
	}

	/*
	 * Design an api to update the patient data
	 */

	@PatchMapping("/patients/{id}")
	public ResponseEntity<Patient> updatePatientController(@RequestBody Patient patient, @PathVariable long id) {
		return patientService.updatePatientService(patient, id);
	}
	
	/*
	 * Design an api to delete the patient record
	 */
	
	
	@DeleteMapping("/patients/{id}")
	public ResponseEntity<String> deletePatientController(@PathVariable long id){
		
		return patientService.deletePatientService(id);
	}
}
