package com.soft.business.repository;

import com.soft.business.model.SupplierOrderParams;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SupplierOrderParamsRepository extends CrudRepository<SupplierOrderParams, Long> {

    Optional<SupplierOrderParams> findSupplierOrderParamsByOrgId(int orgId);
}
