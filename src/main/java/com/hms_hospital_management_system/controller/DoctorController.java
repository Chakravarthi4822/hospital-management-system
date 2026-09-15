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

import com.hms_hospital_management_system.entity.Doctor;
import com.hms_hospital_management_system.service.DoctorService;

@Controller
public class DoctorController {
	
	DoctorService doctorService;

	//inject object
	
	@Autowired
	public DoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}
	
	/*
	 * design an api to save the doctor information
	 */
	
	@PostMapping("/doctors")
	public ResponseEntity<Doctor> saveDoctorController(@RequestBody Doctor doctor){
		 return doctorService.saveDoctorService(doctor);
	}
	
	/*
	 * Design an api to fetch all the Doctors 
	 */

	@GetMapping("/doctors")
	public ResponseEntity<List<Doctor>> fetchAllDoctorService(){
		return doctorService.fetchAllDoctorService();
	}
	
	/*
	 * Design an api to fetch Doctor record By id
	 */
	
	@GetMapping("/doctors/{id}")
	public ResponseEntity<Doctor> fetchDoctorByIdController(@PathVariable long id){
		return doctorService.fetchDoctorByIdService(id);
   	 
    }
	
	/*
	 * Design an api to update the doctor record
	 */
	@PatchMapping("/doctors/{id}")
    public ResponseEntity<Doctor> updateDoctorController(@RequestBody Doctor doct,@PathVariable long id){
   	 return doctorService.updateDoctorService(doct, id);
    }
	
	/*
	 * Design an api to delete the record
	 */
	@DeleteMapping("/doctors/{id}")
	public ResponseEntity<String> deleteDoctorService(@PathVariable long id){
   	 return doctorService.deleteDoctorService(id);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
