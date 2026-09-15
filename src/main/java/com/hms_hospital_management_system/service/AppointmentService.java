package com.hms_hospital_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms_hospital_management_system.entity.Appointment;
import com.hms_hospital_management_system.entity.Doctor;
import com.hms_hospital_management_system.entity.Patient;
import com.hms_hospital_management_system.repository.AppointmentRepository;
import com.hms_hospital_management_system.repository.DoctorRepository;
import com.hms_hospital_management_system.repository.PatientRepository;

@Service
public class AppointmentService {

	private AppointmentRepository appointmentRepository;
	/*
	 * for null purpose
	 */
	PatientRepository patientRepository;
	DoctorRepository doctorRepository;

	@Autowired
	public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository,
			DoctorRepository doctorRepository) {
		
		this.appointmentRepository = appointmentRepository;
		this.patientRepository = patientRepository;
		this.doctorRepository = doctorRepository;
	}

//	@Autowired
//	public AppointmentService(AppointmentRepository appointmentRepository) {
//		this.appointmentRepository = appointmentRepository;
//	}

	// methods on Appointment to perform crud operations

	/*
	 * Design a method to save Appointment
	 */

	public ResponseEntity<Appointment> saveAppointmentServier(Appointment apt) {
		
		Patient patient = patientRepository.findById(apt.getPatient().getId()).orElseThrow();

	    Doctor doctor = doctorRepository.findById(apt.getDoctor().getId()).orElseThrow();

	    apt.setPatient(patient);
	    apt.setDoctor(doctor);
		Appointment appo = appointmentRepository.save(apt);
		return ResponseEntity.ok(appo);
	}
	
	/*
	 * design a methos to fetch all Appointment Record 
	 */
	
	public ResponseEntity<List<Appointment>> fetchAllAppointmentService(){
		List<Appointment> appt=appointmentRepository.findAll();
		if(!(appt.isEmpty())) {
		return ResponseEntity.status(HttpStatus.OK).body(appt);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/*
	 * Design a method to fetch by Appointment by id
	 */
	public ResponseEntity<Appointment> fetchAppointmentById(long id){
		Optional<Appointment> apt=appointmentRepository.findById(id);
		if(apt.isPresent()) {
			Appointment appo=apt.get();
			return ResponseEntity.ok(appo);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/*
	 * design a method to update Appointment 
	 */
	public ResponseEntity<Appointment> updateAppointmentService(Appointment a,long id){
		Optional<Appointment> apt=appointmentRepository.findById(id);
		if(apt.isPresent()) {
			Appointment appo=apt.get();
			
			if(a.getAppointmentDateTime()!=null) appo.setAppointmentDateTime(a.getAppointmentDateTime());
			if(a.getReason()!=null) appo.setReason(a.getReason());
			if(a.getStatus()!=null) appo.setStatus(a.getStatus());
			if(a.getDoctor()!=null) appo.setDoctor(a.getDoctor());
			if(a.getPatient()!=null) appo.setPatient(a.getPatient());
			
			appointmentRepository.save(appo);
			
			return ResponseEntity.ok(appo);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/*
	 * Design a method to delete the record
	 */
	public ResponseEntity<String> deleteAppointmentService(long id){
		Optional<Appointment> apt=appointmentRepository.findById(id);
		if(apt.isPresent()) {
			Appointment appo=apt.get();
			appointmentRepository.delete(appo);
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}
