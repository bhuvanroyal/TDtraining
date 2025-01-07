package Product_service.Product_service.DTO;


import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String category;
    private String description;
    private Double basePrice;
}
