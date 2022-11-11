package com.soft.business.mapper;

import com.soft.business.dto.ProductFamilyDto;
import com.soft.business.model.ProductFamily;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductFamilyMapperr {

    public ProductFamily makeProductFamilyFromDto(int orgId, ProductFamilyDto productFamilyDto) {
        ProductFamily productFamily = new ProductFamily();
        productFamily.setUuid(UUID.randomUUID().toString());
        productFamily.setName(productFamilyDto.getName());
        productFamily.setOrgId(orgId);
        return productFamily;
    }

    public ProductFamilyDto makeDtoFromProductFamily(ProductFamily productFamily) {
        ProductFamilyDto productFamilyDto = new ProductFamilyDto();
        productFamilyDto.setUuid(productFamily.getUuid());
        productFamilyDto.setName(productFamily.getName());
        return productFamilyDto;
    }

    public ProductFamily updateProductFamily(ProductFamilyDto productFamilyDto, ProductFamily productFamilyDb) {
        ProductFamily productFamily = new ProductFamily();

        productFamily.setIdProductFamily(productFamilyDb.getIdProductFamily());
        productFamily.setUuid(productFamilyDb.getUuid());

        if(productFamilyDto.getName() != null) productFamily.setName(productFamilyDto.getName());
        else productFamily.setName(productFamilyDb.getName());

        return productFamily;
    }
}
