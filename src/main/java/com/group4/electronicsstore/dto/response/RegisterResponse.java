package com.group4.electronicsstore.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponse {
    private Long userId;
    private String username;
    private String email;
    private String fullName;
    private String role;
    private String message;
}
