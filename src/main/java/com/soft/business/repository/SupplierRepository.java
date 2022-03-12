package com.soft.business.repository;

import com.soft.business.model.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {
    long deleteByUuid(String uuid);
    Optional<Supplier> findByUuid(String uuid);
    List<Supplier> findAll();
}
