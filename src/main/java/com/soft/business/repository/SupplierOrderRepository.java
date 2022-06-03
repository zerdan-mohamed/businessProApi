package com.soft.business.repository;

import com.soft.business.model.SupplierOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SupplierOrderRepository extends CrudRepository<SupplierOrder, Long> {

    Optional<SupplierOrder> findByUuidAndOrgId(String uuid, int orgId);

    Set<SupplierOrder> findByOrgId(int orgId);

}
