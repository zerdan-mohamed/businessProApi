package com.soft.business.controller;

import com.soft.business.dto.ErrorResponseDto;
import com.soft.business.exception.EmptyInputException;
import com.soft.business.util.ApiErrorCodesConstantes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<ErrorResponseDto> handleEmptyInput(EmptyInputException emptyInputException) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(emptyInputException.getErrorCode(), emptyInputException.getErrorMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponseDto> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ApiErrorCodesConstantes.NO_SUCH_ELEMENT_EXCEPTION_CODE, ApiErrorCodesConstantes.NO_SUCH_ELEMENT_EXCEPTION_MESSAGE);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }
}
