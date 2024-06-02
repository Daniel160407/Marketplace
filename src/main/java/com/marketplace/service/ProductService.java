package com.marketplace.service;

import com.marketplace.dto.ProductDto;
import com.marketplace.model.ProductWithImage;

import java.util.List;

public interface ProductService {
    List<ProductDto> getProducts(int page, String sort, String direction);
    long getProductsAmount();
    List<ProductDto> addProduct(ProductWithImage product);
}
