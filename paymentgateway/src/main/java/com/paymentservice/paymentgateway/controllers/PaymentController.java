package com.paymentservice.paymentgateway.controllers;

import com.paymentservice.paymentgateway.models.PaymentStatus;
import com.paymentservice.paymentgateway.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    PaymentService paymentService;

    @Autowired
    PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/createLink")
    public ResponseEntity<Map<String,String>> createPaymentLink(@RequestParam String orderId){
        return new ResponseEntity<>(paymentService.createLink(orderId),HttpStatus.CREATED);
    }

    @GetMapping("/getPaymentStatus")
    public ResponseEntity<Map<String, PaymentStatus>> getPaymentStatus(@RequestParam("paymentId") String paymentId, @RequestParam("orderId") String orderId){
        return new ResponseEntity<>(paymentService.getPaymentStatus(paymentId, orderId),HttpStatus.OK);
    }
}
