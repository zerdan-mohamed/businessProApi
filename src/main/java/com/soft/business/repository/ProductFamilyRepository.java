package com.soft.business.repository;

import com.soft.business.model.ProductFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductFamilyRepository extends JpaRepository<ProductFamily, Long> {

    Optional<ProductFamily> findByUuidAndOrgId(String uuid, int orgId);

    long deleteByUuidAndOrgId(String uuid, int orgId);

    List<ProductFamily> findByOrgId(int orgId);

}

