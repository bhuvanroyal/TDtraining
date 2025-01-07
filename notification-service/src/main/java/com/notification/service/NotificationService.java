package com.notification.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.lib.dto.OrderEvent;
import com.notification.dto.NotificationResponse;
import com.notification.entity.Notification;
import com.notification.repository.NotificationRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NotificationService {
	
	private final JavaMailSender mailSender;
	
	private ModelMapper modelMapper;
	
	private NotificationRepository notificationRepository;
	
	public List<NotificationResponse> getAllNotificationsByCustomer(Long customerId){
		return notificationRepository.findByCustomerId(customerId).stream()
	            .map(notification -> modelMapper.map(notification, NotificationResponse.class))
	            .toList();
	}
	
	
	@KafkaListener(topics="${spring.kafka.topic.name}",groupId="${spring.kafka.consumer.group-id}")
    public void handleOrderEvent(OrderEvent event) {
    	if("OrderPlaced".equals(event.getEventType())) {
    		sendEmailToCustomer(event);
    	}
    }
	
	private void sendEmailToCustomer(OrderEvent event) {
        
        String customerEmail = event.getEmail();
        String subject = "Order Confirmation - Order ID: " + event.getOrderId();
        String body = buildEmailBody(event);

        Notification notification = new Notification();
        notification.setCustomerId(event.getCustomerId());
        notification.setSubject(subject);
        notification.setBody(body);
        notification.setCustomerEmail(customerEmail);
        notification.setOrderId(event.getOrderId());
        

       
            // Send Email
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(customerEmail);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom("damabhuvan29@gmail.com");
            mailSender.send(message);
            notification.setStatus(event.getStatus());
       

        // Save Notification to DB
        notificationRepository.save(notification);
    }

    private String buildEmailBody(OrderEvent event) {
        return String.format(
            "Dear Customer,\n\n" +
            "Your order has been successfully placed!\n\n" +
            "Order Details:\n" +
            "Order ID: %d\n" +
            "Total Amount: $%.2f\n\n" +
            "Thank you for shopping with us!\n" +
            "Best regards,\n" +
            "E-Commerce Team",
            event.getOrderId(), event.getTotalAmount()
        );
    }

}
