package com.soft.business.repository;

import com.soft.business.model.SupplierOrder;
import com.soft.business.model.SupplierOrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierOrderItemRepository extends CrudRepository<SupplierOrderItem, Long> {

    Optional<SupplierOrderItem> findByUuidAndOrgId(String uuid, int orgId);

    long deleteByUuidAndOrgId(String uuid, int orgId);

    List<SupplierOrderItem> findByOrgId(int orgId);

    List<SupplierOrderItem> findBySupplierOrderAndOrgId(SupplierOrder supplierOrder, int orgid);

}
