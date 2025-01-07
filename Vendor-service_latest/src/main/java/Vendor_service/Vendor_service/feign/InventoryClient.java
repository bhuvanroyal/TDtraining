package Vendor_service.Vendor_service.feign;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import Vendor_service.Vendor_service.dto.InventoryResponse;

import java.util.List;

@FeignClient(url="localhost:8083/api/inventory", value="vendor-service")
public interface InventoryClient {

  /*  @GetMapping("/{productId}/vendors")
    List<VendorResponse> getVendorsByProduct(@PathVariable("productId") Long productId); */

@GetMapping("/product/{productId}")
List<InventoryResponse> getInventoryByProduct(@PathVariable Long productId) ;
}