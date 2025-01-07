package com.notification.dto;

import lombok.Data;

@Data
public class NotificationResponse {

	private Long id;

    private Long orderId;

    private Long customerId;
    
    private String customerEmail;
    
    private String subject;

    private String body;

    private String status;
}
