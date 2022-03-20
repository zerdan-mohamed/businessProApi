package com.soft.business.mapper;

import com.soft.business.dto.ProductDto;
import com.soft.business.model.Product;
import com.soft.business.model.ProductFamily;
import com.soft.business.repository.ProductFamilyRepository;
import com.soft.business.util.Constantes;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductMapper {

    private ProductFamilyRepository productFamilyRepository;

    public ProductMapper(ProductFamilyRepository productFamilyRepository) {
        this.productFamilyRepository = productFamilyRepository;
    }

    public Product makeProductFromDto(ProductDto productDto) throws ParseException {
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
        product.setCreationDate(new Date());

        if (productDto.getInitialStockDate() != null)
            product.setInitialStockDate(
                    Constantes.dateFormatter.parse(productDto.getInitialStockDate())
            );

        if (productDto.getProductFamily() != null) {
            Optional<ProductFamily> optionalProductFamily = productFamilyRepository.findByUuid(
                    productDto.getProductFamily().getUuid());
            if (optionalProductFamily.isPresent()) {
                ProductFamily productFamily = optionalProductFamily.get();
                product.setProductFamily(productFamily);
            }
        }

        return product;
    }

    public ProductDto makeDtoFromProduct(Product product) {
        ProductDto productDto = new ProductDto();

        productDto.setUuid(product.getUuid());
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

        if (product.getInitialStockDate() != null)
            productDto.setInitialStockDate(product.getInitialStockDate().toString());

        if (product.getProductFamily() != null)
            productDto.setProductFamily(product.getProductFamily());

        return productDto;
    }

    public Product updateProduct(ProductDto productDto, Product productDb) {
        Product product = new Product();

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

        // TODO: Change deprecated Date object
        if(productDto.getInitialStockDate() != null) product.setInitialStockDate(new Date(Constantes.dateFormatter.format(productDto.getInitialStockDate())));
        else product.setInitialStockDate(productDb.getInitialStockDate());

        if(productDto.getMaximalStock() != null) product.setMaximalStock(productDto.getMaximalStock());
        else product.setMaximalStock(productDb.getMaximalStock());

        if(productDto.getMinimalStock() != null) product.setMinimalStock(productDto.getMinimalStock());
        else product.setMinimalStock(productDb.getMinimalStock());

        product.setUuid(productDb.getUuid());
        product.setIdProduct(productDb.getIdProduct());

        if (productDto.getProductFamily() != null) {
            Optional<ProductFamily> optionalProductFamily = productFamilyRepository.findByUuid(productDto.getProductFamily().getUuid());

            if (optionalProductFamily.isPresent()) {
                ProductFamily productFamily = optionalProductFamily.get();
                product.setProductFamily(productFamily);
            }

        } else if (productDb.getProductFamily() != null){
            product.setProductFamily(productDb.getProductFamily());
        }

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
