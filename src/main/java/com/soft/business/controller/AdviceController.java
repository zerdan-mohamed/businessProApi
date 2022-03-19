package com.soft.business.controller;

import com.soft.business.dto.ErrorResponseDto;
import com.soft.business.exception.DateFormatException;
import com.soft.business.exception.EmptyInputException;
import com.soft.business.util.ApiErrorCodesConstantes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.text.ParseException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DateFormatException.class)
    public ResponseEntity<ErrorResponseDto> handleDateFormatException(DateFormatException dateFormatException) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(dateFormatException.getErrorCode(), dateFormatException.getErrorMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<ErrorResponseDto> handleEmptyInput(EmptyInputException emptyInputException) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(emptyInputException.getErrorCode(), emptyInputException.getErrorMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponseDto> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ApiErrorCodesConstantes.NO_SUCH_ELEMENT_EXCEPTION_CODE,
                ApiErrorCodesConstantes.NO_SUCH_ELEMENT_EXCEPTION_MESSAGE);

        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleConstraintViolationException(ConstraintViolationException constraintViolationException) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ApiErrorCodesConstantes.CONSTRAINT_VIOLATION_EXCEPTION_CODE,
                constraintViolationException.getConstraintViolations().stream().iterator().next().getPropertyPath() +" - "+
                constraintViolationException.getConstraintViolations().stream().iterator().next().getMessage());

        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ErrorResponseDto> handleParseExceptionException(ParseException parseException) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ApiErrorCodesConstantes.NO_SUCH_ELEMENT_EXCEPTION_CODE,
                ApiErrorCodesConstantes.NO_SUCH_ELEMENT_EXCEPTION_MESSAGE);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }


}
