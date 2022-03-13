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
        products.forEach(product -> productsDto.add(productMapper.makeDtoFromProduct(product)));

        return productsDto;
    }

    @Override
    public ProductDto findProductByUuid(String uuid) {
        Product product = productRepository.findByUuid(uuid).get();

        return productMapper.makeDtoFromProduct(product);
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
            productRepository.save(productMapper.makeProductFromDto(productDto));

            return new ResponseEntity<>(productDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateProductByUuid(String uuid, ProductDto productDto) {
        Optional<Product> product = this.productRepository.findByUuid(uuid);

        if(product.isEmpty()) throw new NoSuchElementException();

        Product updatedProduct = productMapper.updateProduct(productDto, product.get());

        return new ResponseEntity<>(
                productMapper.makeDtoFromProduct(productRepository.save(updatedProduct)),
                HttpStatus.OK
        );
    }


}
