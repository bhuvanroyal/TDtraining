package com.paymentservice.paymentgateway.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Payment{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    private String transactionId;

    private String orderId;

    private String PaymentLink;

    private String PaymentMethod;

    public void setId(Long id) {
        Id = id;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setPaymentLink(String paymentLink) {
        PaymentLink = paymentLink;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Long getId() {
        return Id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getPaymentLink() {
        return PaymentLink;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
