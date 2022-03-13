package com.soft.business.service;

import com.soft.business.dto.ProductFamilyDto;
import com.soft.business.mapper.ProductFamilyMapper;
import com.soft.business.model.ProductFamily;
import com.soft.business.repository.ProductFamilyRepository;
import com.soft.business.util.validator.ProductFamilyValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductFamilyServiceImpl implements ProductFamilyService {

    private ProductFamilyRepository productFamilyRepository;

    private ProductFamilyMapper productFamilyMapper;

    private ProductFamilyValidator productFamilyValidator;

    public ProductFamilyServiceImpl(
            ProductFamilyRepository productFamilyRepository,
            ProductFamilyMapper productFamilyMapper,
            ProductFamilyValidator productFamilyValidator
    ) {
        this.productFamilyRepository = productFamilyRepository;
        this.productFamilyMapper = productFamilyMapper;
        this.productFamilyValidator = productFamilyValidator;
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
        ProductFamily productFamily = productFamilyRepository.findByUuid(uuid).get();

        return productFamilyMapper.makeDtoFromProductFamily(productFamily);
    }

    @Override
    public void deleteProductFamilyByUuid(String uuid) {
        long isDeleted = productFamilyRepository.deleteByUuid(uuid);
        if (isDeleted == 0) throw new NoSuchElementException();
    }

    @Override
    public ResponseEntity<?> createProductFamily(ProductFamilyDto productFamilyDto) {
        try {
            productFamilyValidator.createProductFamilyValidator(productFamilyDto);
            productFamilyRepository.save(productFamilyMapper.makeProductFamilyFromDto(productFamilyDto));

            return new ResponseEntity<>(productFamilyDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateProductFamilyByUuid(String uuid, ProductFamilyDto productFamilyDto) {
        Optional<ProductFamily> productFamily = this.productFamilyRepository.findByUuid(uuid);

        if(productFamily.isEmpty()) throw new NoSuchElementException();

        ProductFamily UpdatedProductFamily = productFamilyMapper
                                                .updateProductFamily(productFamilyDto, productFamily.get());

        return new ResponseEntity<>(
            productFamilyMapper.makeDtoFromProductFamily(productFamilyRepository.save(UpdatedProductFamily)),
            HttpStatus.OK
        );
    }
}
