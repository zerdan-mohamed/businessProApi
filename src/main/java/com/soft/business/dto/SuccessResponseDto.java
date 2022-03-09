package com.soft.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class SuccessResponseDto implements Serializable {
    private static final String SUCCESS_MESSAGE = "success_message";

    @JsonProperty(SUCCESS_MESSAGE)
    private String successMessage;


    public SuccessResponseDto() {
    }

    public SuccessResponseDto(String successMessage) {
        this.successMessage = successMessage;
    }
}
