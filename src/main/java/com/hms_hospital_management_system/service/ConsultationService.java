package com.hms_hospital_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms_hospital_management_system.entity.Appointment;
import com.hms_hospital_management_system.entity.Consultation;
import com.hms_hospital_management_system.repository.AppointmentRepository;
import com.hms_hospital_management_system.repository.ConsultationRepository;

@Service
public class ConsultationService {

	ConsultationRepository consultationRepository;
	AppointmentRepository appointmentRepository;
	
	@Autowired
	public ConsultationService(ConsultationRepository consultationRepository,AppointmentRepository appointmentRepository) {
		this.consultationRepository = consultationRepository;
		this.appointmentRepository = appointmentRepository;
	}
	
	/*
	 * Design a method to save the Consultation record
	 */
	
	public ResponseEntity<Consultation> saveConsultationService(Consultation consultation) {

        long appointmentId = consultation.getAppointment().getId();

        Optional<Appointment> appointment =appointmentRepository.findById(appointmentId);

        if (appointment.isPresent()) {

            consultation.setAppointment(appointment.get());//to avoid the null data of appointment and set

            Consultation savedConsultation =consultationRepository.save(consultation);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedConsultation);
        }

        return ResponseEntity.notFound().build();
    }
	
	/*
	 * Fetch all the Consultation Record 
	 */
	public ResponseEntity<List<Consultation>> fetchAllConsultationService(){
		List<Consultation> consultation=consultationRepository.findAll();
		if(!(consultation.isEmpty())) {
			return ResponseEntity.ok(consultation);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/*
	 * Design a method to fetch Consultation record by id
	 */
	public ResponseEntity<Consultation> fetchConsulationByIdServier(long id ){
		Optional<Consultation> con=consultationRepository.findById(id);
		if(con.isPresent()) {
			Consultation consult=con.get();
			return ResponseEntity.ok(consult);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/*
	 * Design a method to update the Consultation+Appointment
	 */
	public ResponseEntity<Consultation> updateConsultaionService(Consultation con,long id){
		 Optional<Consultation> consult=consultationRepository.findById(id);
		 if(consult.isPresent()) {
			 Consultation consultation=consult.get();
			 
			 if(con.getAppointment()!=null) consultation.setAppointment(con.getAppointment());
			 if(con.getConsultationTime()!=null) consultation.setConsultationTime(con.getConsultationTime());
			 if(con.getSymptoms()!=null) consultation.setSymptoms(con.getSymptoms());
			 if(con.getStatus()!=null) consultation.setStatus(con.getStatus());
			 if(con.getClinicalNotes()!=null) consultation.setClinicalNotes(con.getClinicalNotes());
			 
			 //call save 
			 consultationRepository.save(consultation);
			 return ResponseEntity.ok(consultation);
		 }
		 else {
			 return ResponseEntity.notFound().build();
		 }
	}
	
	/*
	 * Design a method to delete Consultation record
	 */
	public ResponseEntity<String> deleteConsultationService(long id){
		Optional<Consultation> con=consultationRepository.findById(id);
		if(con.isPresent()) {
			Consultation consult=con.get();
			consultationRepository.delete(consult);
			
			return ResponseEntity.noContent().build();
		}
		else{
			return ResponseEntity.notFound().build();
		}
	}
}
