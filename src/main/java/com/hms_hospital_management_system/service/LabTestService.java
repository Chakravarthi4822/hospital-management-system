package com.hms_hospital_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms_hospital_management_system.entity.Appointment;
import com.hms_hospital_management_system.entity.LabTest;
import com.hms_hospital_management_system.entity.Patient;
import com.hms_hospital_management_system.repository.AppointmentRepository;
import com.hms_hospital_management_system.repository.LabTestRepository;
import com.hms_hospital_management_system.repository.PatientRepository;

@Service
public class LabTestService {
	
	 private LabTestRepository labTestRepository;
	 private PatientRepository patientRepository;
	 private AppointmentRepository appointmentRepository;
	 
	 @Autowired
	 public LabTestService(LabTestRepository labTestRepository, PatientRepository patientRepository,
				AppointmentRepository appointmentRepository) {
			super();
			this.labTestRepository = labTestRepository;
			this.patientRepository = patientRepository;
			this.appointmentRepository = appointmentRepository;
		 }
	 
	 /*
	  * Design a method to save the LabTest Result
	  */
	 
	 public ResponseEntity<LabTest> saveLabTestService(LabTest labTest){
		long id=labTest.getAppointment().getId();
	       Optional<Appointment> appo=appointmentRepository.findById(id);
	       if(appo.isPresent()) {
	    	   Appointment a=appo.get();
	    	    labTest.setAppointment(a);
	    	    labTest.setPatient(a.getPatient());
	    	    
	    	    LabTest l=labTestRepository.save(labTest);
	    	    return ResponseEntity.status(HttpStatus.CREATED).body(l);
	       }
	       
	       else {
	    	   return ResponseEntity.notFound().build();
	       }
	 }
	 
	 /*
	  * Design a method to fetch all the LabTest
	  */
	 
	 public ResponseEntity<List<LabTest>> fetchAllLabTestsService() {

		    List<LabTest> labTests = labTestRepository.findAll();
		    return ResponseEntity.ok(labTests);
//		    if(!(labTests.isEmpty())) {
//		    	return ResponseEntity.ok(labTests);
//		    }
//		    else {
//		    	return ResponseEntity.notFound().build();
//		    }
		}

	 /*
	  * Design a method to fetch  LabTest by id 
	  */
	 
	 public ResponseEntity<LabTest> fetchLabTestByIdService(long id) {

		    Optional<LabTest> labTest = labTestRepository.findById(id);
		    if (labTest.isPresent()) {
		        return ResponseEntity.ok(labTest.get());
		    }
		    else {
		    	return ResponseEntity.notFound().build();
		    }
		}
	 
	 /*
	  * Design a method to update the LabTest
	  * 
	  */
	 public ResponseEntity<LabTest> updateLabTestService(long id, LabTest labTest) {
		 
		    Optional<LabTest> existing = labTestRepository.findById(id);
		    if (existing.isPresent()) {
		        LabTest l = existing.get();

		        if (labTest.getTestName() != null) l.setTestName(labTest.getTestName());
		        if (labTest.getTestType() != null) l.setTestType(labTest.getTestType());
		        if (labTest.getStatus() != null) l.setStatus(labTest.getStatus());
		        
		        LabTest updated = labTestRepository.save(l);
		        return ResponseEntity.ok(updated);
		    }
		    else {
		    	 return ResponseEntity.notFound().build();
		    }
		}

	 /*
	  * Design a method to delete the record
	  */
	 
	 public ResponseEntity<String> deleteLabTestService(long id) {

		    Optional<LabTest> labTest = labTestRepository.findById(id);

		    if (labTest.isPresent()) {
		        labTestRepository.delete(labTest.get());
		        return ResponseEntity.noContent().build();
		    }
		    else {
		    	return ResponseEntity.notFound().build();
		    }
		}
}
