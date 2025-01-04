package com.order.dto;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

	private Long customerId;
	
	private String firstName;

	private String lastName;

	private String email;

	private String phoneNumber;

	private LocalDate registeredDate;
}
