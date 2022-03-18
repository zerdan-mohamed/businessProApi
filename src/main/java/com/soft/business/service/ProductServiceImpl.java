package com.soft.business.service;

import com.soft.business.dto.ProductDto;
import com.soft.business.mapper.ProductMapper;
import com.soft.business.model.Product;
import com.soft.business.model.ProductFamily;
import com.soft.business.repository.ProductRepository;
import com.soft.business.util.validator.ProductValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private ProductMapper productMapper;

    private ProductValidator productValidator;

    public ProductServiceImpl(
            ProductRepository productRepository,
            ProductValidator productValidator,
            ProductMapper productMapper
    ) {
       this.productRepository = productRepository;
       this.productValidator = productValidator;
       this.productMapper = productMapper;
    }

    @Override
    public List<ProductDto> findProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productsDto = new ArrayList<>();

        products.forEach(
                product -> productsDto.add(productMapper.makeDtoFromProduct(product))
        );

        return productsDto;
    }

    @Override
    public ResponseEntity<?> findProductByUuid(String uuid) {
        Optional<Product> optionalProduct = productRepository.findByUuid(uuid);

        if (optionalProduct.isPresent()) {
            ProductDto productDto = productMapper.makeDtoFromProduct(optionalProduct.get());
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteProductByUuid(String uuid) {
        long isDeleted = productRepository.deleteByUuid(uuid);

        if(isDeleted != 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?>  createProduct(ProductDto productDto) {
        try {
            productValidator.createProductValidator(productDto);
            Product p = productMapper.makeProductFromDto(productDto);
            productRepository.save(p);

            return new ResponseEntity<>(productDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateProductByUuid(String uuid, ProductDto productDto) {
        Optional<Product> product = this.productRepository.findByUuid(uuid);

        if(product.isPresent()) {
            Product updatedProduct = productMapper.updateProduct(productDto, product.get());
            Product savedProduct = productRepository.save(updatedProduct);

            return new ResponseEntity<>(
                    productMapper.makeDtoFromProduct(savedProduct),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }




    }

}
