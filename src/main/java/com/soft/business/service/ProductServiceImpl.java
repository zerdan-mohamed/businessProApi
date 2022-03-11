package com.soft.business.service;

import com.soft.business.dto.ProductDto;
import com.soft.business.mapper.ProductMapper;
import com.soft.business.model.Product;
import com.soft.business.repository.ProductRepository;
import com.soft.business.util.validator.ProductValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private ProductMapper productMapper;

    private ProductValidator productValidator;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> findProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);

        return productMapper.productsToProductsDto(products);
    }

    @Override
    public List<ProductDto> findProductsByName(String name) {
        List<Product> products = new ArrayList<>();
        productRepository.findByNameContaining(name).forEach(products::add);

        return productMapper.productsToProductsDto(products);
    }

    @Override
    public Optional<ProductDto> findProductByUuid(String uuid) {
        Optional<Product> product = productRepository.findByUuid(uuid);

        return productMapper.makeDtoFromOptionalProduct(product);
    }

    @Override
    public void deleteProductByUuid(String uuid) {
        long isDeleted = productRepository.deleteByUuid(uuid);
        if (isDeleted == 0) throw new NoSuchElementException();
    }

    @Override
    public ResponseEntity<?>  createProduct(ProductDto productDto) {
        try {
            productValidator.createProductValidator(productDto);
            Product product = productRepository.save(productMapper.makeProductFromDto(productDto));

            return new ResponseEntity<>(productMapper.makeDtoFromProduct(product), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // #####################################################################################

    @Override
    public ResponseEntity<?> updateProductByUuid(String uuid, ProductDto productDto) {
        Optional<Product> optProduct = this.productRepository.findByUuid(uuid);

        if(optProduct.isEmpty()) throw new NoSuchElementException();

        Product product = productMapper.makeProductFromDto(productDto);

        return new ResponseEntity<>(
                productMapper.makeDtoFromProduct(productRepository.save(product)),
                HttpStatus.OK
        );
    }


}
