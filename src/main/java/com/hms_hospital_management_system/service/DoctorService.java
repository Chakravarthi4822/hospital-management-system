package com.hms_hospital_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms_hospital_management_system.entity.Doctor;
import com.hms_hospital_management_system.repository.DoctorRepository;

@Service
public class DoctorService {
	
	DoctorRepository doctorRepository;
     @Autowired//optional becoz one constructor
	public DoctorService(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}
     
     /*
      * design a method to save the doctor information
      */
     
     public ResponseEntity<Doctor> saveDoctorService(Doctor doctor) {
    	  Doctor doc=doctorRepository.save(doctor);//this save the dept info also with the help of json
    	  return ResponseEntity.status(HttpStatus.CREATED).body(doc);
     }
	
     /*
      * design a method to fetch the all records
      */
     public ResponseEntity<List<Doctor>> fetchAllDoctorService(){
    	 List<Doctor> doct=doctorRepository.findAll();
    	 if(!(doct.isEmpty())) {
    		 
    		 return ResponseEntity.ok(doct);
    	 }
    	 else {
    		 return ResponseEntity.notFound().build();
    				 
    	 }
     }
    
     /*
      * Design a method to fetch the Doctor Record By Id
      */
     public ResponseEntity<Doctor> fetchDoctorByIdService(long id){
    	 Optional<Doctor> doct=doctorRepository.findById(id);
    	 if(doct.isPresent()) {
    		 Doctor doctor=doct.get();
    		 return ResponseEntity.status(HttpStatus.OK).body(doctor);
    	 }
    	 else {
    		 return ResponseEntity.notFound().build();
    	 }
     }
     
     /*
      * Design a method to update the record 
      */
     public ResponseEntity<Doctor> updateDoctorService(Doctor doct,long id){
    	 Optional<Doctor> docto=doctorRepository.findById(id);
    	 
    	 if(docto.isPresent()) {
    		 Doctor doctor=docto.get();
    		 
    		 if(doct.getName() !=null) doctor.setName(doct.getName());
    		 if(doct.getSpecialization() !=null) doctor.setSpecialization(doct.getSpecialization());
    		 if(doct.getPhone() !=null) doctor.setPhone(doct.getPhone());
    		 if(doct.getEmail() !=null) doctor.setEmail(doct.getEmail());
    		 if(doct.getQualification() !=null) doctor.setQualification(doct.getQualification());
    		 if(doct.getStatus() !=null) doctor.setStatus(doct.getStatus());
    		 
    		 doctorRepository.save(doctor);
    		 
    		 return ResponseEntity.ok(doctor);
    	 }
    	 else {
    		 return ResponseEntity.notFound().build();
    	 }
     }
     
     /*
      * Design a method to delete doctor Record
      */
     public ResponseEntity<String> deleteDoctorService(long id){
    	 Optional<Doctor> doct=doctorRepository.findById(id);
    	 if(doct.isPresent()) {
    		Doctor doctor= doct.get();
    		doctorRepository.delete(doctor);
    		return ResponseEntity.noContent().build();
    	 }
    	 else {
    		 return ResponseEntity.notFound().build();
    	 }
     }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
}
