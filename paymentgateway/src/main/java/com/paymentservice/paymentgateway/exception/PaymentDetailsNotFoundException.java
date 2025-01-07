package com.paymentservice.paymentgateway.exception;

public class PaymentDetailsNotFoundException extends RuntimeException {

	public PaymentDetailsNotFoundException(String str){
		super(str);
	}
}
