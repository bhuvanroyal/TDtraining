package Inventory_Service.Inventory_Service.dto;


import lombok.Data;

@Data
public class InventoryRequest {
    private Long productId;
    private Long vendorId;
    private Integer stock;
    private Double price;
    private String deliveryTime;
}
