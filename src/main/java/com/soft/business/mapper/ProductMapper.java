package com.soft.business.mapper;

import com.soft.business.dto.ProductDto;
import com.soft.business.model.Product;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Optional<ProductDto> makeDtoFromOptionalProduct(Optional<Product> product);

    Optional<Product> makeOptionalProductFromDto(Optional<ProductDto> productDto);

    Product makeProductFromDto(ProductDto productDto);

    ProductDto makeDtoFromProduct(Product product);

    List<ProductDto> productsToProductsDto(List<Product> products);

    List<Product> productsDtoToProducts(List<ProductDto> productsDto);

}
