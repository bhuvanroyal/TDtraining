package com.address.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.address.dto.AddressRequest;
import com.address.dto.AddressResponse;
import com.address.exception.AddressNotFoundException;
import com.address.service.AddressService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/address")
public class AddressController {

	AddressService addressService;
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<AddressResponse>> getAddressByCustomerId(@PathVariable Long customerId){
		
		return new ResponseEntity<>(addressService.getAddressesByCustomer(customerId),HttpStatus.OK);
	}
	
	@PostMapping("/{customerId}")
	public ResponseEntity<AddressResponse> createAddress(@PathVariable Long customerId,@Valid @RequestBody AddressRequest addressRequest){
		return new ResponseEntity<>(addressService.createAddress(customerId, addressRequest),HttpStatus.CREATED);
	}
	
	@PutMapping("/{addressId}")
	public ResponseEntity<AddressResponse> updateAddressById(@PathVariable Long addressId,@Valid @RequestBody AddressRequest addressRequest) throws AddressNotFoundException
	{
		return new ResponseEntity<>(addressService.updateAddress(addressId, addressRequest),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{addressId}")
	public ResponseEntity<AddressResponse> getAddressById(@PathVariable Long addressId) throws AddressNotFoundException{
		return new ResponseEntity<>(addressService.getAddressById(addressId),HttpStatus.OK);
	}
	
	@DeleteMapping("/{addressId}")
	public ResponseEntity<Void> deleteAddressById(@PathVariable Long addressId) throws AddressNotFoundException{
		addressService.deleteAddress(addressId);
		return ResponseEntity.noContent().build();
	}
	
}
