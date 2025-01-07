package com.order.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String,String> errors=new HashMap<>();
		List<ObjectError> errorList=ex.getAllErrors();
		errorList.forEach(error->{
			String errorType=((FieldError)error).getField();
			String errorMessage=error.getDefaultMessage();
			errors.put(errorType, errorMessage);
		});
		return new ResponseEntity<>(errors,status);
		
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleCustomerNotFound(CustomerNotFoundException e, WebRequest request) {
		ErrorDetails errorResponse=new ErrorDetails();
		errorResponse.setMessage(e.getMessage());
		errorResponse.setPath(request.getDescription(false));
		errorResponse.setTimeStamp(LocalDateTime.now());
		errorResponse.setStatusCode("NOT_FOUND");
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleProductNotFound(ProductNotFoundException e, WebRequest request) {
		ErrorDetails errorResponse=new ErrorDetails();
		errorResponse.setMessage(e.getMessage());
		errorResponse.setPath(request.getDescription(false));
		errorResponse.setTimeStamp(LocalDateTime.now());
		errorResponse.setStatusCode("NOT_FOUND");
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InventoryUnavailableException.class)
	public ResponseEntity<ErrorDetails> handleInventoryUnvailable(InventoryUnavailableException e, WebRequest request) {
		ErrorDetails errorResponse=new ErrorDetails();
		errorResponse.setMessage(e.getMessage());
		errorResponse.setPath(request.getDescription(false));
		errorResponse.setTimeStamp(LocalDateTime.now());
		errorResponse.setStatusCode("NOT_FOUND");
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleOrderNotfound(OrderNotFoundException e, WebRequest request) {
		ErrorDetails errorResponse=new ErrorDetails();
		errorResponse.setMessage(e.getMessage());
		errorResponse.setPath(request.getDescription(false));
		errorResponse.setTimeStamp(LocalDateTime.now());
		errorResponse.setStatusCode("NOT_FOUND");
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
}
