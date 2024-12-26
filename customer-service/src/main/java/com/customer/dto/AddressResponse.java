package com.customer.dto;

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
	
	private String location;
	
	private Long pindcode;
	
	private String landmark;

}
