package com.address.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.address.dto.AddressRequest;
import com.address.dto.AddressResponse;
import com.address.entity.Address;
import com.address.exception.AddressNotFoundException;
import com.address.repository.AddressRepository;
import com.address.service.AddressService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {
	
	private AddressRepository addressRepository;
	
	private ModelMapper modelMapper;
	
	@Override
	public AddressResponse createAddress(Long customerId, AddressRequest addressRequest) {
		
		Address address=new Address();
		address.setAddressLine(addressRequest.getAddressLine());
		address.setCity(addressRequest.getCity());
		address.setPhoneNumber(addressRequest.getPhoneNumber());
		address.setPostalCode(addressRequest.getPhoneNumber());
		address.setState(addressRequest.getState());
		address.setCustomerId(customerId);
		return modelMapper.map(addressRepository.save(address), AddressResponse.class);
	}

	@Override
	public AddressResponse updateAddress(Long addressId, AddressRequest addressRequest) throws AddressNotFoundException {
		
		Address address = addressRepository.findById(addressId)
	            .orElseThrow(() -> new AddressNotFoundException("Address with ID " + addressId + " not found"));
			
			address.setAddressLine(addressRequest.getAddressLine());
			address.setCity(addressRequest.getCity());
			address.setPhoneNumber(addressRequest.getPhoneNumber());
			address.setPostalCode(addressRequest.getPhoneNumber());
			address.setState(addressRequest.getState());
			return modelMapper.map(addressRepository.save(address), AddressResponse.class);
		
	}

	@Override
	public List<AddressResponse> getAddressesByCustomer(Long customerId) {
		return addressRepository.findByCustomerId(customerId).stream().map(address->
		modelMapper.map(address,AddressResponse.class)).toList();
	}

	@Override
	public AddressResponse getAddressById(Long addressId) throws AddressNotFoundException {
		Address address = addressRepository.findById(addressId)
	            .orElseThrow(() -> new AddressNotFoundException("Address with ID " + addressId + " not found"));
		return modelMapper.map(address, AddressResponse.class);
	}

	@Override
	public void deleteAddress(Long addressId) throws AddressNotFoundException {
		Address address = addressRepository.findById(addressId)
	            .orElseThrow(() -> new AddressNotFoundException("Address with ID " + addressId + " not found"));
		addressRepository.delete(address);
		
	}

}
