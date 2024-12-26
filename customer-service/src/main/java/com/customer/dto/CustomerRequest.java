package com.customer.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerRequest {

	private String firstName;

	private String lastName;

	private String email;

	private String phoneNumber;

	private LocalDate registeredDate;
}
