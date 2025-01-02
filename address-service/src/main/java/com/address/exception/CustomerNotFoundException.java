package com.address.exception;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(String str){
		super(str);
	}
}
