package Inventory_Service.Inventory_Service.feign;

import Inventory_Service.Inventory_Service.dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="vendor-service")
public interface VendorServiceClient {

    @GetMapping("/{vendorId}")
    InventoryResponse getVendorById(@PathVariable("vendorId") Long vendorId);
}
// url="localhost:8081/api/vendors", 