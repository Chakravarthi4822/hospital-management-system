package com.hms_hospital_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms_hospital_management_system.entity.Consultation;
import com.hms_hospital_management_system.entity.Diagnosis;
import com.hms_hospital_management_system.repository.ConsultationRepository;
import com.hms_hospital_management_system.repository.DiagnosisRepository;

@Service
public class DiagnosisService {
	
	 private DiagnosisRepository diagnosisRepository;
	 private ConsultationRepository consultationRepository;
	 
	 @Autowired 
	 public DiagnosisService(DiagnosisRepository diagnosisRepository, ConsultationRepository consultationRepository) {
		this.diagnosisRepository = diagnosisRepository;
		this.consultationRepository = consultationRepository;
	 }
	 
	 
	 /*
	  * Design a method to save Diagnosis Records
	  */
	 public ResponseEntity<Diagnosis> saveDiadnosisService(Diagnosis d){
		  long id=d.getConsultation().getId();
		  Optional<Consultation> con=consultationRepository.findById(id);
		  if(con.isPresent()) {
			 Consultation consult= con.get();
			 d.setConsultation(consult);
			 Diagnosis dd=diagnosisRepository.save(d);
			 return ResponseEntity.status(HttpStatus.CREATED).body(dd);
		  }
		  else {
			  return ResponseEntity.notFound().build();
		  }
	 }
	 
	 /*
	  * design a method to fetch all the Diagnosis
	  * 
	  */

	 public ResponseEntity<List<Diagnosis>> fetchAllDiagnosisService() {

		    List<Diagnosis> diagnoses = diagnosisRepository.findAll();
		    return ResponseEntity.ok(diagnoses);
//		    	if(!(diagnoses.isEmpty())) {
//		    		return ResponseEntity.ok(diagnoses);
//		    	}
//		    	else {
//		    		return ResponseEntity.notFound().build();
//		    	}
		}
	 
	 /*
	  * Design a method to fetch Diagnosis by id
	  * 
	  */
	 
	 public ResponseEntity<Diagnosis> fetchDiagnosisByIdService(long id) {

		    Optional<Diagnosis> diagnosis =diagnosisRepository.findById(id);

		    if (diagnosis.isPresent()) {
		        return ResponseEntity.ok(diagnosis.get());
		    }
		    else {
		    	 return ResponseEntity.notFound().build();
		    }
		}
	 
	 /*
	  * design a method to update Diagnosis Record
	  */
	 
	 public ResponseEntity<Diagnosis> updateDiagnosisService(Diagnosis diagnosis, long id){
		 Optional<Diagnosis> dia=diagnosisRepository.findById(id);
		 
		 if(dia.isPresent()) {
			 Diagnosis dis=dia.get();
			 
			 if(diagnosis.getConsultation()!=null) dis.setConsultation(diagnosis.getConsultation());
			 if(diagnosis.getDescription()!=null) dis.setDescription(diagnosis.getDescription());
			 if(diagnosis.getDiagnosisName()!=null) dis.setDiagnosisName(diagnosis.getDiagnosisName());
			 if(diagnosis.getSeverity()!=null) dis.setSeverity(diagnosis.getSeverity());
			 if(diagnosis.getStatus()!=null) dis.setStatus(diagnosis.getStatus());
			 
			 Diagnosis d=diagnosisRepository.save(dis);
			 return ResponseEntity.ok(d);
		 }
		 else {
			 return ResponseEntity.notFound().build();
		 }
	 }
	 
	 /*
	  * Design a method to delete Diagnosis
	  */
	 public ResponseEntity<String> deleteDiagnosisService(long id) {
		 
		    Optional<Diagnosis> diagnosis =diagnosisRepository.findById(id);
		    if (diagnosis.isPresent()) {
		        diagnosisRepository.delete(diagnosis.get());
		        return ResponseEntity.noContent().build();
		    }
		    else {
		    	return ResponseEntity.notFound().build();
		    }
		    
		}
}
