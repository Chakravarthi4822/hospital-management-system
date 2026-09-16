package com.hms_hospital_management_system.service;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms_hospital_management_system.entity.Patient;
import com.hms_hospital_management_system.exception.AgeInvalidException;
import com.hms_hospital_management_system.exception.NameInvalidException;
import com.hms_hospital_management_system.repository.PatientRepository;

@Service
public class PatientService {
	
	PatientRepository patientRepository;

	@Autowired
	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	
	/*
	 * 
	 * Design an method to save the records 
	 */
	
	public ResponseEntity<Patient> savePatientService(Patient patient) {
		if(patient.getFirstName() ==null || patient.getLastName() ==null) {
			throw new NameInvalidException("name is invalid please provide valid one ");
		}
		if(patient.getAge()<=0) {
			throw new AgeInvalidException("Age is negative please provide valid one ");
		}
		Patient pat=patientRepository.save(patient);
		return ResponseEntity.status(HttpStatus.CREATED).body(pat);
	}
	
	/*
	 *
	 * Desin a method to fetch all the data 
	 */
	public ResponseEntity<List<Patient>> fetchAllPatientService(){
		List<Patient> patient=patientRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(patient);
		
		//it ccheck if null not found 
		
//		if(!(patient.isEmpty())) {
//			return ResponseEntity.status(HttpStatus.OK).body(patient);
//		}
//		return ResponseEntity.notFound().build();
	}
	
	/*
	 * design a method to fetch by id
	 */
	
	public ResponseEntity<Patient> fetchPatientByIdService(long id){
		Optional<Patient> p=patientRepository.findById(id);
		     if(p.isPresent()) {
		    	 Patient patient=p.get();
		    	 return ResponseEntity.ok(patient);
		     }
		     return ResponseEntity.notFound().build();
	}
	
	/*
	 * design a method to update the record
	 */

	public ResponseEntity<Patient> updatePatientService(Patient p,long id){
	     //   01.fetch
		Optional<Patient> pp=patientRepository.findById(id);
		if(pp.isPresent()) {
			Patient patient=pp.get();
			
			//set the dynamic data
			if(p.getUhid()!= null) patient.setUhid(p.getUhid());
			if (p.getFirstName() != null) patient.setFirstName(p.getFirstName());
	        if (p.getLastName() != null) patient.setLastName(p.getLastName());
	        if (p.getAge() >0) patient.setAge(p.getAge());
	        if (p.getGender() != null) patient.setGender(p.getGender());
	        if (p.getDateOfBirth() != null) patient.setDateOfBirth(p.getDateOfBirth());
	        if (p.getPhone() != null) patient.setPhone(p.getPhone());
	        if (p.getEmail() != null) patient.setEmail(p.getEmail());
	        if (p.getAddress() != null) patient.setAddress(p.getAddress());
	        if (p.getBloodGroup() != null) patient.setBloodGroup(p.getBloodGroup());
	        if (p.getEmergencyCantact() != null) patient.setEmergencyCantact(p.getEmergencyCantact());
			
			//save the data 
			patientRepository.save(patient);
			return ResponseEntity.status(HttpStatus.OK).body(patient);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/*
	 * Design a method to delete the patient record
	 */
	public ResponseEntity<String> deletePatientService(long id){
		//fetch the record from db
		Optional<Patient> p=patientRepository.findById(id);
			if(p.isPresent()) {
				Patient patient=p.get();
				//delete the record 
				
				patientRepository.delete(patient);
				return ResponseEntity.noContent().build();
			}
			else {
				return ResponseEntity.notFound().build();
			}
		
	}
	
}
