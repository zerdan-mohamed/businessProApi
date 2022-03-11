package com.soft.business.service;

import com.soft.business.repository.ProductFamilyRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductFamilyService {

    ProductFamilyRepository productFamilyRepository;

    public ProductFamilyService(ProductFamilyRepository productFamilyRepository) {
        this.productFamilyRepository = productFamilyRepository;
    }
}
