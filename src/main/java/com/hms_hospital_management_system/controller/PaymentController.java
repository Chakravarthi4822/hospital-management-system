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

import com.hms_hospital_management_system.entity.Bill;
import com.hms_hospital_management_system.entity.Payment;
import com.hms_hospital_management_system.service.PaymentService;

@Controller
public class PaymentController {
	
	PaymentService paymentService;
	
	@Autowired
	public PaymentController(PaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}
	
	/*
	 * Design a method to save the Payment Record
	 * 
	 */
	@PostMapping("/payments")
	public ResponseEntity<Payment> savePaymentController(@RequestBody Payment payment) {
		return paymentService.savePaymentService(payment);
	}
	
	/*
	 * Design a method to fetch all the PAyment Info
	 */
	@GetMapping("/payments")
	public ResponseEntity<List<Payment>> fetchAllPaymentsController() {
		return paymentService.fetchAllPaymentsService();
	}
	
	/*
     * Design a method to fetch Payment By id
     */
	@GetMapping("/payments/{id}")
	public ResponseEntity<Payment> fetchPaymentByIdController(@PathVariable long id) {
		return paymentService.fetchPaymentByIdService(id);
	}
	
	/*
	 * Design a method to update the Payment
	 */
	@PatchMapping("/payments/{id}")
	public ResponseEntity<Payment> updatePaymentController(@PathVariable long id,@RequestBody Payment payment) {
		return paymentService.updatePaymentService(id, payment);
	}
	
	
	/*
	 * Design a method to delete the Payment
	 * 
	 */
	@DeleteMapping("/payments/{id}")
	public ResponseEntity<String> deletePaymentcontroller(@PathVariable long id) {
		return paymentService.deletePaymentService(id);
	}
	

}
