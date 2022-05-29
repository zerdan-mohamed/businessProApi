package com.soft.business.repository;

import com.soft.business.model.Product;
import com.soft.business.model.ProductFamily;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findByUuidAndOrgId(String uuid, int orgId);

    long deleteByUuidAndOrgId(String uuid, int orgId);

    List<Product> findByOrgId(int orgId);

    List<Product> findAllByProductFamilyAndOrgId(ProductFamily productFamily, int orgId);
}
