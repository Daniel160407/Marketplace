package com.marketplace.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@Builder
public class ProductWithImage {
    private String name;
    private String price;
    private String description;
    private String submittionTime;
    private MultipartFile image;
    private String uploader;
}
