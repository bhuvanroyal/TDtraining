package Inventory_Service.Inventory_Service.dto;


import lombok.Data;

@Data
public class AvailabilityResponse {
    private boolean isAvailable;
    private Integer availableStock;
    private Double price;
}
