package Product_service.Product_service.DTO;

import lombok.Data;

@Data
public class ProductResponse {
    private Long productId;
    private String name;
    private String category;
    private String description;
    private Double basePrice;
}
