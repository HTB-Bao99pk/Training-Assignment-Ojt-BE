package com.group4.electronicsstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordRequest {

    @NotBlank(message = "NOT_BLANK")
    @Size(min = 6, message = "PASSWORD_LENGTH_INVALID")
    private String newPassword;

    @Size(min = 6, message = "PASSWORD_LENGTH_INVALID")
    @NotBlank(message = "OLD_PASSWORD_WRONG")
    private String oldPassword;


    @NotBlank(message = "USER_NAME_AND_PASSWORD_NOT_BLANK")
    private String confirmPassword;
}

