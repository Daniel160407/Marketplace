package com.marketplace.service;

import com.marketplace.dto.ProductDto;
import com.marketplace.model.ProductWithImage;
import com.marketplace.repository.ProductsRepository;
import com.marketplace.util.ModelConverter;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductsRepository productsRepository;
    private final ModelConverter modelConverter;
    private final ImageService imageService;

    @Autowired
    public ProductServiceImpl(ProductsRepository productsRepository, ModelConverter modelConverter, ImageService imageService) {
        this.productsRepository = productsRepository;
        this.modelConverter = modelConverter;
        this.imageService = imageService;
    }

    @Override
    public List<ProductDto> getProducts(int page, String sort, String direction) {
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Pageable pageable = PageRequest.of(page, 6, Sort.by(sortDirection, sort));
        val products = productsRepository.findAll(pageable).getContent();
        return modelConverter.convertProductsToDtoList(products);
    }

    @Override
    public long getProductsAmount() {
        long amount = productsRepository.count();
        return amount % 6 == 0 ? amount / 6 : amount / 6 + 1;
    }

    @Override
    public List<ProductDto> addProduct(ProductWithImage product) {
        imageService.storePhoto(product.getImage(), product.getImage().getOriginalFilename());
        productsRepository.save(modelConverter.convert(product, "/images/" + product.getImage().getOriginalFilename()));
        return modelConverter.convertProductsToDtoList(productsRepository.getAllBy());
    }
}
