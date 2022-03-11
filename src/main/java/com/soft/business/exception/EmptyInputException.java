package com.soft.business.exception;

public class EmptyInputException extends FunctionalException {

    public EmptyInputException() {
        super();
    }

    public EmptyInputException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
