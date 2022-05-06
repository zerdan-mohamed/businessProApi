package com.soft.business.dto.auth;

import lombok.Data;

@Data
public class JwtAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthResponse(String jwt) {
        this.accessToken = jwt;
    }
}
