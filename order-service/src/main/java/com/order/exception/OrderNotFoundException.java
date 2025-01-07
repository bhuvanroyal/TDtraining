package com.order.exception;

import com.order.entity.Order;

public class OrderNotFoundException extends  RuntimeException{

	public OrderNotFoundException(String str) {
		super(str);
	}

	
}
