package com.customer.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.customer.dto.CustomerAddressResponse;
import com.customer.dto.CustomerRequest;
import com.customer.dto.CustomerResponse;
import com.customer.entity.Customer;
import com.customer.exception.CustomerNotFoundException;
import com.customer.feign.AddressClient;
import com.customer.repository.CustomerRepository;
import com.customer.service.CustomerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{
	
	private CustomerRepository customerRepository;
	
	private AddressClient addressClient;
	
	private ModelMapper modelMapper;

	@Override
	public CustomerResponse createCustomer(CustomerRequest customerRequest) {
		Customer customer=customerRepository.save(modelMapper.map(customerRequest, Customer.class));
		return modelMapper.map(customer, CustomerResponse.class);
	}

	@Override
	public CustomerResponse updateCustomer(Long customerId, CustomerRequest customerRequest){
		Customer customer=customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer Id witn " +customerId +" does not exist"));
		customer.setFirstName(customerRequest.getFirstName());
		customer.setLastName(customerRequest.getLastName());
		customer.setPhoneNumber(customerRequest.getPhoneNumber());
		customer.setEmail(customerRequest.getEmail());
		customerRepository.save(customer);
		return modelMapper.map(customer, CustomerResponse.class);
		
	}

	@Override
	public CustomerResponse getCustomerById(Long customerId) {
		Customer customer=customerRepository.findById(customerId).orElseThrow(()->
		new CustomerNotFoundException("Customer with id " +customerId +" not found"));
		return modelMapper.map(customer, CustomerResponse.class);
	}

	@Override
	public List<CustomerResponse> getAllCustomers() {
		return customerRepository.findAll().stream().map(customer->modelMapper.map(customer,CustomerResponse.class)).toList();
	}

	@Override
	public void deleteCustomer(Long customerId) {
		Customer customer=customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer Id witn " +customerId +" does not exist"));
		customerRepository.delete(customer);
	}

	@Override
	public CustomerAddressResponse getAddressAndCustomerByCustomer(Long customerId) {
		CustomerAddressResponse response=new CustomerAddressResponse();
		response.setCustomer(getCustomerById(customerId));
		response.setAddresses(addressClient.getAddressByCustomerId(customerId));
		return response;
	}

}
