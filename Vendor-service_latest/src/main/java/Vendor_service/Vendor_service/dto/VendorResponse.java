package Vendor_service.Vendor_service.dto;

import Vendor_service.Vendor_service.model.Vendor;
import lombok.Data;

@Data
public class VendorResponse {
    public VendorResponse(Vendor vendor) {
		// TODO Auto-generated constructor stub
	}
	public VendorResponse() {
		// TODO Auto-generated constructor stub
	}
	private Long vendorId;
    private String name;
    private String contactEmail;
    private String phone;
    private String location;
}
