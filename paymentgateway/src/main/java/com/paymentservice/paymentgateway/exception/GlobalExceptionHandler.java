package com.paymentservice.paymentgateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(PaymentDetailsNotFoundException.class)
	public ResponseEntity<ErrorDetails> PaymentDetailsNotFoundExceptionHandling(Exception e, WebRequest request) {
		ErrorDetails errorResponse=new ErrorDetails();
		errorResponse.setMessage(e.getMessage());
		errorResponse.setPath(request.getDescription(false));
		errorResponse.setTimeStamp(LocalDateTime.now());
		errorResponse.setStatusCode("NOT_FOUND");
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

}
