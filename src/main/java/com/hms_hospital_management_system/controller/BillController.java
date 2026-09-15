package com.hms_hospital_management_system.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hms_hospital_management_system.entity.Bill;
import com.hms_hospital_management_system.service.BillService;

@Controller
public class BillController {

	 BillService billService;

	@Autowired
	public BillController(BillService billService) {
		this.billService = billService;
	}

	/*
	 * Design a method to save Bill
	 */

	@PostMapping("/bills")
	public ResponseEntity<Bill> saveBillController(@RequestBody Bill bill) {
		return billService.saveBillService(bill);
	}

	
	/*
	 * Design a method to Fetch All Bill
	 */
	
	@GetMapping("/bills")
	public ResponseEntity<List<Bill>> fetchAllBillsController() {
		return billService.fetchAllBillsService();
	}
	
	/*
	 * Design a method to FeTch BY Id
	 */
	@GetMapping("/bills/{id}")
	public ResponseEntity<Bill> fetchBillByIdController(@PathVariable long id) {
		return billService.fetchBillByIdService(id);
	}
	
	/*
	 * Design a method to Update the Bill Record
	 */
	@PatchMapping("/bills/{id}")
	public ResponseEntity<Bill> updateBillController(@PathVariable long id,@RequestBody Bill bill) {
		return billService.updateBillService(id, bill);
	}
		
	
	/*
	 * Design a method to delete Bill Record
	 * 
	 */
	@DeleteMapping("bills/{id}")
	public ResponseEntity<String> deleteBillController(@PathVariable long id) {
		return billService.deleteBillService(id);
	}
}
