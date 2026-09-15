package com.hms_hospital_management_system.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NameInvalidException.class)
	public String NameInvalidExceptionHnadler(NameInvalidException ele) {
		return ele.getMessage();
	}
	
	@ExceptionHandler(AgeInvalidException.class)
	public String NameInvalidExceptionHnadler(AgeInvalidException ele) {
		return ele.getMessage();
	}

}
