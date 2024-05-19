package com.marketplace.util;

import com.marketplace.dto.ProductDto;
import com.marketplace.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelConverter {
    public List<ProductDto> convertProductsToDtoList(List<Product> products) {
        List<ProductDto> productDtos = new ArrayList<>();
        products.forEach(product -> productDtos.add(
                ProductDto.builder()
                        .name(product.getName())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .submittionTime(product.getSubmittionTime())
                        .photoUrl(product.getPhotoUrl())
                        .build()
        ));
        return productDtos;
    }

    public Product convert(ProductDto productDto) {
        return Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .submittionTime(productDto.getSubmittionTime())
                .photoUrl(productDto.getPhotoUrl())
                .build();
    }
}
