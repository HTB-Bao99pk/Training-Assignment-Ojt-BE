package com.group4.electronicsstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshTokenRequest {
    @NotBlank(message = "REFRESH_TOKEN_EMPTY")
    private String refreshToken;
}
