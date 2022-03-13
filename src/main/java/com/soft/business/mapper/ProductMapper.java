package com.soft.business.mapper;

import com.soft.business.dto.ProductDto;
import com.soft.business.model.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductMapper {

    /**
     * A lot of duplicated code
     *
     * @deprecated use {cloneProductFromDto(ProductDto productDto)} instead.
     */
    @Deprecated
    public Product makeProductFromDto(ProductDto productDto) {
        Product product = new Product();

        product.setUuid(UUID.randomUUID().toString());
        product.setName(productDto.getName());
        product.setMeasureUnite(productDto.getMeasureUnite());
        product.setVatRate(productDto.getVatRate());
        product.setBuyingPrice(productDto.getBuyingPrice());
        product.setResellerPrice(productDto.getResellerPrice());
        product.setPublicSalePrice(productDto.getPublicSalePrice());
        product.setCurrentStock(productDto.getCurrentStock());
        product.setInitialStock(productDto.getInitialStock());
        product.setMinimalStock(productDto.getMinimalStock());
        product.setMaximalStock(productDto.getMaximalStock());
        product.setCreationDate(productDto.getCreationDate());
        // product.setProductFamily(productDto.getIdProductFamily());

        return product;
    }

    /**
     * A lot of duplicated code
     *
     * @deprecated use {cloneDtoFromProduct(Product product)} instead.
     */
    @Deprecated
    public ProductDto makeDtoFromProduct(Product product) {
        ProductDto productDto = new ProductDto();

        productDto.setUuid(UUID.randomUUID().toString());
        productDto.setName(product.getName());
        productDto.setMeasureUnite(product.getMeasureUnite());
        productDto.setVatRate(product.getVatRate());
        productDto.setBuyingPrice(product.getBuyingPrice());
        productDto.setResellerPrice(product.getResellerPrice());
        productDto.setPublicSalePrice(product.getPublicSalePrice());
        productDto.setCurrentStock(product.getCurrentStock());
        productDto.setInitialStock(product.getInitialStock());
        productDto.setMinimalStock(product.getMinimalStock());
        productDto.setMaximalStock(product.getMaximalStock());
        productDto.setCreationDate(product.getCreationDate());

        // TODO: handle set id of productFamily
        // productDto.setProductFamily(product.getIdProductFamily());

        return productDto;
    }

    public Product updateProduct(ProductDto productDto, Product productDb) {

        Product product = new Product();

        if(productDto.getUuid() != null) product.setUuid(productDto.getName());
        else product.setName(productDb.getName());

        if(productDto.getName() != null) product.setName(productDto.getName());
        else product.setName(productDb.getName());

        if(productDto.getVatRate() != null) product.setVatRate(productDto.getVatRate());
        else product.setVatRate(productDb.getVatRate());

        if(productDto.getMeasureUnite() != null) product.setMeasureUnite(productDto.getMeasureUnite());
        else product.setMeasureUnite(productDb.getMeasureUnite());

        if(productDto.getBuyingPrice() != null) product.setBuyingPrice(productDto.getBuyingPrice());
        else product.setBuyingPrice(productDb.getBuyingPrice());

        if(productDto.getResellerPrice() != null) product.setResellerPrice(productDto.getResellerPrice());
        else product.setResellerPrice(productDb.getResellerPrice());

        if(productDto.getPublicSalePrice() != null) product.setPublicSalePrice(productDto.getPublicSalePrice());
        else product.setPublicSalePrice(productDb.getPublicSalePrice());

        if(productDto.getCurrentStock() != null) product.setCurrentStock(productDto.getCurrentStock());
        else product.setCurrentStock(productDb.getCurrentStock());

        if(productDto.getInitialStock() != null) product.setInitialStock(productDto.getInitialStock());
        else product.setInitialStock(productDb.getInitialStock());

        if(productDto.getInitialStockDate() != null) product.setInitialStockDate(productDto.getInitialStockDate());
        else product.setInitialStockDate(productDb.getInitialStockDate());

        if(productDto.getMaximalStock() != null) product.setMaximalStock(productDto.getMaximalStock());
        else product.setMaximalStock(productDb.getMaximalStock());

        if(productDto.getMinimalStock() != null) product.setMinimalStock(productDto.getMinimalStock());
        else product.setMinimalStock(productDb.getMinimalStock());

        if(productDto.getCreationDate() != null) product.setCreationDate(productDto.getCreationDate());
        else product.setCreationDate(productDb.getCreationDate());

        return product;
    }

    // TODO: this function will be optimize the code from deprecated function makeProductFromDto()
    public Product cloneProductFromDto (ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);

        return product;
    }

    // TODO: this function will be optimize the code from deprecated function makeDtoFromProduct()
    public ProductDto cloneDtoFromProduct (Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto, "idProduct");

        return productDto;
    }
}
