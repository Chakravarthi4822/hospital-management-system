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

import com.hms_hospital_management_system.entity.Department;
import com.hms_hospital_management_system.service.DepartmentService;

@Controller
public class DepartmentController {
			
				DepartmentService departmentService;
				
				@Autowired
				public DepartmentController(DepartmentService departmentService) {
					this.departmentService = departmentService;
				}
				
				/*
				 * design a api to save the department
				 * 
				 */
				@PostMapping("/departments")
				public ResponseEntity<Department> saveDepartmentController(@RequestBody Department dept){
					return departmentService.saveDepartmentService(dept);
				}
				
				/*
				 * Design an api to fetch all the records
				 */
				@GetMapping("/departments")
				public ResponseEntity<List<Department>> fetchAllDepartmentController(){
					return departmentService.fetchAllDepartmentService();
				}
				
				/*
				 * Design an api to fetch All the Department REcords
				 */
				
				@GetMapping("/departments/{id}")
				public ResponseEntity<Department> fetchDepartmentById(@PathVariable long id){
					return departmentService.fetchDepartmentByIdService(id);
				}
				
				/*
				 * design an api to update the Department Record
				 */
				@PatchMapping("/departments/{id}")
				public ResponseEntity<Department> updateDepartmentController(@RequestBody Department dept,@PathVariable long id){
					return departmentService.updateDepartmentService(dept, id);
				}
				
				/*
				 * design an api to delete the department reord
				 */
				@DeleteMapping("/departments/{id}")
				public ResponseEntity<String> deleteDepartmentController(@PathVariable long id){
					return departmentService.deleteDepartmentService(id);
				}
				
	
	
}
