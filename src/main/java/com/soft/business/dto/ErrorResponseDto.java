package com.soft.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class ErrorResponseDto implements Serializable {

    private static final String ERROR_CODE = "error_code";
    private static final String ERROR_MESSAGE = "error_message";

    @JsonProperty(ERROR_CODE)
    private String errorCode;
    @JsonProperty(ERROR_MESSAGE)
    private String errorMessage;

    public ErrorResponseDto() {}

    public ErrorResponseDto(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
