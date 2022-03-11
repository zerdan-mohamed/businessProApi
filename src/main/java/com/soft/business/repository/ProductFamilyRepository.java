package com.soft.business.repository;

import com.soft.business.model.ProductFamily;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin
public interface ProductFamilyRepository extends CrudRepository<ProductFamily, Long> {
}

