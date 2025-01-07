package Vendor_service.Vendor_service.Service;

import Vendor_service.Vendor_service.dto.InventoryResponse;
import Vendor_service.Vendor_service.dto.VendorRequest;
import Vendor_service.Vendor_service.dto.VendorResponse;
import Vendor_service.Vendor_service.model.Vendor;
import Vendor_service.Vendor_service.repo.VendorRepository;
import feign.FeignException;
import Vendor_service.Vendor_service.feign.InventoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private InventoryClient inventoryServiceClient;

    // Create a new vendor
    public VendorResponse createVendor(VendorRequest vendorRequest) {
        Vendor vendor = new Vendor();
        vendor.setName(vendorRequest.getName());
        vendor.setContactEmail(vendorRequest.getContactEmail());
        vendor.setPhone(vendorRequest.getPhone());
        vendor.setLocation(vendorRequest.getLocation());

        Vendor savedVendor = vendorRepository.save(vendor);
        return convertToVendorResponse(savedVendor);
    }

    // Get vendor by ID
    public VendorResponse getVendorById(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));
        return convertToVendorResponse(vendor);
    }

    // Update vendor details
    public VendorResponse updateVendor(Long vendorId, VendorRequest vendorRequest) {
        Vendor existingVendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        existingVendor.setName(vendorRequest.getName());
        existingVendor.setContactEmail(vendorRequest.getContactEmail());
        existingVendor.setPhone(vendorRequest.getPhone());
        existingVendor.setLocation(vendorRequest.getLocation());

        Vendor updatedVendor = vendorRepository.save(existingVendor);
        return convertToVendorResponse(updatedVendor);
    }

    // Delete a vendor
    public void deleteVendor(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));
        vendorRepository.delete(vendor);
    }

    // Get vendors for a product using InventoryServiceClient
    public List<VendorResponse> getVendorsByProduct(Long productId) {
        /*try {
            return inventoryServiceClient.getVendorsByProduct(productId);
        } /*catch (FeignException.NotFound e) {
            throw new RuntimeException("No vendors found for product with ID: " + productId);
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching vendors for product with ID: " + productId, e);
        } */
    	
    	
    	List<InventoryResponse> inventories=inventoryServiceClient.getInventoryByProduct(productId);
    	System.out.println(inventories);
    	List<VendorResponse> vendors= new ArrayList<>();
    	for(InventoryResponse inventory:inventories) {
    		vendors.add(getVendorById(inventory.getVendorId()));
    	}
    	return vendors;
    }
    // Helper method to convert Vendor entity to VendorResponse
    private VendorResponse convertToVendorResponse(Vendor vendor) {
        VendorResponse response = new VendorResponse();
        response.setVendorId(vendor.getVendorId());
        response.setName(vendor.getName());
        response.setContactEmail(vendor.getContactEmail());
        response.setPhone(vendor.getPhone());
        response.setLocation(vendor.getLocation());
        return response;
    }
}
