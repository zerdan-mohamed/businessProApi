package com.soft.business.repository;

import com.soft.business.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Repository
@CrossOrigin
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByNameContaining(String name);

    Optional<Product> findByUuid(String uuid);

    long deleteByUuid(String uuid);
}