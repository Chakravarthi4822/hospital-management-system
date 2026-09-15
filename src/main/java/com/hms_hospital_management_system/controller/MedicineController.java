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

import com.hms_hospital_management_system.entity.Medicine;
import com.hms_hospital_management_system.service.MedicineService;

@Controller
public class MedicineController {
	
	MedicineService medicineService;

	@Autowired
	public MedicineController(MedicineService medicineService) {
		this.medicineService = medicineService;
	}
	
	
	/*
	 * Design an api to save the MEdicine Record
	 */
	@PostMapping("/medicines")
    public ResponseEntity<Medicine> saveMedicineController(@RequestBody Medicine m) {
    		return medicineService.saveMedicineService(m);
    }
	
	
	/*
     * Desingn an api to fetch all Medicine Records
     */
    
	@GetMapping("/medicines")
    public ResponseEntity<List<Medicine>> fetchAllMedicinesController() {
       return medicineService.fetchAllMedicinesService();
    }
    
	/*
     * Design an api to fetch Medicine By ID
     */
	@GetMapping("/medicines/{id}")
    public ResponseEntity<Medicine> fetchMedicineByIdController(@PathVariable long id) {
			return medicineService.fetchMedicineByIdService(id);
    }
    
	/*
     * Design an api to update the medicine
     */ 
	@PatchMapping("/medicines/{id}")
    public ResponseEntity<Medicine> updateMedicineController(@PathVariable long id,@RequestBody Medicine medicine) {
    	return medicineService.updateMedicineService(id, medicine);
    }
    
	
	/*
     * Design an api to delete the Record 
     * 
     */
	@DeleteMapping("/medicines/{id}")
    public ResponseEntity<String> deleteMedicineController(@PathVariable long id) {
    	return medicineService.deleteMedicineService(id);
    }
    
    
    
    
	
	
	

}
