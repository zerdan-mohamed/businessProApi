package com.soft.business.mapper;

import com.soft.business.dto.ItemReceptionDto;
import com.soft.business.dto.ReceptionDto;
import com.soft.business.model.Reception;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Service
public class ReceptionMapper {

    public Reception makeReceptionFromDto(int orgId, ReceptionDto receptionDto) {
        Reception reception = new Reception();

        reception.setOrgId(orgId);
        reception.setUuid(UUID.randomUUID().toString());
        reception.setCreationDate(LocalDateTime.now());
        reception.setDescription(receptionDto.getDescription());
        reception.setSupplierId(receptionDto.getSupplierId());

        return  reception;
    }

    public ReceptionDto makeDtoFromReception(Reception reception, Set<ItemReceptionDto> itemsReceptionDto) {

        ReceptionDto receptionDto = new ReceptionDto();

        receptionDto.setUuid(reception.getUuid());
        receptionDto.setDescription(reception.getDescription());
        receptionDto.setCreationDate(reception.getCreationDate().toString());
        receptionDto.setSupplierId(reception.getSupplierId());

        receptionDto.setReceptionItems(itemsReceptionDto);

        return receptionDto;
    }

    public Reception updateReception(int orgId, ReceptionDto receptionDto, Reception dbReception) {

        return null;
    }

}
