package com.marketplace.controller;

import com.marketplace.dto.ProductDto;
import com.marketplace.model.ProductWithImage;
import com.marketplace.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/marketplace/product")
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ResponseBody
    public List<ProductDto> getProducts(@RequestParam int page) {
        return productService.getProducts(page);
    }

    @GetMapping("/amount")
    public ResponseEntity<Long> getProductsAmount() {
        Long amount = productService.getProductsAmount();
        return ResponseEntity.ok(amount);
    }

    @PostMapping
    @ResponseBody
    public List<ProductDto> addProduct(@RequestPart("title") String title,
                                 @RequestPart("price") String price,
                                 @RequestPart("description") String description,
                                 @RequestPart("submittionTime") String time,
                                 @RequestPart("image") MultipartFile image) {

        return productService.addProduct(ProductWithImage.builder()
                .name(title)
                .price(price)
                .description(description)
                .submittionTime(time)
                .image(image)
                .build()
        );
    }
}
