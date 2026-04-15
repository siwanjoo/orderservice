package com.example.orderservice.product.service;

import com.example.orderservice.common.exception.ProductNotFoundException;
import com.example.orderservice.product.domain.Product;
import com.example.orderservice.product.dto.CreateProductRequest;
import com.example.orderservice.product.dto.ProductResponse;
import com.example.orderservice.product.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse createProduct(CreateProductRequest request) {
        Product product = Product.builder()
                .name(request.name())
                .price(request.price())
                .stock(request.stock())
                .build();
        Product saved = productRepository.save(product);
        return ProductResponse.from(saved);
    }

    public List<ProductResponse> getProducts() {
        return productRepository.findAll().stream()
                .map(ProductResponse::from)
                .toList();
    }

    public ProductResponse getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        return ProductResponse.from(product);
    }
}
