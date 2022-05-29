package com.soft.business.service;

import com.soft.business.dto.ProductDto;
import com.soft.business.mapper.ProductMapper;
import com.soft.business.model.Product;
import com.soft.business.repository.ProductRepository;
import com.soft.business.service.organization.OrganizationServiceImpl;
import com.soft.business.util.validator.ProductValidator;
import org.springframework.security.core.Authentication;
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
    public List<ProductDto> findProducts(Authentication authentication) {
        int orgId = OrganizationServiceImpl.getOrgIdFromPrincipal(authentication);
        List<Product> products = productRepository.findByOrgId(orgId);
        List<ProductDto> productsDto = new ArrayList<>();

        products.forEach(
                product -> productsDto.add(productMapper.makeDtoFromProduct(product))
        );

        return productsDto;
    }

    @Override
    public ProductDto findProductByUuid(Authentication authentication, String uuid) {
        int orgId = OrganizationServiceImpl.getOrgIdFromPrincipal(authentication);
        Optional<Product> product = productRepository.findByUuidAndOrgId(uuid, orgId);

        return productMapper.makeDtoFromProduct(product.get());
    }

    @Override
    @Transactional
    public void deleteProductByUuid(Authentication authentication, String uuid) {
        int orgId = OrganizationServiceImpl.getOrgIdFromPrincipal(authentication);
        long isDeleted = productRepository.deleteByUuidAndOrgId(uuid, orgId);
        if(isDeleted == 0) throw new NoSuchElementException();
    }

    @Override
    public ProductDto createProduct(
            Authentication authentication, ProductDto productDto) throws ParseException {
        int orgId = OrganizationServiceImpl.getOrgIdFromPrincipal(authentication);
        productValidator.createProductValidator(productDto);
        productRepository.save(productMapper.makeProductFromDto(orgId, productDto));

        return productDto;
    }

    @Override
    public ProductDto updateProductByUuid(
            Authentication authentication, String uuid, ProductDto productDto) {
        int orgId = OrganizationServiceImpl.getOrgIdFromPrincipal(authentication);
        Optional<Product> optionalProduct = this.productRepository.findByUuidAndOrgId(uuid, orgId);

        if (optionalProduct.isEmpty()) throw new NoSuchElementException();

        Product product = productMapper.updateProduct(productDto, optionalProduct.get());
        productRepository.save(product);

        return productDto;
    }
}
