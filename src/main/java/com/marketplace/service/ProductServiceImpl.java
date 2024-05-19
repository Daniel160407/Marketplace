package com.marketplace.service;

import com.marketplace.dto.ProductDto;
import com.marketplace.repository.ProductsRepository;
import com.marketplace.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductsRepository productsRepository;
    private final ModelConverter modelConverter;

    @Autowired
    public ProductServiceImpl(ProductsRepository productsRepository, ModelConverter modelConverter) {
        this.productsRepository = productsRepository;
        this.modelConverter = modelConverter;
    }

    @Override
    public List<ProductDto> getProducts() {
        return modelConverter.convertProductsToDtoList(productsRepository.getAllBy());
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        productsRepository.save(modelConverter.convert(productDto));
        return productDto;
    }
}
