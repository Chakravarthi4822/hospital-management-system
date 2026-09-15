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

import com.hms_hospital_management_system.entity.Appointment;
import com.hms_hospital_management_system.entity.LabTest;
import com.hms_hospital_management_system.service.LabTestService;

@Controller
public class LabTestController {
	
	LabTestService labTestService;

	@Autowired
	public LabTestController(LabTestService labTestService) {
		this.labTestService = labTestService;
	}
	
	/*
	 * Design an api to save lab test
	 */
	@PostMapping("/labtests")
	public ResponseEntity<LabTest> saveLabTestController(@RequestBody LabTest labTest){
		return labTestService.saveLabTestService(labTest);
	 }
	
	/*
	  * Design a method to fetch all the LabTest
	  */
	 
	@GetMapping("/labtests")
	 public ResponseEntity<List<LabTest>> fetchAllLabTestsController() {
		 		return labTestService.fetchAllLabTestsService();
		   
		}
	
	/*
	  * Design a method to fetch  LabTest by id 
	  */
	 @GetMapping("/labtests/{id}")
	 public ResponseEntity<LabTest> fetchLabTestByIdController(@PathVariable long id) {
		 	return labTestService.fetchLabTestByIdService(id);  
		}
	 
	 
	 /*
	  * Design an api to Update Lab Test
	  */
	 @PatchMapping("/labtests/{id}")
	 public ResponseEntity<LabTest> updateLabTestController(@PathVariable long id, @RequestBody LabTest labTest) {
		 return labTestService.updateLabTestService(id, labTest);
		}
	 
	 /*
	  * Design a method to delete the labTest
	  */
	 @DeleteMapping("/labtests/{id}")
	 public ResponseEntity<String> deleteLabTestController(@PathVariable long id) {
		 		return labTestService.deleteLabTestService(id);
		}
	
	

}
