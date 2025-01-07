package Vendor_service.Vendor_service.controller;


import Vendor_service.Vendor_service.dto.VendorRequest;
import Vendor_service.Vendor_service.dto.VendorResponse;
import Vendor_service.Vendor_service.Service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    // Create a new vendor
    @PostMapping
    public VendorResponse createVendor(@RequestBody VendorRequest vendorRequest) {
        return vendorService.createVendor(vendorRequest);
    }

    // Get vendor by ID
    @GetMapping("/{vendorId}")
    public VendorResponse getVendorById(@PathVariable Long vendorId) {
        return vendorService.getVendorById(vendorId);
    }

    // Update vendor details
    @PutMapping("/{vendorId}")
    public VendorResponse updateVendor(@PathVariable Long vendorId, @RequestBody VendorRequest vendorRequest) {
        return vendorService.updateVendor(vendorId, vendorRequest);
    }

    // Delete vendor
    @DeleteMapping("/{vendorId}")
    public void deleteVendor(@PathVariable Long vendorId) {
        vendorService.deleteVendor(vendorId);
    }

    // Get vendors for a product
    @GetMapping("/product/{productId}")
    public List<VendorResponse> getVendorsByProduct(@PathVariable Long productId) {
        return vendorService.getVendorsByProduct(productId);
    }
    
    
}
