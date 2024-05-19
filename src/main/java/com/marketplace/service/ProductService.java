package com.marketplace.service;

import com.marketplace.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductDto> getProducts();

    ProductDto addProduct(ProductDto productDto);
}
