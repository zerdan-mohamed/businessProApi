package com.soft.business.service;

import com.soft.business.dto.ProductDto;
import com.soft.business.mapper.ProductMapper;
import com.soft.business.model.Product;
import com.soft.business.repository.ProductRepository;
import com.soft.business.util.validator.ProductValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
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
        Optional<Product> oProduct = productRepository.findByUuid(uuid);
        ProductDto productDto = productMapper.makeDtoFromProduct(oProduct.get());
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteProductByUuid(String uuid) {
        long isDeleted = productRepository.deleteByUuid(uuid);
        if(isDeleted == 0) throw new NoSuchElementException();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?>  createProduct(ProductDto productDto) throws ParseException {
        productValidator.createProductValidator(productDto);
        Product p = productMapper.makeProductFromDto(productDto);
        Product savedProduct = productRepository.save(p);
        return new ResponseEntity<>(productMapper.makeDtoFromProduct(savedProduct), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateProductByUuid(String uuid, ProductDto productDto) {
        Optional<Product> oProduct = this.productRepository.findByUuid(uuid);
        if(oProduct.isEmpty()) throw new NoSuchElementException();
        Product updatedProduct = productMapper.updateProduct(productDto, oProduct.get());
        Product savedProduct = productRepository.save(updatedProduct);
        return new ResponseEntity<>(
                productMapper.makeDtoFromProduct(savedProduct),
                HttpStatus.OK
        );
    }
}
