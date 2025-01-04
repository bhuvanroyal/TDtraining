package com.order.exception;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(String str) {
		super(str);
	}

}
