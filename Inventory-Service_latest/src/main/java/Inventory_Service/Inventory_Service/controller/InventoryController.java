package Inventory_Service.Inventory_Service.controller;


import Inventory_Service.Inventory_Service.dto.InventoryRequest;
import Inventory_Service.Inventory_Service.dto.InventoryResponse;
import Inventory_Service.Inventory_Service.exception.InvalidInputException;
import Inventory_Service.Inventory_Service.exception.ResourceConflictException;
import Inventory_Service.Inventory_Service.exception.ResourceNotFoundException;
import Inventory_Service.Inventory_Service.dto.AvailabilityResponse;
import Inventory_Service.Inventory_Service.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Add Inventory
    @PostMapping
    public ResponseEntity<InventoryResponse> addInventory(@RequestBody InventoryRequest inventoryRequest) {
        try {
            InventoryResponse response = inventoryService.addInventory(inventoryRequest);
            return ResponseEntity.ok(response);
        } catch (InvalidInputException | ResourceNotFoundException | ResourceConflictException e) {
            return ResponseEntity.badRequest().body(null); // You can add more detailed error response here
        }
    }

    @PutMapping("/{inventoryId}")
    public InventoryResponse updateInventory(@PathVariable Long inventoryId, @RequestBody InventoryRequest inventoryRequest) {
        return inventoryService.updateInventory(inventoryId, inventoryRequest);
    }


    // Delete Inventory
    @DeleteMapping("/{inventoryId}")
    public void deleteInventory(@PathVariable Long inventoryId) {
        inventoryService.deleteInventory(inventoryId);
    }

    // Get Inventory by Product
    @GetMapping("/product/{productId}")
    public List<InventoryResponse> getInventoryByProduct(@PathVariable Long productId) {
        return inventoryService.getInventoryByProduct(productId);
    }

    // Get Inventory by Vendor
    @GetMapping("/vendor/{vendorId}")
    public List<InventoryResponse> getInventoryByVendor(@PathVariable Long vendorId) {
        return inventoryService.getInventoryByVendor(vendorId);
    }

    // Check Availability
    @GetMapping("/availability")
    public AvailabilityResponse checkAvailability(@RequestParam Long productId, @RequestParam Long vendorId, @RequestParam Integer quantity) {
        return inventoryService.checkAvailability(productId, vendorId, quantity);
    }
}
