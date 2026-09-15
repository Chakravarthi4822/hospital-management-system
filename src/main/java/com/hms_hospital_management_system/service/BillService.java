package com.hms_hospital_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms_hospital_management_system.entity.Bill;
import com.hms_hospital_management_system.entity.Patient;
import com.hms_hospital_management_system.repository.BillRepository;
import com.hms_hospital_management_system.repository.PatientRepository;

@Service
public class BillService {

	private final BillRepository billRepository;
	private final PatientRepository patientRepository;//this is for patient data getting not be null  ||exception occurs

	@Autowired
	public BillService(BillRepository billRepository, PatientRepository patientRepository) {
		this.billRepository = billRepository;
		this.patientRepository = patientRepository;
	}

		/*
		 * Design an api to save Bill
		 */
	
	public ResponseEntity<Bill> saveBillService(Bill bill) {
		
		//get the patient id
		
		long patientId = bill.getPatient().getId();
		
		//fetch patient data  by id
	
		Optional<Patient> patient = patientRepository.findById(patientId);
		if (patient.isPresent()) {
					
			//set patient data to BILL
			bill.setPatient(patient.get());

			//save Bill save + patient 
			
			Bill savedBill = billRepository.save(bill);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedBill);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	

	/*
	 * Design an api to Fetch All Bill
	 */
	
	
	public ResponseEntity<List<Bill>> fetchAllBillsService() {
		List<Bill> bills = billRepository.findAll();
		if(!(bills.isEmpty())) {
			return ResponseEntity.ok(bills);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}


	/*
	 * Design an api to FeTch BY Id
	 */
	
	public ResponseEntity<Bill> fetchBillByIdService(long id) {
		Optional<Bill> bill = billRepository.findById(id);
		if (bill.isPresent()) {
			return ResponseEntity.ok(bill.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
		
	}

	/*
	 * Design an api to Update the Bill Record
	 */
	public ResponseEntity<Bill> updateBillService(long id, Bill bill) {
		Optional<Bill> existing = billRepository.findById(id);
		if (existing.isPresent()) {
			Bill b = existing.get();
			//check for null because patch mapping
			
			if (bill.getTotalAmount() != null) b.setTotalAmount(bill.getTotalAmount());
			if (bill.getStatus() != null) b.setStatus(bill.getStatus());
			
			//update
			Bill updated = billRepository.save(b);
			return ResponseEntity.ok(updated);
		}
		else {
			return ResponseEntity.notFound().build();
		}
		
	}

	/*
	 * Design an api to delete Bill Record
	 * 
	 */
	
	public ResponseEntity<String> deleteBillService(long id) {
		Optional<Bill> bill = billRepository.findById(id);
		if (bill.isPresent()) {
			billRepository.delete(bill.get());
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
		
	}

}
