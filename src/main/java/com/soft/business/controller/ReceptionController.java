package com.soft.business.controller;

import com.soft.business.dto.ReceptionDto;
import com.soft.business.service.ReceptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Set;

@RestController
@RequestMapping("api/receptions")
public class ReceptionController {

    private final ReceptionService receptionService;

    public ReceptionController(ReceptionService receptionService) {
        this.receptionService = receptionService;
    }

    @PostMapping()
    // @PreAuthorize("hasAuthority('FUNC_RECEPTION_CREATE')")
    public ResponseEntity<ReceptionDto> createReception(
            @Valid @RequestBody ReceptionDto receptionDto,
            Authentication authentication
    ) throws ParseException {
        return new ResponseEntity<>(
                receptionService.createReception(authentication, receptionDto),
                HttpStatus.OK
        );
    }

    @GetMapping()
    // @PreAuthorize("hasAuthority('FUNC_RECEPTION_RETRIEVE')")
    public ResponseEntity<Set<ReceptionDto>> findAllReceptions(Authentication authentication) {
        Set<ReceptionDto> receptions = this.receptionService.findReceptions(authentication);
        return new ResponseEntity<>(receptions, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    // @PreAuthorize("hasAuthority('FUNC_RECEPTION_RETRIEVE')")
    public ResponseEntity<ReceptionDto> findReceptionByUuid(
            Authentication authentication, @PathVariable("uuid") String uuid) {
        ReceptionDto receptionDto = this.receptionService.findReceptionByUuid(authentication, uuid);
        return ResponseEntity.ok(receptionDto);
    }

    @PatchMapping("/{uuid}")
    // @PreAuthorize("hasAuthority('FUNC_RECEPTION_UPDATE')")
    public ResponseEntity<ReceptionDto> updateReception(
            Authentication authentication,
            @PathVariable("uuid") String uuid,
            @Valid @RequestBody ReceptionDto receptionDto
    ) {
        return new ResponseEntity<>(
                receptionService.updateReceptionByUuid(authentication, uuid, receptionDto), HttpStatus.OK
        );
    }

    @DeleteMapping("/{uuid}")
    // @PreAuthorize("hasAuthority('FUNC_RECEPTION_DELETE')")
    public ResponseEntity deleteReceptionByUuid(
            Authentication authentication, @PathVariable("uuid") String uuid
    ) {
        receptionService.deleteReceptionByUuid(authentication, uuid);
        return ResponseEntity.ok().build();
    }

}
