package com.paymentservice.paymentgateway.services;

import com.paymentservice.paymentgateway.dtos.PaymentLinkRequestDto;
import com.paymentservice.paymentgateway.exception.PaymentDetailsNotFoundException;
import com.paymentservice.paymentgateway.models.Payment;
import com.paymentservice.paymentgateway.models.PaymentStatus;
import com.paymentservice.paymentgateway.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PaymentService {


    private final PaymentGateway paymentGateway;

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentGateway paymentGateway, PaymentRepository paymentRepository) {
        this.paymentGateway = paymentGateway;
        this.paymentRepository = paymentRepository;
    }

    public Map<String,String> createLink(String orderId){

        /*Make a call to order service and get the order details.
        OrderDetail order = restTemplate.getMapping(orderId)
        `name = order.getCustomerName()
        amount = order.getAmount()
        phone = order.getCustomerPhone()*/
        PaymentLinkRequestDto paymentLinkRequestDto = new PaymentLinkRequestDto();
        paymentLinkRequestDto.setCustomerName("vipin");
        paymentLinkRequestDto.setOrderId(orderId);
        paymentLinkRequestDto.setPhone("9627115851");

        paymentLinkRequestDto.setAmount(1*100);
        // Generate payment link using the payment gateway
        String paymentLink = paymentGateway.createPaymentLink(paymentLinkRequestDto);
        Map<String,String> linkMap= new HashMap<>();
        linkMap.put("paymentLink",paymentLink);
        // Save payment details in the repository
        Payment paymentResponse = new Payment();
        paymentResponse.setPaymentLink(paymentLink);
        paymentResponse.setOrderId(orderId);
        paymentRepository.save(paymentResponse);
        return linkMap;
    }

    public Map<String,PaymentStatus> getPaymentStatus(String paymentId, String orderId) {
        // Retrieve payment details by order ID
        Optional<Payment> paymentDetails = Optional.ofNullable(paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new PaymentDetailsNotFoundException("Payment Details Not found with id" +orderId + "not found")));

        // Get payment status from the payment gateway
        PaymentStatus status = paymentGateway.getStatus(paymentId, orderId);

        // Update and save payment details with the new status
        Payment paymentResponse = paymentDetails.get();
        Map<String,PaymentStatus> statusMap= new HashMap<>();
        statusMap.put("Payment status",status);
        paymentResponse.setStatus(status);
        paymentResponse.setTransactionId(paymentId);
        paymentRepository.save(paymentResponse);
        return statusMap;
    }
}
