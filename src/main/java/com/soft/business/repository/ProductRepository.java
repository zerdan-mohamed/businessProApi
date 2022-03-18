package com.soft.business.repository;

import com.soft.business.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
}
