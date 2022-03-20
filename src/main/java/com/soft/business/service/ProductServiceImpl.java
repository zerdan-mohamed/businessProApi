package com.soft.business.service;

import com.soft.business.dto.ProductDto;
import com.soft.business.mapper.ProductMapper;
import com.soft.business.model.Product;
import com.soft.business.repository.ProductRepository;
import com.soft.business.util.validator.ProductValidator;
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
    public ProductDto findProductByUuid(String uuid) {
        Optional<Product> product = productRepository.findByUuid(uuid);

        // TODO: Optional value should only be accessed after calling isPresent()
        return productMapper.makeDtoFromProduct(product.get());
    }

    @Override
    @Transactional
    public void deleteProductByUuid(String uuid) {
        long isDeleted = productRepository.deleteByUuid(uuid);
        if(isDeleted == 0) throw new NoSuchElementException();
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) throws ParseException {
        productValidator.createProductValidator(productDto);
        productRepository.save(productMapper.makeProductFromDto(productDto));

        return productDto;
    }

    @Override
    public ProductDto updateProductByUuid(String uuid, ProductDto productDto) {
        Optional<Product> optionalProduct = this.productRepository.findByUuid(uuid);

        if (optionalProduct.isEmpty()) throw new NoSuchElementException();

        Product product = productMapper.updateProduct(productDto, optionalProduct.get());
        productRepository.save(product);

        return productDto;
    }
}
