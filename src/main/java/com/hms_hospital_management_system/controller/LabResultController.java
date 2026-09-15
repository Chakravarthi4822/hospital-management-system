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

import com.hms_hospital_management_system.entity.LabResult;
import com.hms_hospital_management_system.entity.LabTest;
import com.hms_hospital_management_system.service.LabResultService;

@Controller
public class LabResultController {

	LabResultService labResultService;

	@Autowired
	public LabResultController(LabResultService labResultService) {
		this.labResultService = labResultService;
	}

	/*
	 * Design an api to save LabResult
	 */

	@PostMapping("/labresults")
	public ResponseEntity<LabResult> saveLabResultController(@RequestBody LabResult labResult) {
		return labResultService.saveLabResultService(labResult);
	}

	/*
	 * Design an api to fetch LabRsult
	 */
	@GetMapping("/labresults")
	public ResponseEntity<List<LabResult>> fetchAllLabResultsController() {
		return labResultService.fetchAllLabResultsService();
	}

	/*
	 * Design an api to fetch LabRsult by id
	 */
	@GetMapping("/labresults/{id}")
	public ResponseEntity<LabResult> fetchLabResultByIdController(@PathVariable long id) {
		return labResultService.fetchLabResultByIdService(id);
	}

	/*
	 * Design an api to update the LabResult
	 */
	@PatchMapping("/labresults/{id}")
	public ResponseEntity<LabResult> updateLabResultController(@PathVariable long id,
			@RequestBody LabResult labResult) {
		return labResultService.updateLabResultService(id, labResult);
	}

	/*
	 * Design an api to delete LabResults
	 */
	@DeleteMapping("/labresults/{id}")
	public ResponseEntity<String> deleteLabResultController(@PathVariable long id) {
		return labResultService.deleteLabResultService(id);

	}

}
