package com.notification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.notification.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{

	List<Notification> findByCustomerId(Long customerId);
}
