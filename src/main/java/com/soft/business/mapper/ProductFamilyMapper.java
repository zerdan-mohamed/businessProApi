package com.soft.business.mapper;

import com.soft.business.dto.ProductFamilyDto;
import com.soft.business.model.ProductFamily;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class})
public interface ProductFamilyMapper {
    ProductFamilyMapper instance = Mappers.getMapper(ProductFamilyMapper.class);

    @Mapping(target = "uuid", expression = "java(UUID.randomUUID().toString())")
    @Mapping(target = "orgId", source = "orgId")
    ProductFamily dtoToModelCreate(ProductFamilyDto productFamilyDto, int orgId);

    ProductFamilyDto modelToDto(ProductFamily productFamily);
    @Mapping(target = "orgId", source = "orgId")
    ProductFamily dtoToModelUpdate(ProductFamilyDto productFamilyDto, int orgId);
}
