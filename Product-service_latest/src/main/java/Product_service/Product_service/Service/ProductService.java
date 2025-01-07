package Product_service.Product_service.Service;


import Product_service.Product_service.DTO.ProductRequest;
import Product_service.Product_service.DTO.ProductResponse;
import Product_service.Product_service.model.Product;
import Product_service.Product_service.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setDescription(productRequest.getDescription());
        product.setBasePrice(productRequest.getBasePrice());

        Product savedProduct = productRepository.save(product);

        return convertToProductResponse(savedProduct);
    }

    public ProductResponse updateProduct(Long productId, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(productRequest.getName());
        existingProduct.setCategory(productRequest.getCategory());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setBasePrice(productRequest.getBasePrice());

        Product updatedProduct = productRepository.save(existingProduct);

        return convertToProductResponse(updatedProduct);
    }

    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

    public ProductResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return convertToProductResponse(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToProductResponse)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> searchProducts(String name) {
        List<Product> products = productRepository.findByNameContaining(name);
        return products.stream()
                .map(this::convertToProductResponse)
                .collect(Collectors.toList());
    }

    private ProductResponse convertToProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setProductId(product.getProductId());
        response.setName(product.getName());
        response.setCategory(product.getCategory());
        response.setDescription(product.getDescription());
        response.setBasePrice(product.getBasePrice());
        return response;
    }
}
