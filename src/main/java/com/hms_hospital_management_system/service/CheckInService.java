package com.hms_hospital_management_system.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms_hospital_management_system.entity.Appointment;
import com.hms_hospital_management_system.entity.CheckIn;
import com.hms_hospital_management_system.repository.AppointmentRepository;
import com.hms_hospital_management_system.repository.CheckInRepository;

@Service
public class CheckInService {

	CheckInRepository checkInRepository;

	AppointmentRepository appointmentRepository;

	@Autowired
	public CheckInService(CheckInRepository checkInRepository, AppointmentRepository appointmentRepository) {
		this.checkInRepository = checkInRepository;
		this.appointmentRepository = appointmentRepository;
	}

//	@Autowired
//	public CheckInService(CheckInRepository checkInRepository) {
//		this.checkInRepository = checkInRepository;
//	}

	/*
	 * design a method to save CheckIn
	 */

	public ResponseEntity<CheckIn> saveCheckInService(CheckIn ck) {
		//this is for resolving null for appointment
		
		long id = ck.getAppointment().getId();
		Optional<Appointment> apt = appointmentRepository.findById(id);
		if (apt.isPresent()) {
			Appointment appo = apt.get();
			
			//inject appointment to  ck so that we will get 
			
			ck.setAppointment(appo);
			
//			Long doctorId=appo.getDoctor().getId();
//			
//			LocalDateTime checkInTime=ck.getCheckInTime();
//			
//			LocalDateTime startOfDay =checkInTime.toLocalDate().atStartOfDay();
//
//			LocalDateTime endOfDay =startOfDay.plusDays(1);
//			
//			Integer maxQueue = checkInRepository.findMaxQueueNumber(doctorId,startOfDay,endOfDay);
//			
//			
//			//generate the queue number
//			int nextQueueNumber;
//			
//			if(maxQueue ==null) {
//				nextQueueNumber=1;
//			}
//			else {
//				nextQueueNumber=maxQueue+1;
//			}
//			
//			//call setter method to set the queuNumber
//			ck.setQueueNumber(nextQueueNumber);
			
			//save the ck (direct )
			CheckIn ckk = checkInRepository.save(ck);
			return ResponseEntity.status(HttpStatus.CREATED).body(ckk);
		}
		return ResponseEntity.notFound().build();
	}

	/*
	 * design an method to fetvh All CheckIn Data
	 */
	public ResponseEntity<List<CheckIn>> fetchAllCheckInService() {
		List<CheckIn> ckk = checkInRepository.findAll();
		if (!(ckk.isEmpty())) {
			return ResponseEntity.status(HttpStatus.OK).body(ckk);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	/*
	 * design a method to fetch Checkin By Id
	 */

	public ResponseEntity<CheckIn> fetchCheckInByIdService(long id) {
		Optional<CheckIn> ckk = checkInRepository.findById(id);
		if (ckk.isPresent()) {
			CheckIn ck = ckk.get();
			return ResponseEntity.ok(ck);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/*
	 * Design a method to update the Checkin
	 */
	public ResponseEntity<CheckIn> updateCheckInservice(CheckIn ck,long id){
		Optional<CheckIn> check=checkInRepository.findById(id);
		if(check.isPresent()) {
			CheckIn c=check.get();
			
			if(ck.getAppointment()!=null) c.setAppointment(ck.getAppointment());
			if(ck.getCheckInTime()!=null) c.setCheckInTime(ck.getCheckInTime());
			if(ck.getQueueNumber()!=null) c.setQueueNumber(ck.getQueueNumber());
			if(ck.getStatus()!=null) c.setStatus(ck.getStatus());
			checkInRepository.save(c);
			return ResponseEntity.ok(c);
 		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/*
	 * Design an api to delete the data 
	 */
	public ResponseEntity<String> deleteCheckInService(long id){
		Optional<CheckIn> ck=checkInRepository.findById(id);
		if(ck.isPresent()) {
			CheckIn ckk=ck.get();
			
			//call the child and set null ----break the connection ---from child
			
			Appointment appointment = ckk.getAppointment();
	        if (appointment != null) {
	            appointment.setCheckIn(null);
	        }
	        
	        //break from parent
	        
			ckk.setAppointment(null);
			
			//then only deletion is possible 
			
			checkInRepository.delete(ckk);
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
}