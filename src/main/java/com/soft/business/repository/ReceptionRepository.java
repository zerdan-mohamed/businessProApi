package com.soft.business.repository;

import com.soft.business.model.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ReceptionRepository extends CrudRepository<Reception, Long> {

    Optional<Reception> findByUuidAndOrgId(String uuid, int orgId);

    Set<Reception> findByOrgId(int orgId);

    long deleteByUuidAndOrgId(String uuid, int orgId);

    Set<Reception> findAllBySupplierIdAndOrgId(Long supplierId, int orgId);

    // TODO : find receptions by date
}
