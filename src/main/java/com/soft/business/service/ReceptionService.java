package com.soft.business.service;

import com.soft.business.dto.ReceptionDto;
import org.springframework.security.core.Authentication;

import java.text.ParseException;
import java.util.Set;

public interface ReceptionService {

    Set<ReceptionDto> findReceptions(Authentication authentication);

    ReceptionDto findReceptionByUuid(Authentication authentication, String uuid);

    void deleteReceptionByUuid(Authentication authentication, String uuid);

    ReceptionDto  createReception(Authentication authentication, ReceptionDto receptionDto) throws ParseException;

    ReceptionDto updateReceptionByUuid(Authentication authentication, String uuid, ReceptionDto receptionDto);
}
