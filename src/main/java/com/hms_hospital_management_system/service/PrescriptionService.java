package com.hms_hospital_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms_hospital_management_system.entity.Consultation;
import com.hms_hospital_management_system.entity.Prescription;
import com.hms_hospital_management_system.repository.ConsultationRepository;
import com.hms_hospital_management_system.repository.PrescriptionRepository;

@Service
public class PrescriptionService {

	private PrescriptionRepository prescriptionRepository;
	private ConsultationRepository consultationRepository;

	@Autowired
	public PrescriptionService(PrescriptionRepository prescriptionRepository,
			ConsultationRepository consultationRepository) {
		this.prescriptionRepository = prescriptionRepository;
		this.consultationRepository = consultationRepository;
	}

	/*
	 * Design a method to save Prescription
	 */

	public ResponseEntity<Prescription> savePrescriptionService(Prescription p) {
		long id = p.getConsultation().getId();
		Optional<Consultation> con = consultationRepository.findById(id);

		if (con.isPresent()) {
			p.setConsultation(con.get());

			Prescription prescript = prescriptionRepository.save(p);
			return ResponseEntity.status(HttpStatus.CREATED).body(prescript);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/*
	 * Design a method to fetch all the Prescription Records
	 */
	public ResponseEntity<List<Prescription>> fetchAllPrescriptionService() {

		List<Prescription> prescriptions = prescriptionRepository.findAll();
		if (!(prescriptions.isEmpty())) {
			return ResponseEntity.ok(prescriptions);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/*
	 * Design a method to fetch Prescription record by id
	 */
	public ResponseEntity<Prescription> fetchPrescriptionByIdService(long id) {

		Optional<Prescription> prescription = prescriptionRepository.findById(id);
		if (prescription.isPresent()) {
			return ResponseEntity.ok(prescription.get());
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	/*
	 * Design a method to update the PRescription
	 */

	public ResponseEntity<Prescription> updatePrescriptionService(long id, Prescription pre) {

		Optional<Prescription> existing = prescriptionRepository.findById(id);

		if (existing.isPresent()) {

			Prescription prescription = existing.get();

			if (pre.getMedicineName() != null)
				prescription.setMedicineName(pre.getMedicineName());
			if (pre.getDosage() != null)
				prescription.setDosage(pre.getDosage());
			if (pre.getFrequency() != null)
				prescription.setFrequency(pre.getFrequency());
			if (pre.getDuration() != null)
				prescription.setDuration(pre.getDuration());
			if (pre.getInstructions() != null)
				prescription.setInstructions(pre.getInstructions());

			Prescription updated = prescriptionRepository.save(prescription);

			return ResponseEntity.ok(updated);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/*
	 * design a method to delete the Prescription
	 */
	public ResponseEntity<String> deletePrescriptionService(long id) {
		// find
		Optional<Prescription> prescription = prescriptionRepository.findById(id);

		if (prescription.isPresent()) {
			Prescription p = prescription.get();
			// delete
			prescriptionRepository.delete(p);

			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
