package com.soft.business.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
public class  EmptyInputException extends RuntimeException {
    private String errorCode;
    private String errorMessage;

    public EmptyInputException() {
        super();
    }

    public EmptyInputException(String errorCode,
                               String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
