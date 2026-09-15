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
import com.hms_hospital_management_system.service.AppointmentService;

@Controller
public class AppointmentController {
	
	AppointmentService appointmentService;
	@Autowired
	public AppointmentController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	
	/*
	 * Design anapi to save Appointment record
	 */
	@PostMapping("/appointments")
	public ResponseEntity<Appointment> saveAppointmentController(@RequestBody Appointment apt){
		return appointmentService.saveAppointmentServier(apt);
	}
	
	/*
	 * Design an api to feth all Appointments
	 */
	@GetMapping("/appointments")
	public ResponseEntity<List<Appointment>> fetchAllAppointmentService(){
		return appointmentService.fetchAllAppointmentService();
	}
	
	/*
	 * Design an api to fetch Appointment By id
	 */
	@GetMapping("/appointments/{id}")
	public ResponseEntity<Appointment> fetchAppointmentById(@PathVariable long id){
		return appointmentService.fetchAppointmentById(id);
	}
	
	/*
	 * Design an api to Update Appointment
	 */
	
	@PatchMapping("/appointments/{id}")
	public ResponseEntity<Appointment> updateAppointmentService(@RequestBody Appointment a,@PathVariable long id){
		return appointmentService.updateAppointmentService(a, id);
	}
	
	/*
	 * Design an api to delete Appointment
	 */

	@DeleteMapping("/appointments/{id}")
	public ResponseEntity<String> deleteAppointmentService(@PathVariable long id){
	return appointmentService.deleteAppointmentService(id);	
	}
}
