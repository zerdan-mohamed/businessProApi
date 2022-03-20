package com.soft.business.service;

import com.soft.business.dto.ProductFamilyDto;
import com.soft.business.mapper.ProductFamilyMapper;
import com.soft.business.model.Product;
import com.soft.business.model.ProductFamily;
import com.soft.business.repository.ProductFamilyRepository;
import com.soft.business.repository.ProductRepository;
import com.soft.business.util.validator.ProductFamilyValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class ProductFamilyServiceImpl implements ProductFamilyService {

    private ProductFamilyRepository productFamilyRepository;

    private ProductRepository productRepository;

    private ProductFamilyMapper productFamilyMapper;

    private ProductFamilyValidator productFamilyValidator;

    public ProductFamilyServiceImpl(
            ProductFamilyRepository productFamilyRepository,
            ProductFamilyMapper productFamilyMapper,
            ProductFamilyValidator productFamilyValidator,
            ProductRepository productRepository
    ) {
        this.productFamilyRepository = productFamilyRepository;
        this.productFamilyMapper = productFamilyMapper;
        this.productFamilyValidator = productFamilyValidator;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductFamilyDto> findProductFamilies() {
        List<ProductFamily> productFamilies = productFamilyRepository.findAll();
        List<ProductFamilyDto> productFamiliesDto = new ArrayList<>();
        productFamilies.forEach(
                product -> productFamiliesDto.add(productFamilyMapper.makeDtoFromProductFamily(product))
        );

        return productFamiliesDto;
    }

    @Override
    public ProductFamilyDto findProductFamilyByUuid(String uuid) {
        Optional<ProductFamily>  productFamily = productFamilyRepository.findByUuid(uuid);

        // TODO: Optional value should only be accessed after calling isPresent()
        return productFamilyMapper.makeDtoFromProductFamily(productFamily.get());
    }

    @Override
    @Transactional
    public void deleteProductFamilyByUuid(String uuid) {
        long isDeleted = productFamilyRepository.deleteByUuid(uuid);
        if(isDeleted == 0) throw new NoSuchElementException();
    }

    @Override
    public ProductFamilyDto createProductFamily(ProductFamilyDto productFamilyDto) {
        productFamilyValidator.createProductFamilyValidator(productFamilyDto);
        productFamilyRepository.save(productFamilyMapper.makeProductFamilyFromDto(productFamilyDto));

        return productFamilyDto;
    }

    @Override
    public ProductFamilyDto updateProductFamilyByUuid(String uuid, ProductFamilyDto productFamilyDto) {
        Optional<ProductFamily> optionalProductFamily = this.productFamilyRepository.findByUuid(uuid);

        if (optionalProductFamily.isEmpty()) throw new NoSuchElementException();

        ProductFamily productFamily = productFamilyMapper.updateProductFamily(productFamilyDto, optionalProductFamily.get());
        productFamilyRepository.save(productFamily);

        return productFamilyDto;
    }
}
