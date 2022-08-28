package com.soft.business.repository;

import com.soft.business.model.ItemReception;
import com.soft.business.model.ItemReceptionId;
import com.soft.business.model.Reception;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ItemReceptionRepository extends CrudRepository<ItemReception, ItemReceptionId> {

    Optional<ItemReception> findByUuidAndOrgIdAndReceptionId(String uuid, int orgId, Long receptionId);

    Set<ItemReception> findAllByReceptionIdAndOrgId(Long receptionId, int orgId);

    long deleteByUuidAndOrgId(String uuid, int orgId);

}
