package Vendor_service.Vendor_service.dto;


import lombok.Data;

@Data
public class VendorRequest {
    private String name;
    private String contactEmail;
    private String phone;
    private String location;
}
