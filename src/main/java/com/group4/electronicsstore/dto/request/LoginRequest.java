package com.group4.electronicsstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "USER_NAME_AND_PASSWORD_NOT_BLANK")
    private String username;

    @NotBlank(message = "USER_NAME_AND_PASSWORD_NOT_BLANK")
    private String password;
}