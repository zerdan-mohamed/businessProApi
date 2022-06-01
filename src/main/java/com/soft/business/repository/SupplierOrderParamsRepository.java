package com.soft.business.repository;

import com.soft.business.model.SupplierOrderParams;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SupplierOrderParamsRepository extends CrudRepository<SupplierOrderParams, Long> {

    Optional<SupplierOrderParams> findSupplierOrderParamsByOrgId(int orgId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update SupplierOrderParams sop set sop.counter = sop.counter+1 where sop.orgId = :orgId")
    void updateSupplierOrderParamsCounter(@Param("orgId") Integer orgId);
}
