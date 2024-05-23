package com.marketplace.service;

import com.marketplace.dto.ProductDto;
import com.marketplace.model.ProductWithImage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductDto> getProducts(int page);
    long getProductsAmount();

    List<ProductDto> addProduct(ProductWithImage product);
}
