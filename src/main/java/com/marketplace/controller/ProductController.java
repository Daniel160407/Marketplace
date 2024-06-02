package com.marketplace.controller;

import com.marketplace.dto.ProductDto;
import com.marketplace.model.ProductWithImage;
import com.marketplace.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
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
    public ResponseEntity<List<ProductDto>> getProducts(@RequestParam int page,
                                                        @RequestParam String sort,
                                                        @RequestParam String direction) {
        return ResponseEntity.ok().body(productService.getProducts(page, sort, direction));
    }

    @GetMapping("/amount")
    public ResponseEntity<Long> getProductsAmount() {
        Long amount = productService.getProductsAmount();
        return ResponseEntity.ok(amount);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<List<ProductDto>> addProduct(@RequestPart("title") String title,
                                                       @RequestPart("price") String price,
                                                       @RequestPart("description") String description,
                                                       @RequestPart("image") MultipartFile image,
                                                       @RequestPart("uploader") String uploader) {

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(ProductWithImage.builder()
                .name(title)
                .price(price)
                .description(description)
                .submissionTime(Instant.now())
                .image(image)
                .uploader(uploader)
                .build()
        ));
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS).build();
    }
}
