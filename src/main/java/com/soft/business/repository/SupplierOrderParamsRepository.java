package com.soft.business.repository;

import com.soft.business.model.SupplierOrderParams;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SupplierOrderParamsRepository {

    // TODO: check that this function is like "select 1 from supplierOrderParams
    Optional<SupplierOrderParams> findOrderParams();
}
