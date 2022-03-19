package com.soft.business.exception;

public class DateFormatException extends FunctionalException {
    public DateFormatException() {
        super();
    }

    public DateFormatException(String errorCode,
                               String errorMessage) {
        super(errorCode, errorMessage);
    }
}
