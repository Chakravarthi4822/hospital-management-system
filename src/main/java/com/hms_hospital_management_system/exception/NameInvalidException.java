package com.hms_hospital_management_system.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;


public class NameInvalidException extends RuntimeException{

	public NameInvalidException(String msg) {
		super(msg);
	}
	
	
	

}
