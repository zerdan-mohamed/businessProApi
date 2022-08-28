package com.soft.business.repository;

import com.soft.business.model.SupplierOrder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SupplierOrderRepository extends CrudRepository<SupplierOrder, Long> {

    Optional<SupplierOrder> findByUuidAndOrgId(String uuid, int orgId);

    @Query("select so from SupplierOrder so where so.orgId = ?1 and so.idSupplierOrder in ?2")
    Set<SupplierOrder> findByOrgIdAndIdSupplierOrder(int orgId, List<Long> idSupplierOrder);


    // Set<SupplierOrder> findByIdSupplierOrderAndOrgId(int orgId, List<Long> idSupplierOrder);

    Optional<SupplierOrder> findByIdSupplierOrderAndOrgId(Long idSupplierOrder, int orgId);

    Set<SupplierOrder> findByOrgId(int orgId);

    @Modifying
    @Query(
        "update SupplierOrder s" +
            " set s.supplierOrderStatus = :supplierOrderStatus " +
            "where s.idSupplierOrder = :idSupplierOrder " +
            "and s.orgId = :orgId"
    )
    void updateSupplierOrderStatus(
            @Param(value = "idSupplierOrder") long idSupplierOrder,
            @Param(value = "supplierOrderStatus") Integer supplierOrderStatus,
            @Param(value = "orgId") Integer orgId
    );

}
