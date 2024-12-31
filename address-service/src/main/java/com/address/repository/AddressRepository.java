package com.address.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.address.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long>{

	List<Address> findByCustomerId(Long customerId);

}
