package Vendor_service.Vendor_service.dto;


import lombok.Data;

@Data
public class InventoryResponse {
    private Long inventoryId;
    private Long productId;
    private Long vendorId;
    private Integer stock;
    private Double price;
    private String deliveryTime;
}
