package com.address.service;

import java.util.List;

import com.address.dto.AddressRequest;
import com.address.dto.AddressResponse;
import com.address.exception.AddressNotFoundException;

public interface AddressService {

	AddressResponse createAddress(Long customerId, AddressRequest addressRequest);
    
    AddressResponse updateAddress(Long addressId, AddressRequest addressRequest) throws AddressNotFoundException;
    
    List<AddressResponse> getAddressesByCustomer(Long customerId);
    
    AddressResponse getAddressById(Long addressId) throws AddressNotFoundException;
    
    void deleteAddress(Long addressId) throws AddressNotFoundException;

}
