package com.marketplace.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private String name;
    private String price;
    private String description;
    private String submittionTime;
    private String photoUrl;
    private String uploader;
}
