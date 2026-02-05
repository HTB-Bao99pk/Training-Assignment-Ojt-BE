package com.group4.electronicsstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ChangePasswordResponse {
    private String username;
    private LocalDateTime changedAt;
}
