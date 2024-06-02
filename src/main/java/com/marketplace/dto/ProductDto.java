package com.marketplace.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ProductDto {
    private String name;
    private String price;
    private String description;
    private Instant submissionTime;
    private String photoUrl;
    private String uploader;
}
