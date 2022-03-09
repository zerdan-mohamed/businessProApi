package com.soft.business.controller;

import com.soft.business.dto.ErrorResponseDto;
import com.soft.business.exception.EmptyInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<ErrorResponseDto> handleEmptyInput(EmptyInputException emptyInputException) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(emptyInputException.getErrorCode(), emptyInputException.getErrorMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }
}
