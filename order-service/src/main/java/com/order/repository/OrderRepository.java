package com.order.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

	List<Order> findByCustomerId(Long customerId);
	
    List<Order> findByCustomerIdAndOrderDateBetween(Long customerId, LocalDateTime startDate, LocalDateTime endDate);

    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);

}
