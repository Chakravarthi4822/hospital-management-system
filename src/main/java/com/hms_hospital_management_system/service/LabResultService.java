package com.hms_hospital_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms_hospital_management_system.entity.LabResult;
import com.hms_hospital_management_system.entity.LabTest;
import com.hms_hospital_management_system.repository.LabResultRepository;
import com.hms_hospital_management_system.repository.LabTestRepository;

@Service
public class LabResultService {

	private LabResultRepository labResultRepository;
	private LabTestRepository labTestRepository;

	@Autowired
	public LabResultService(LabResultRepository labResultRepository, LabTestRepository labTestRepository) {
		this.labResultRepository = labResultRepository;
		this.labTestRepository = labTestRepository;
	}

	/*
	 * Design a method to save LabResult
	 */

	public ResponseEntity<LabResult> saveLabResultService(LabResult labResult) {

		// to avoid lab test info null

		long id = labResult.getLabTest().getId();
		Optional<LabTest> test = labTestRepository.findById(id);
		if (test.isPresent()) {

			LabTest labTest = test.get();

			// set to labRsult
			labResult.setLabTest(labTest);

			// save
			LabResult savedResult = labResultRepository.save(labResult);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedResult);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/*
	 * Design a method to fetch all LabRsult
	 */

	public ResponseEntity<List<LabResult>> fetchAllLabResultsService() {
		List<LabResult> results = labResultRepository.findAll();
		return ResponseEntity.ok(results);
//		if (!(results.isEmpty())) {
//			return ResponseEntity.ok(results);
//		} else {
//			return ResponseEntity.notFound().build();
//		}
	}

	/*
	 * Design a method to fetch LabRsult by id
	 */
	public ResponseEntity<LabResult> fetchLabResultByIdService(long id) {

		Optional<LabResult> result = labResultRepository.findById(id);
		if (result.isPresent()) {
			return ResponseEntity.ok(result.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/*
	 * Design a method to update the LabResult
	 */

	public ResponseEntity<LabResult> updateLabResultService(long id, LabResult labResult) {

		Optional<LabResult> existing = labResultRepository.findById(id);

		if (existing.isPresent()) {

			LabResult l = existing.get();

			if (labResult.getResult() != null)
				l.setResult(labResult.getResult());
			if (labResult.getRemarks() != null)
				l.setRemarks(labResult.getRemarks());
			if (labResult.getStatus() != null)
				l.setStatus(labResult.getStatus());
			if (labResult.getResultDate() != null)
				l.setResultDate(labResult.getResultDate());

			LabResult updated = labResultRepository.save(l);

			return ResponseEntity.ok(updated);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/*
	 * Design a method to delete LabResults
	 */
	public ResponseEntity<String> deleteLabResultService(long id) {

		Optional<LabResult> result = labResultRepository.findById(id);
		if (result.isPresent()) {
			labResultRepository.delete(result.get());
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
