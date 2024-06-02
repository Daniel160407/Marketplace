package com.marketplace.util;

import com.marketplace.dto.ProductDto;
import com.marketplace.dto.UserDto;
import com.marketplace.model.Product;
import com.marketplace.model.ProductWithImage;
import com.marketplace.model.User;
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
                        .uploader(product.getUploader())
                        .build()
        ));
        return productDtos;
    }

    public Product convert(ProductWithImage product, String imgUrl) {
        return Product.builder()
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .submittionTime(product.getSubmittionTime())
                .photoUrl(imgUrl)
                .uploader(product.getUploader())
                .build();
    }

    public User convert(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }

    public UserDto convert(User user) {
        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
