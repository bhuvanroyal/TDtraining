package Vendor_service.Vendor_service.repo;

import Vendor_service.Vendor_service.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    List<Vendor> findByNameContaining(String name);
}
