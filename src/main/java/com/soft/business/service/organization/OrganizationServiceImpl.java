package com.soft.business.service.organization;

import com.soft.business.model.SupplierOrder;
import com.soft.business.model.SupplierOrderParams;
import com.soft.business.repository.SupplierOrderParamsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final SupplierOrderParamsRepository supplierOrderParamsRepository;

    public OrganizationServiceImpl(SupplierOrderParamsRepository supplierOrderParamsRepository) {
        this.supplierOrderParamsRepository = supplierOrderParamsRepository;
    }

    @Override
    public String makeSupplierOrderNumber(Integer orgId, SupplierOrder supplierOrder) {
        Optional<SupplierOrderParams> oSupplierOrderParams = this.supplierOrderParamsRepository
                                                        .findSupplierOrderParamsByOrgId(orgId);
        SupplierOrderParams supplierOrderParams = null;
        LocalDateTime creationDate = supplierOrder.getCreationDate();
        if(oSupplierOrderParams.isPresent()) {
            supplierOrderParams = oSupplierOrderParams.get();
            String prefix = supplierOrderParams.getPrefix()+"_"+creationDate.getYear()+"_"+supplierOrderParams.getCounter();
            return prefix;
        }
        throw new NoSuchElementException();
    }

    @Override
    public void incrementPrefix(Integer orgId) {
        this.supplierOrderParamsRepository.updateSupplierOrderParamsCounter(orgId);
    }
}
