package com.hms_hospital_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms_hospital_management_system.entity.Medicine;
import com.hms_hospital_management_system.repository.MedicineRepository;

@Service
public class MedicineService {
	
	MedicineRepository medicineRepository;

	@Autowired
	public MedicineService(MedicineRepository medicineRepository) {
		this.medicineRepository = medicineRepository;
	}
	
	/*
	 * Design a method to save the MEdicine Record
	 */
    public ResponseEntity<Medicine> saveMedicineService(Medicine m) {
        Medicine medicine = medicineRepository.save(m);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicine);
    }
    

    /*
     * Desingn a method to fetch all Medicine Records
     */
    
    public ResponseEntity<List<Medicine>> fetchAllMedicinesService() {
        List<Medicine> medicines = medicineRepository.findAll();
        return ResponseEntity.ok(medicines);
//        if(!(medicines.isEmpty())) {
//        	return ResponseEntity.ok(medicines);
//        }
//        else {
//        	return ResponseEntity.notFound().build();
//        }
        
    }

    /*
     * Design a method to fetch Medicine By ID
     */
    public ResponseEntity<Medicine> fetchMedicineByIdService(long id) {

        Optional<Medicine> medicine = medicineRepository.findById(id);
        if (medicine.isPresent()) {
            return ResponseEntity.ok(medicine.get());
        }
        else{
        	return ResponseEntity.notFound().build();
        }
        
    }
    

    /*
     * Design a method to update the medicine
     */ 
    public ResponseEntity<Medicine> updateMedicineService(long id, Medicine medicine) {
    	
        Optional<Medicine> existing = medicineRepository.findById(id);
        if (existing.isPresent()) {
            Medicine m = existing.get();
            
            //setters
             if(medicine.getMedicineName()!=null) m.setMedicineName(medicine.getMedicineName());
             if(medicine.getCategory()!=null) m.setCategory(medicine.getCategory());
             if(medicine.getStockQuantity()!=null)  m.setStockQuantity(medicine.getStockQuantity());
             if(medicine.getStatus()!=null) m.setStatus(medicine.getStatus());
            //save
            Medicine updated = medicineRepository.save(m);
            return ResponseEntity.ok(updated);
        }
        else {
        	 return ResponseEntity.notFound().build();
        }
       
    }

    
    /*
     * Design a method to delete the Record 
     * 
     */
    public ResponseEntity<String> deleteMedicineService(long id) {
    	//fetch
        Optional<Medicine> medicine = medicineRepository.findById(id);
        if (medicine.isPresent()) {
        	Medicine m=medicine.get();
        	//delete
            medicineRepository.delete(m);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
	

}
