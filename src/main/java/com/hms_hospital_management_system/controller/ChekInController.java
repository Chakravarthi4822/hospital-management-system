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

import com.hms_hospital_management_system.entity.CheckIn;
import com.hms_hospital_management_system.service.CheckInService;

@Controller
public class ChekInController {

	CheckInService checkInService;

	@Autowired
	public ChekInController(CheckInService checkInService) {
		this.checkInService = checkInService;
	}

	/*
	 * Design an api to SAve the Checkin
	 */

	@PostMapping("/checkins")
	public ResponseEntity<CheckIn> saveCheckInContrller(@RequestBody CheckIn ch) {
		return checkInService.saveCheckInService(ch);
	}

	/*
	 * Design method to fetchAll Checkins
	 */
	@GetMapping("/checkins")
	public ResponseEntity<List<CheckIn>> fetchAllCheckInService() {
		return checkInService.fetchAllCheckInService();
	}

	/*
	 * Design an api to fetch By id
	 */
	@GetMapping("/checkins/{id}")
	public ResponseEntity<CheckIn> fetchCheckInController(@PathVariable long id) {
		return checkInService.fetchCheckInByIdService(id);
	}

	/*
	 * Design an api to update
	 */
	@PatchMapping("/checkins/{id}")
	public ResponseEntity<CheckIn> updateCheckInservice(@RequestBody CheckIn ck, @PathVariable long id) {
		return checkInService.updateCheckInservice(ck, id);
	}

	/*
	 * Design an api to delete the CheckIn
	 */

	@DeleteMapping("/checkins/{id}")
	public ResponseEntity<String> deleteCheckInController(@PathVariable long id) {
		return checkInService.deleteCheckInService(id);
	}
}
