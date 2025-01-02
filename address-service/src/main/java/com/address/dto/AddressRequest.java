package com.address.dto;

import jakarta.validation.constraints.NotBlank;
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
public class AddressRequest {
	
	@NotBlank(message = "Address line is mandatory")
	private String addressLine;
	
    @NotBlank(message = "City is mandatory")
    private String city;
    
    @NotBlank(message = "State is mandatory")
    private String state;
    
    @NotBlank(message = "Postal code is mandatory")
    private String postalCode;
    
    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

}	
