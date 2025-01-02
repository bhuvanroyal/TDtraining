package com.customer.service;

import java.util.List;

import com.customer.dto.AddressResponse;
import com.customer.dto.CustomerAddressResponse;
import com.customer.dto.CustomerRequest;
import com.customer.dto.CustomerResponse;

public interface CustomerService {

	public CustomerResponse createCustomer(CustomerRequest customerRequest);
	
	public CustomerResponse updateCustomer(Long customerId, CustomerRequest customerRequest);
	
	public CustomerResponse getCustomerById(Long customerId);

	public List<CustomerResponse> getAllCustomers();

	public void deleteCustomer(Long customerId);
	
	public CustomerAddressResponse getAddressAndCustomerByCustomer(Long customerId);
//	
//	public AddressResponse addAddressForCustomer(Long customerId, AddressRequest addressRequest);


}
