package com.soft.business.service;

import com.soft.business.dto.ProductFamilyDto;
import com.soft.business.mapper.ProductFamilyMapper;
import com.soft.business.model.Product;
import com.soft.business.model.ProductFamily;
import com.soft.business.repository.ProductFamilyRepository;
import com.soft.business.util.validator.ProductFamilyValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> findProductFamilyByUuid(String uuid) {
        Optional<ProductFamily>  optionalProductFamily = productFamilyRepository.findByUuid(uuid);
        ProductFamilyDto productFamilyDto =
                    productFamilyMapper.makeDtoFromProductFamily(optionalProductFamily.get());
        return new ResponseEntity<>(productFamilyDto, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteProductFamilyByUuid(String uuid) {
        long isDeleted = productFamilyRepository.deleteByUuid(uuid);
        if(isDeleted == 0) throw new NoSuchElementException();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createProductFamily(ProductFamilyDto productFamilyDto) {
        productFamilyValidator.createProductFamilyValidator(productFamilyDto);
        ProductFamily p = productFamilyMapper.makeProductFamilyFromDto(productFamilyDto);
        ProductFamily productFamily = productFamilyRepository.save(p);
        return new ResponseEntity<>(productFamilyMapper.makeDtoFromProductFamily(productFamily),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateProductFamilyByUuid(String uuid, ProductFamilyDto productFamilyDto) {
        Optional<ProductFamily> oProductFamily = this.productFamilyRepository.findByUuid(uuid);
        if(oProductFamily.isEmpty()) throw new NoSuchElementException();
        ProductFamily updatedProductFamily =
                    productFamilyMapper.updateProductFamily(productFamilyDto, oProductFamily.get());
        ProductFamily savedProductFamily = productFamilyRepository.save(updatedProductFamily);

        return new ResponseEntity<>(
                productFamilyMapper.makeDtoFromProductFamily(savedProductFamily), HttpStatus.OK
        );
    }
}
