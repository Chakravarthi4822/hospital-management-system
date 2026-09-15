package com.hms_hospital_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.hms_hospital_management_system.entity.Department;
import com.hms_hospital_management_system.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	DepartmentRepository departmentRepository;

	@Autowired
	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}
	
	
	/*
	 * design a method to save the doctor
	 */
	public ResponseEntity<Department> saveDepartmentService(Department dept){
		Department depart=departmentRepository.save(dept);
		return ResponseEntity.status(HttpStatus.CREATED).body(depart);
	}
	
	/*
	 * design an method to fetch All Department info
	 */
	
	public ResponseEntity<List<Department>> fetchAllDepartmentService(){
		List<Department> dept=departmentRepository.findAll();
		if(!(dept.isEmpty())) {
			return ResponseEntity.status(HttpStatus.OK).body(dept);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/*
	 * design a method to fetch department by id 
	 */
	
	public ResponseEntity<Department> fetchDepartmentByIdService(long id){
		
		Optional<Department> dept=departmentRepository.findById(id);
		if(dept.isPresent()) {
			Department department=dept.get();
			return ResponseEntity.ok(department);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/*
	 * design a method to update the Department Record
	 */
	public ResponseEntity<Department> updateDepartmentService(Department dept, long id){
		//find the department by id
		Optional<Department> depart=departmentRepository.findById(id);
		if(depart.isPresent()) {
			Department department=depart.get();
			
			//set the data 
			if(dept.getName()!=null) department.setName(dept.getName());
			if(dept.getDescription()!=null) department.setDescription(dept.getDescription());
			if(dept.getStatus()!=null) department.setStatus(dept.getStatus());
			
			//update
			
			departmentRepository.save(department);
			
			return ResponseEntity.ok(department);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/*
	 * Design a method to delete the department record
	 */
	public ResponseEntity<String> deleteDepartmentService(long id){
		
		//fetch
		Optional<Department> dept=departmentRepository.findById(id);
		if(dept.isPresent()) {
			Department department=dept.get();
			departmentRepository.delete(department);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
