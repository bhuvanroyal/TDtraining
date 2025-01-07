package com.notification.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.dto.NotificationResponse;
import com.notification.service.NotificationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/notification")
public class NotificationController {
	
	private NotificationService notificationService;
	
	@GetMapping("/{customerId}")
	public ResponseEntity<List<NotificationResponse>> getNotificationsByCustomerId(@PathVariable Long customerId){
		return new ResponseEntity<>(notificationService.getAllNotificationsByCustomer(customerId),HttpStatus.OK);
	}
	
	

}
