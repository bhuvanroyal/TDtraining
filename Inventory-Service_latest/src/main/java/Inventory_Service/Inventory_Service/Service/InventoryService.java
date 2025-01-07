package Inventory_Service.Inventory_Service.Service;

import Inventory_Service.Inventory_Service.feign.ProductServiceClient;
import Inventory_Service.Inventory_Service.feign.VendorServiceClient;
import Inventory_Service.Inventory_Service.dto.InventoryRequest;
import Inventory_Service.Inventory_Service.dto.InventoryResponse;
import Inventory_Service.Inventory_Service.exception.InvalidInputException;
import Inventory_Service.Inventory_Service.exception.ResourceConflictException;
import Inventory_Service.Inventory_Service.exception.ResourceNotFoundException;
import Inventory_Service.Inventory_Service.dto.AvailabilityResponse;
import Inventory_Service.Inventory_Service.model.Inventory;
import Inventory_Service.Inventory_Service.repo.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lib.dto.OrderEvent;
import com.lib.dto.OrderItemResponse;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductServiceClient productServiceClient;

    @Autowired
    private VendorServiceClient vendorServiceClient;

    public InventoryResponse addInventory(InventoryRequest inventoryRequest) {
        // Validate input data
        if (inventoryRequest == null) {
            throw new InvalidInputException("Inventory request cannot be null");
        }

        // Check if product exists (via Feign Client)
        InventoryResponse productResponse = productServiceClient.getProductById(inventoryRequest.getProductId());
        if (productResponse == null) {
            throw new ResourceNotFoundException("Product with ID " + inventoryRequest.getProductId() + " not found");
        }

        // Check if vendor exists (via Feign Client)
        InventoryResponse vendorResponse = vendorServiceClient.getVendorById(inventoryRequest.getVendorId());
        if (vendorResponse == null) {
            throw new ResourceNotFoundException("Vendor with ID " + inventoryRequest.getVendorId() + " not found");
        }

        // Validate stock (should not be negative)
        if (inventoryRequest.getStock() < 0) {
            throw new InvalidInputException("Stock cannot be negative");
        }

        // Validate price (should be greater than 0)
        if (inventoryRequest.getPrice() <= 0) {
            throw new InvalidInputException("Price must be greater than 0");
        }
       
        // Validate delivery time (ensure it's a valid date or duration)
        if (inventoryRequest.getDeliveryTime() == null) {
            throw new InvalidInputException("Delivery time cannot be null");
        }

        // Check if the inventory already exists for the same product and vendor
        Optional<Inventory> existingInventory = inventoryRepository.findByProductIdAndVendorId(
                inventoryRequest.getProductId(), inventoryRequest.getVendorId());
        if (existingInventory.isPresent()) {
            throw new ResourceConflictException("Inventory already exists for this product and vendor");
        }

        // Create new inventory record
        Inventory inventory = new Inventory();
        inventory.setProductId(inventoryRequest.getProductId());
        inventory.setVendorId(inventoryRequest.getVendorId());
        inventory.setStock(inventoryRequest.getStock());
        inventory.setPrice(inventoryRequest.getPrice());
        inventory.setDeliveryTime(inventoryRequest.getDeliveryTime());

        // Save inventory to the repository
        Inventory savedInventory = inventoryRepository.save(inventory);

        // Create response object
        InventoryResponse response = new InventoryResponse();
        response.setInventoryId(savedInventory.getInventoryId());
        response.setProductId(savedInventory.getProductId());
        response.setVendorId(savedInventory.getVendorId());
        response.setStock(savedInventory.getStock());
        response.setPrice(savedInventory.getPrice());
        response.setDeliveryTime(savedInventory.getDeliveryTime());

        return response;
    }


    // Update Inventory
    public InventoryResponse updateInventory(Long inventoryId, InventoryRequest inventoryRequest) {
        // Check if the inventory exists
        Optional<Inventory> inventory = inventoryRepository.findById(inventoryId);

        if (!inventory.isPresent()) {
            throw new ResourceNotFoundException("Inventory with ID " + inventoryId + " not found");
        }

        // Retrieve the existing inventory
        Inventory existingInventory = inventory.get();

        // Perform input validation (optional but recommended)
        if (inventoryRequest.getStock() < 0) {
            throw new InvalidInputException("Stock cannot be negative");
        }

        if (inventoryRequest.getPrice() <= 0) {
            throw new InvalidInputException("Price must be greater than 0");
        }

        if (inventoryRequest.getDeliveryTime() == null) {
            throw new InvalidInputException("Delivery time cannot be null");
        }

        // Update the inventory fields
        existingInventory.setStock(inventoryRequest.getStock());
        existingInventory.setPrice(inventoryRequest.getPrice());
        existingInventory.setDeliveryTime(inventoryRequest.getDeliveryTime());

        // Save the updated inventory record
        Inventory updatedInventory = inventoryRepository.save(existingInventory);

        // Prepare the response
        InventoryResponse response = new InventoryResponse();
        response.setInventoryId(updatedInventory.getInventoryId());
        response.setProductId(updatedInventory.getProductId());
        response.setVendorId(updatedInventory.getVendorId());
        response.setStock(updatedInventory.getStock());
        response.setPrice(updatedInventory.getPrice());
        response.setDeliveryTime(updatedInventory.getDeliveryTime());

        return response;
    }

    // Delete Inventory
    public void deleteInventory(Long inventoryId) {
        inventoryRepository.deleteById(inventoryId);
    }

    // Get Inventory by Product
    public List<InventoryResponse> getInventoryByProduct(Long productId) {
        List<Inventory> inventoryList = inventoryRepository.findByProductId(productId);
        // Map to InventoryResponse and return
        return inventoryList.stream().map(inventory -> {
            InventoryResponse response = new InventoryResponse();
            response.setInventoryId(inventory.getInventoryId());
            response.setProductId(inventory.getProductId());
            response.setVendorId(inventory.getVendorId());
            response.setStock(inventory.getStock());
            response.setPrice(inventory.getPrice());
            response.setDeliveryTime(inventory.getDeliveryTime());
            return response;
        }).toList();
    }

    // Get Inventory by Vendor
    public List<InventoryResponse> getInventoryByVendor(Long vendorId) {
        List<Inventory> inventoryList = inventoryRepository.findByVendorId(vendorId);
        // Map to InventoryResponse and return
        return inventoryList.stream().map(inventory -> {
            InventoryResponse response = new InventoryResponse();
            response.setInventoryId(inventory.getInventoryId());
            response.setProductId(inventory.getProductId());
            response.setVendorId(inventory.getVendorId());
            response.setStock(inventory.getStock());
            response.setPrice(inventory.getPrice());
            response.setDeliveryTime(inventory.getDeliveryTime());
            return response;
        }).toList();
    }

    // Check Product Availability
    public AvailabilityResponse checkAvailability(Long productId, Long vendorId, Integer quantity) {
        Optional<Inventory> inventory = inventoryRepository.findByProductIdAndVendorId(productId, vendorId);

        AvailabilityResponse response = new AvailabilityResponse();
        if (inventory.isPresent()) {
            Inventory inv = inventory.get();
            response.setAvailable(inv.getStock() >= quantity);
            response.setAvailableStock(inv.getStock());
            response.setPrice(inv.getPrice());
            
        } else {
            response.setAvailable(false);
            response.setAvailableStock(0);
        }
        return response;
    }
    
    public void updateStock(OrderEvent orderEvent) {
    	for(OrderItemResponse item:orderEvent.getItems()) {
    	       
    	      inventoryRepository.reduceStock(item.getProductId(),item.getVendorId(),item.getQuantity());
    	}
    }
    
    @KafkaListener(topics="${spring.kafka.topic.name}",groupId="${spring.kafka.consumer.group-id}")
    public void handleOrderEvent(OrderEvent event) {
    	if("OrderPlaced".equals(event.getEventType())) {
    		updateStock(event);
    	}
    }
}
