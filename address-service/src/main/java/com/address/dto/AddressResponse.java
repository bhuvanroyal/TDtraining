package com.address.dto;

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
public class AddressResponse {
	private Long addressId;
	private Long customerId;
	private String addressLine;
	private String city;
	private String state;
	private String postalCode;
	private String phoneNumber;
}
