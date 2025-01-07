package Inventory_Service.Inventory_Service.feign;


import Inventory_Service.Inventory_Service.dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="product-service")
public interface ProductServiceClient {

    @GetMapping("/{productId}")
    InventoryResponse getProductById(@PathVariable("productId") Long productId);
}
//url="localhost:8082/api/products", 