package com.soft.business.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FunctionalException extends RuntimeException{
    private String errorCode;
    private String errorMessage;

    public FunctionalException() {
        super();
    }

    public FunctionalException(String errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
