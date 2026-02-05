package com.group4.electronicsstore.dto.response;

import com.group4.electronicsstore.entity.Role;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long userId;
    private String fullName;
    private String username;
    private String email;
    private Role roles;
    private String accessToken;
    private String refreshToken;
}
