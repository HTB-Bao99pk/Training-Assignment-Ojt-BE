package com.group4.electronicsstore.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "USER_NAME_AND_PASSWORD_NOT_BLANK")
    @Size(min = 6, max = 28, message = "USER_NAME_LENGTH_INVALID")
    private String username;

    @NotBlank(message = "USER_NAME_AND_PASSWORD_NOT_BLANK")
    @Size(min = 6, message = "PASSWORD_LENGTH_INVALID")
    private String password;

    @NotBlank(message = "CONFIRM_PASSWORD_NOT_BLANK")
    private String confirmPassword;

    @NotBlank(message = "EMAIL_NOT_BLANK")
    @Email(message = "EMAIL_INVALID")
    private String email;

    @NotBlank(message = "FULL_NAME_IS_REQUIRED")
    @Size(min = 2, max = 50, message = "FULL_NAME_LENGTH_INVALID")
    private String fullName;
}
