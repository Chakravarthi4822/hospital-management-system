package com.hms_hospital_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms_hospital_management_system.entity.Bill;
import com.hms_hospital_management_system.entity.Payment;
import com.hms_hospital_management_system.repository.BillRepository;
import com.hms_hospital_management_system.repository.PaymentRepository;

@Service
public class PaymentService {

	private  PaymentRepository paymentRepository;
	private  BillRepository billRepository;

	@Autowired
	public PaymentService(PaymentRepository paymentRepository, BillRepository billRepository) {
		this.paymentRepository = paymentRepository;
		this.billRepository = billRepository;
	}

	/*
	 * Design a method to save the Payment Record
	 * 
	 */
	public ResponseEntity<Payment> savePaymentService(Payment payment) {
		
		/*
		 *  HERE ARE PERFORMING TWO ACTIONS SAVE PAYMENT(DONE)+ UPDATE BILL(PAID)
		 */
		
		
		//find the bill id correspondent to payment
		long billId = payment.getBill().getId();

		//find the if information
		
		Optional<Bill> bill = billRepository.findById(billId);

		if (bill.isPresent()) {

			//set the bill info to payment
			
			payment.setBill(bill.get());
			
			// id it exist then save the payment(done payment) and change status 
			
			Payment savedPayment = paymentRepository.save(payment);

			// Update bill status
			
			Bill b = bill.get();
			//set bill
			b.setStatus("PAID");
			
			//save update bill
			billRepository.save(b);

			return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	/*
	 * Design a method to fetch all the PAyment Info
	 */
	
	public ResponseEntity<List<Payment>> fetchAllPaymentsService() {
		List<Payment> payments = paymentRepository.findAll();
		if(!(payments.isEmpty())) {
			return ResponseEntity.ok(payments);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
    /*
     * Design a method to fetch Payment By id
     */
	
	public ResponseEntity<Payment> fetchPaymentByIdService(long id) {
		Optional<Payment> payment = paymentRepository.findById(id);
		if (payment.isPresent()) {
			return ResponseEntity.ok(payment.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}


	/*
	 * Design a method to update the Payment
	 */
	public ResponseEntity<Payment> updatePaymentService(long id, Payment payment) {
		Optional<Payment> existing = paymentRepository.findById(id);
		if (existing.isPresent()) {
			Payment p = existing.get();
			
			//check  null and update
			
			if (payment.getAmount() != null) p.setAmount(payment.getAmount());
			if (payment.getPaymentMethod() != null) p.setPaymentMethod(payment.getPaymentMethod());
	
			//update mean save updated record
			Payment updated = paymentRepository.save(p);

			return ResponseEntity.ok(updated);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	/*
	 * Design a method to delete the Payment
	 * 
	 */
	
	public ResponseEntity<String> deletePaymentService(long id) {
		//fetch
		Optional<Payment> payment = paymentRepository.findById(id);
		if (payment.isPresent()) {
					//get and delete 
			
			paymentRepository.delete(payment.get());
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}
