package com.soft.business.repository;

import com.soft.business.model.Product;
import com.soft.business.model.ProductFamily;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findByUuid(String uuid);

    long deleteByUuid(String uuid);

    List<Product> findAll();

    List<Product> findAllByProductFamily(ProductFamily productFamily);
}
