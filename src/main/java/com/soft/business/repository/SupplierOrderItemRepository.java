package com.soft.business.repository;

import com.soft.business.model.SupplierOrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierOrderItemRepository extends CrudRepository<SupplierOrderItem, Long> {

    long deleteByUuid(String uuid);
    Optional<SupplierOrderItem> findByUuid(String uuid);
    List<SupplierOrderItem> findAll();

}
