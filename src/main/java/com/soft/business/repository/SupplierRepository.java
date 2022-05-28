package com.soft.business.repository;

import com.soft.business.model.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {
    long deleteByUuidAndOrgId(String uuid, int orgId);
    Optional<Supplier> findByUuidAndOrgId(String uuid, int orgId);
    @Deprecated Optional<Supplier> findByUuid(String uuid);
    List<Supplier> findByOrgId(int orgId);
}
