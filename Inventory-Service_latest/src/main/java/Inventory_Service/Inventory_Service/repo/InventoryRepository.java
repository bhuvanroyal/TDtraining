package Inventory_Service.Inventory_Service.repo;


import Inventory_Service.Inventory_Service.model.Inventory;
import feign.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByProductId(Long productId);
    List<Inventory> findByVendorId(Long vendorId);
    Optional<Inventory> findByProductIdAndVendorId(Long productId, Long vendorId);
    
    @Modifying
    @Query("UPDATE Inventory i SET i.stock = i.stock - :quantity WHERE i.productId = :productId AND i.vendorId = :vendorId AND i.stock >= :quantity")
    int reduceStock(@Param("productId") Long productId, @Param("vendorId") Long vendorId, 
                    @Param("quantity") Integer quantity);
}
