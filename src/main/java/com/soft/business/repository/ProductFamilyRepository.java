package com.soft.business.repository;

import com.soft.business.model.ProductFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ProductFamilyRepository extends JpaRepository<ProductFamily, Long> {

    Optional<ProductFamily> findByUuidAndOrgId(String uuid, int orgId);

    @Modifying()
    @Query("""
            DELETE FROM ProductFamily pf WHERE pf.uuid=:uuid and pf.orgId=:orgId
           """)
    void deleteByUuidAndOrgId(@Param("uuid") String uuid, @Param("orgId") int orgId);

    List<ProductFamily> findByOrgId(int orgId);
}

